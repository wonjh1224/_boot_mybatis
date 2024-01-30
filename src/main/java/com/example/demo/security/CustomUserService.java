package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.controller.BoardController;
import com.example.demo.repository.MemberMapper;

public class CustomUserService implements UserDetailsService {

	@Autowired
	private MemberMapper mapper;
	
	private final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username DB에 설정되어 있는 email이 맞는지 체크
		// 인증하여 해당 객체를 AuthMember로 리턴
		MemberVO mvo = mapper.selectEmail(username);
		mvo.setAuthList(mapper.selectAuths(username));
		log.info("userDeatils {}", mvo);
		return new AuthMember(mvo);
	}

}
