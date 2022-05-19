package com.study.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

//DTO에러 발생시 DTO파일을 삭제한 후 다시 붙여넣기 하여서 실행하기
@Data
public class BoardDTO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	private int replycnt; // 댓글 개수 조회하기
	
	//첨부파일 정보
	private List<AttachDTO> attachList;
}
