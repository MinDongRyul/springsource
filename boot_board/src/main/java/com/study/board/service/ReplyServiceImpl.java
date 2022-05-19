package com.study.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.board.dto.Criteria;
import com.study.board.dto.ReplyDTO;
import com.study.board.dto.ReplyPageDTO;
import com.study.board.mapper.BoardMapper;
import com.study.board.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public boolean replyInsert(ReplyDTO insertDto) {
		
		//원본글의 댓글 수 추가
		boardMapper.updateReplyCnt(insertDto.getBno(), 1);
		
		return mapper.insert(insertDto) == 1 ? true : false;
	}

	@Override
	public ReplyDTO replySelect(int rno) {
		return mapper.read(rno);
	}

	@Override
	public boolean replyUpdate(ReplyDTO updateDto) {
		return mapper.update(updateDto) == 1 ? true : false;
	}

	@Transactional
	@Override
	public boolean replyDelete(int rno) {
		
		//bno 알아내기
		ReplyDTO dto = mapper.read(rno);
		
		//원본글의 댓글 수 감소
		boardMapper.updateReplyCnt(dto.getBno(), -1);
		
		return mapper.delete(rno) == 1 ? true : false;
	}

	@Override
	public ReplyPageDTO getlist(Criteria cri, int bno) {
		//ReplyPageDTO(전체 개수, 게시물 목록)
		//return시 두개 리턴을 못하기에 ReplyPageDTO를 만들어서 return해줌
		return new ReplyPageDTO(mapper.getCountBno(bno), mapper.select(cri, bno));
	}

	
}
