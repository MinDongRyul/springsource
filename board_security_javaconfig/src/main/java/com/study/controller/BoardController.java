package com.study.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.AttachDTO;
import com.study.dto.BoardDTO;
import com.study.dto.Criteria;
import com.study.dto.PageDTO;
import com.study.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// /board/list 컨트롤러 작성
	@GetMapping("/list")
	public void list(Model model,@ModelAttribute("cri") Criteria cri) {
		log.info("전체 리스트 요청 "+cri);
		//리스트 실행할때 마다 pageNum과 amount를 받아서 cri에 저장
		List<BoardDTO> list = service.getList(cri);
		
		//전체 게시물 개수
		int total = service.getTotalCnt(cri);
		
		//새로운 pageNum과 amount를 pageDto에 담아서 넘겨줌
		model.addAttribute("pageDto", new PageDTO(cri, total));
		model.addAttribute("list", list);
	}
	
	// /board/register 컨트롤러 작성
	// isAuthenticated() : 인증된 사용자인 경우 true
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void register() {
		log.info("게시판 쓰기 사이트 요청");
	}
	
	// post
	// 성공시 리스트작업
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto, Criteria cri, RedirectAttributes rttr) {
		log.info("게시판 등록 요청 "+insertDto);
		log.info("게시판 등록 요청-cri "+cri);
		
		service.register(insertDto);
		
		//성공시
		//insert실행시 bno값을 넘겨 받음 =>
		//list.jsp에게 result에 bno값을 넘겨서 보냄
		rttr.addFlashAttribute("result", insertDto.getBno());
		
		//주소줄에 딸려보내줌
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";	
	}
	
	// /board/read + bno 컨트롤러 작성
	// bno에 해당하는 게시물 읽어온 후 read.jsp보여주기
	// 그냥 jsp로 찾으러 가는 경우로는 @ModelAttribute("cri") Criteria cri 만 해주면 알아서 값 유지
	@GetMapping("/read")
	public void readGet(int bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("게시물 보여주기 요청 혹은 수정 페이지 요청 "+bno);
		log.info("게시물 요청 - cri "+cri);
		
		//read를 들어가면서 댓글 아래에 다른 게시물이 보여지도록 해보는 실험
		List<BoardDTO> list = service.getList(cri);
		
		//전체 게시물 개수
		int total = service.getTotalCnt(cri);
		model.addAttribute("list", list);
		
		//새로운 pageNum과 amount를 pageDto에 담아서 넘겨줌
		model.addAttribute("pageDto", new PageDTO(cri, total));
		
		//read를 들어가면서 댓글 아래에 다른 게시물이 보여지도록 해보는 실험
		
		BoardDTO dto = service.getRow(bno);
		model.addAttribute("dto", dto);
	}
	
	@GetMapping("/modify")
	public void modifyGet(int bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("게시물 보여주기 요청 혹은 수정 페이지 요청 "+bno);
		log.info("게시물 요청 - cri "+cri);
		
		BoardDTO dto = service.getRow(bno);
		model.addAttribute("dto", dto);
	}
	
	/*
	 * //modify 버튼 클릭시 operForm이동
	 * 
	 * @GetMapping("/modify") public void modify(int bno, Model model) {
	 * log.info("수정 폼 요청");
	 * 
	 * BoardDTO dto = service.getRow(bno); model.addAttribute("dto", dto); }
	 */
	
	// 성공시 : board/read + 매핑 : post => 수정 성공 시 수정된 게시물 보여주기
	// 원래 있던 cri값이 유지가 된다.
	// "redirect:/board/read"로 다시 컨트롤러로 들어가는 경우는 rttr을 사용한다
	// 게시글 수정 
	// 첨부 파일 수정 하는 경우 1) 기존 첨부파일 삭제 2)첨부파일 추가 
	//                    => bno에 해당되는 첨부파일 삭제 => 현재 첨부파일 목록 삽입
	// 잘못 업로드 된 파일 삭제(첨부파일 저장 폴더와 db가 일치하지 않는 상황)
	//                    => 어제 날짜로 등록된 첨부 파일의 목록 구하기
	//                       어제 업로드 되었지만 데이터베이스에는 존재하지 않는 파일 찾기 => 폴더와 DB 비교 후 삭제
	//                    => spring batch : 대용량의 데이터를 주기적으로 읽고 쓰는 작업에 주로 사용
	//                       스케쥴러를 이용해 작업 가능
	
	@PreAuthorize("principal.username == #updateDto.writer") // 작성자와 로그인 한 사람이 같은지 확인
	@PostMapping("/modify")
	public String modifyGet(BoardDTO updateDto,@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("수정 완료 요청 "+updateDto);
		log.info("수정 완료 요청 - cri "+cri);
		
		service.updateContent(updateDto);
		
		//수정 성공시
		rttr.addAttribute("bno", updateDto.getBno());
		//주소에 딸려 보내줌
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/read";
	}
	
	// /board/remove + bno
	// 성공시 list 보여주기
	// 게시물 삭제 시 : 댓글, 첨부파일, 게시물 삭제\
	@PreAuthorize("principal.username == #writer")
	@GetMapping("/remove")
	public String remove(int bno, String writer, Criteria cri, RedirectAttributes rttr) {
		log.info("게시물 삭제 요청 "+bno);
		log.info("게시물 삭제 요청-cri "+cri);
		
		//서버 폴더에 저장한 첨부 파일 삭제
		List<AttachDTO> attachList = service.attachList(bno); //첨부파일 리스트
		deleteFiles(attachList);
		
		//DB작업 : 게시글 삭제 + 첨부파일 삭제 + 댓글 삭제
		service.deleteContent(bno);
		
		//주소줄에 딸려보내는 방식
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		//세션을 이용하는 방식
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/board/list";
	}
	
	//read.js에서 첨부파일 영역에 넣어줄 때 사용
	//첨부파일 가져오기
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachDTO>> getAttachList(int bno){
		
		log.info("첨부 파일 "+bno);
		
		return new ResponseEntity<List<AttachDTO>>(service.attachList(bno), HttpStatus.OK);
	}
	
	//서버 첨부파일 삭제
	public void deleteFiles(List<AttachDTO> attachList) {
		log.info("폴더 내 첨부파일 삭제");
		
		if(attachList == null || attachList.size() <= 0) {
			return;
		}
		
		for(AttachDTO attach:attachList) {
			
			//파일들이 있는 경로
			Path path = Paths.get("c:\\upload\\",attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
		
			try {
				//일반파일, 원본이미지 삭제
				Files.deleteIfExists(path);
				
				//  Files.probeContentType(파일경로) : 확장자를 통해서 MIME타입을 판단
				
				//mime타입의 시작이 image라면 썸네일도 삭제하겠다 ex) image/jpg, image/gif
				if(Files.probeContentType(path).startsWith("image")) {
					Path thumb = Paths.get("c:\\upload\\",attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					//파일 삭제
					Files.delete(thumb);				
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
