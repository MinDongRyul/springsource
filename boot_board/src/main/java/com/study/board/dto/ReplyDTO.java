package com.study.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDTO {
	private int rno;           //댓글 번호
	private int bno;           //원본글 번호
	private String reply;      //댓글 내용
	private String replyer;    //댓글 작성자
	private Date replydate;    //댓글 최초 날짜
	private Date updatedate;   //댓글 수정 날짜
}
