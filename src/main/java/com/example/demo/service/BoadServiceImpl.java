package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardVO;
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
	
}
