package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.repository.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoadServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;

	@Override
	public void register(BoardVO bvo) {
		mapper.insert(bvo);
		
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return mapper.selectAll(pgvo);
	}

	@Override
	public BoardVO getDetail(long bno) {
		// TODO Auto-generated method stub
		return mapper.selectOne(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		return mapper.update(bvo);
	}


	@Override
	public int delete(long bno) {
		return mapper.delete(bno);
		
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return mapper.getTotalCount(pgvo);
	}




	
}
