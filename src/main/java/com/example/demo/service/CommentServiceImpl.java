package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.repository.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper mapper;

	@Override
	public int register(CommentVO cvo) {
		// TODO Auto-generated method stub
		return mapper.insert(cvo);
	}

//	@Override
//	public List<CommentVO> getList(long bno) {
//		// TODO Auto-generated method stub
//		return mapper.selectAll(bno);
//	}
	
	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// Controller에서 처리해도 되지만, 처리 속도가 더 빨라짐
		//totalCount
		int totalCount = mapper.getTotalCount(bno);
		//list
		List<CommentVO> list = mapper.selectAll(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}

	@Override
	public int edit(CommentVO cvo) {
		// TODO Auto-generated method stub
		return mapper.update(cvo);
	}


}
