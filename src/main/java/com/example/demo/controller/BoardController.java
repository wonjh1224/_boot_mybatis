package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.BoardVO;
import com.example.demo.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService bsv;
	
	@GetMapping("register")
	public void register() {}
	
	@PostMapping("register")
	public String register(BoardVO bvo) {
		
		
		bsv.register(bvo);
		
		return "index";
		
	}
	
}
