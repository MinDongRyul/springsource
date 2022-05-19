package com.study.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReplyPageDTO {
	private int replyCnt;         //댓글 전체 갯수
	private List<ReplyDTO> list;  //댓글 목록
}
