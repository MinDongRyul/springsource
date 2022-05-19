package com.study.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.dto.SampleDTO;

import lombok.extern.slf4j.Slf4j;

@RestController // 여기서 리턴하는 모든 것들은 데이터 그 자체
@Slf4j
public class BasicController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World";
	}
	
	//브라우저가 이해할 수 있는 형태로 보내줘야 한다. XML ↘ 
	@GetMapping(path = "/send.xml", produces = {MediaType.APPLICATION_XML_VALUE})
	public SampleDTO sendDto() {
		
		SampleDTO dto = new SampleDTO();
		
		dto.setName("hong");
		dto.setBno("123");
		dto.setAddr("서울");
		
		return dto; 
	}
	
	@GetMapping(path = "/send.json", produces = {MediaType.APPLICATION_JSON_VALUE})
	public SampleDTO sendDtoJson() {
		
		SampleDTO dto = new SampleDTO();
		
		dto.setName("hong");
		dto.setBno("123");
		dto.setAddr("서울");
		
		return dto; 
	}
	
	@GetMapping(path = "/send_list", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<SampleDTO> sendList() {
		
		List<SampleDTO> list = new ArrayList<SampleDTO>();
		
		for(int i = 0; i < 10; i++) {
			
			SampleDTO dto = new SampleDTO();
			
			dto.setBno("123"+i);
			dto.setName("hong"+i);
			dto.setAddr("서울"+i);
			
			list.add(dto);
		}
		
		return list; 
	}
	
	@GetMapping(path = "/get_map", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, SampleDTO> sendMap() {
		
		SampleDTO dto = new SampleDTO();
		
		dto.setName("hong");
		dto.setBno("123");
		dto.setAddr("서울");
		
		Map<String, SampleDTO> map = new HashMap<String, SampleDTO>();
		map.put("First", dto);
		
		return map; 
	}

	// ResponseEntity 타입 : Http 상태코드 + 데이터
	// ex) 상태코드 : 404, 500, 406, 200(OK)
	@GetMapping(path = "/check")
	public ResponseEntity<SampleDTO> check(Double height, Double weight){
		
		SampleDTO dto = new SampleDTO("123", height+"", weight+"");
		
		ResponseEntity<SampleDTO> result = null;
		
		if(height < 150) {
			//result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			result = new ResponseEntity<>(dto, HttpStatus.BAD_GATEWAY);
		}else {
			//result = ResponseEntity.status(HttpStatus.OK).body(dto);
			result = new ResponseEntity<>(dto, HttpStatus.OK);
		}
		return result;
	}
	
	// @PathVariable : REST 방식은 URL이 가지고 있는 값을 정보로 사용하는 경우가 많음
	//                 URL 파라미터에 들어오는 값을 변수에 담을 수 있게 해줌
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid) {
		return new String[] {"category : "+cat, "productId : "+pid};
	}
	
	// @RequestBody : JSON데이터를 서버로 가져올 때 원하는 타입의 객체로 변환할 때 사용
	@PostMapping("/test1")
	// 넘길 때 DTO에서 생성한 변수명과 이름이 같아야 한다.
	public void test1(@RequestBody SampleDTO dto) {
		log.info("json 데이터 가져오기 "+dto);
	}
	
	
}














