package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dto.AttachDTO;
import com.study.dto.BoardDTO;
import com.study.dto.Criteria;
import com.study.mapper.AttachMapper;
import com.study.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return mapper.select(cri);
	}

	@Transactional
	@Override
	public void register(BoardDTO insertDto) {
		
		//새글 등록
		mapper.insert(insertDto);
		
		//첨부파일이 없다면 되돌려 보내기
		if(insertDto.getAttachList() == null || insertDto.getAttachList().size() <= 0) {
			return;
		}
		
		//첨부 파일 개수만큼 루프 돌기
		insertDto.getAttachList().forEach(attach -> {
			attach.setBno(insertDto.getBno());
			//첨부파일 삽입
			attachMapper.insert(attach);
		});
	}

	@Override
	public BoardDTO getRow(int bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean updateContent(BoardDTO updateDto) {
		return mapper.update(updateDto) == 1 ? true : false;
	}

	@Override
	public boolean deleteContent(int bno) {
		return mapper.delete(bno) == 1 ? true : false;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachDTO> attachList(int bno) {
		return attachMapper.list(bno);
	}

}
