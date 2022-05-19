package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.dto.Criteria;
import com.study.dto.ReplyDTO;
import com.study.dto.ReplyPageDTO;
import com.study.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // return값이 데이터 그 자체로 돌려주기위해 설정
@RequestMapping("/replies/*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	// 댓글삽입 - /replies/new + post + body(댓글내용-json)
	// 성공시 success + 200 , 실패시 fail + 500
	// produces : 내가 보내는 타입을 설정해서 보내 주는 것
	// consumes : 내가 받아서 처리할 컨텐트 타입  
	@PostMapping(path = "/new")
	public ResponseEntity<String> create(@RequestBody ReplyDTO insertDto) {
		
		log.info("댓글 요청 "+insertDto);
		
		return service.replyInsert(insertDto) ? 
		new ResponseEntity<String>("success", HttpStatus.OK) : 
		new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	// 특정 댓글 가져오기 - /replies/rno + GET
	// 성공시 ReplyDTO + 200
	@GetMapping(path = "/{rno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyDTO> read(@PathVariable("rno") int rno){
		
		log.info("특정 댓글 요청 "+rno);
		
		ReplyDTO readDto = service.replySelect(rno);
		
		return new ResponseEntity<ReplyDTO>(readDto, HttpStatus.OK);
	}
	
	// 특정 댓글 수정 - /replies/rno + put + body - json
	// 성공시 success + 200 , 실패시 fail + 500
	// updateDto에 rno가 안들어있는걸 생각해서 set처리를 해줘야한다
	
	//putmapping을 requestmapping로 줄 때 사용 
	//@RequestMapping(path = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	
	@PutMapping(path = "/{rno}")
	public ResponseEntity<String> update(@PathVariable("rno") int rno,@RequestBody ReplyDTO updateDto){
		
		log.info("특정 댓글 수정 요청 "+rno+" "+updateDto);
		
		updateDto.setRno(rno);
		
		return service.replyUpdate(updateDto) ? 
		new ResponseEntity<String>("success", HttpStatus.OK) : 
		new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글삭제 : /replies/rno + delete
	// 성공시 success + 200 , 실패시 fail + 500
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		
		log.info("특정 댓글 삭제 요청 "+rno);
		
		return service.replyDelete(rno) ? 
		new ResponseEntity<String>("success", HttpStatus.OK) : 
		new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 리스트 가져오기 : /replies/pages/bno/page + get
	// 성공시 댓글 리스트
	// http://localhost:9090/replies/pages/456(원본 글번호)/1()
	@GetMapping("/pages/{bno}/{page}")
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno") int bno,@PathVariable("page") int page){
		
		log.info("댓글 리스트 요청 "+bno+" "+page);
		
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<ReplyPageDTO>(service.getlist(cri, bno), HttpStatus.OK);
	}
	
}
