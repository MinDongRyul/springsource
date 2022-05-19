package com.study.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.study.dto.AttachDTO;
import com.study.mapper.AttachMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileCheckTask {

	@Autowired
	private AttachMapper attachMapper;
		
	//전일자 폴더 구하기
	private String getFolderYesterDay() {
		
		//어제 날짜 추출, LocalDate.now() : 오늘 날짜
		LocalDate yesterday = LocalDate.now().minusDays(1); // 2022-05-09 
	
		//추출된 날짜의 포맷 변경 "2022-05-09"
		String str = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
		//File.separator : 파일 구분자 
		return str.replace("-", File.separator); // "2022-05-09" => "2022\05\09"
	}
	
	@Scheduled(cron="0 0 2 * * *")
	public void checkFiles() {
		
		log.info("file check task run");
		
		//DB에서 가져올 때 부터 어제의 날짜 목록을 가져옴. 아직까지는 list
		List<AttachDTO> oldList = attachMapper.getOldFiles(); 
		
		List<Path> fileListPaths = oldList.stream() // 스트림화 시킴
										  .map(dto -> Paths.get("c:\\upload", dto.getUploadPath(), dto.getUuid()+"_"+dto.getFileName()))
										  .collect(Collectors.toList()); // 리스트에 모아준다
		
		// oldList -> dto, 썸네일 처리하기 위함
		oldList.stream()
			   .filter(dto -> dto.isFileType() == true) // 이미지라면
			   .map(dto -> Paths.get("c:\\upload", dto.getUploadPath(), "s_"+dto.getUuid()+"_"+dto.getFileName())) // Path만 모아준다
			   .forEach(f -> fileListPaths.add(f)); // 순차적으로 모은 파일명을 fileListPaths<Path>에 추가
		
		//전일자 폴더의 파일 목록 추출
		File targetDir = Paths.get("c:\\upload", getFolderYesterDay()).toFile();
		
		//targetDir : 하루 전 날짜의 폴더의 파일 목록, fileListPaths : 현재 DB에 있는 파일 목록
 		//전일자의 파일목록과 DB의 파일목록을 비교 -> DB리스트안에 파일 경로가 없다면 removeFiles에 담아줌
		File[] removeFiles = targetDir.listFiles(f -> fileListPaths.contains(f.toPath()) == false);
		
		//비교 후 폴더 안의 파일 삭제
		for(File remove:removeFiles) {
			log.info("제거 파일 : "+remove.getAbsolutePath());
			remove.delete();
		}
	}
	
}
