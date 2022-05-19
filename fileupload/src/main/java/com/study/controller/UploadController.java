package com.study.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UploadController {
	
	@GetMapping("/uploadform")
	public void uloadFile() {
		log.info("upload 폼 요청");
	}
	
	
	// type=file 에 들어있는 값 가져오기
	// uploadFile : jsp의 input 타입의 name과 맞춰준 것이다
	// multiple로 넘어 온다면 MultipartFile[]로 받는다
	@PostMapping("/uploadform")
	public void uloadFilePost(MultipartFile[] uploadFile) {
		log.info("upload 요청");
		
		// 업로드 폴더 지정
		String uploadPath = "c:\\upload";
		
		for(MultipartFile f : uploadFile) {
			log.info("파일명 : "+f.getOriginalFilename());
			log.info("파일 크기 : "+f.getSize());
			
			try {
				File save = new File(uploadPath, f.getOriginalFilename());
				//파일저장
				f.transferTo(save);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * //다운로드 //이미지, 텍스트, 비디오.... + 상태코드
	 * 
	 * @GetMapping(path = "/download", produces =
	 * MediaType.APPLICATION_OCTET_STREAM_VALUE) public ResponseEntity<Resource>
	 * downloadFile(String fileName){ log.info("다운로드 요청 - "+fileName);
	 * 
	 * Resource resource = new FileSystemResource("c:\\upload\\"+fileName); String
	 * resourceName = resource.getFilename();
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * 
	 * try { headers.add("Content-Disposition", "attachment;filename="+new
	 * String(resourceName.getBytes("utf-8"),"ISO-8859-1")); } catch
	 * (UnsupportedEncodingException e) { e.printStackTrace(); }
	 * 
	 * return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK); }
	 */
	
}
