package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.BoardController;
import com.example.demo.repository.MemberMapper;
import com.example.demo.security.MemberVO;

@Service
public class MemberServiceImpl implements MemberSerivce{

	@Autowired
	private MemberMapper mapper;
	
	private final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Override
	public int insert(MemberVO mvo) {
		// TODO Auto-generated method stub
		int isOk = mapper.insert(mvo);
		
		return mapper.insertAuthinit(mvo.getEmail());
	}
	
}
