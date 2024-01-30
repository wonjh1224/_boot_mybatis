package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.security.MemberVO;
import com.example.demo.service.MemberSerivce;

@RequestMapping("/member")
@Controller
public class MemberController {

	@Autowired
	private MemberSerivce msv;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info(">>> mvo >>> {}", mvo);
		mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
		
		int isOk = msv.insert(mvo);
		
		return isOk > 0 ? "/index" : "/member/register";
		
	}
	
	@GetMapping("/login")
	public void login() {}
	
}
