package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@GetMapping("/register")
	public void register() {
		log.info("게시판 쓰기 사이트 요청");
	}
	
	// post
	// 성공시 리스트작업
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
	@GetMapping("/remove")
	public String remove(int bno, Criteria cri, RedirectAttributes rttr) {
		log.info("게시물 삭제 요청 "+bno);
		log.info("게시물 삭제 요청-cri "+cri);
		
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
	
	//첨부파일 가져오기
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachDTO>> getAttachList(int bno){
		
		log.info("첨부 파일 "+bno);
		
		return new ResponseEntity<List<AttachDTO>>(service.attachList(bno), HttpStatus.OK);
	}
}
