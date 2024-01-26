package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.service.CommentService;


@RequestMapping("/comment/*")
@RestController
public class CommentController {
	
	private final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private CommentService csv;
	
	@PostMapping("/post")
	@ResponseBody
	public String register(@RequestBody CommentVO cvo) {
		log.info(">>> cvo >>> {}" ,cvo);
		
		int isReg = csv.register(cvo);
		
		return isReg > 0 ? "1" : "0";
	}
	
	@GetMapping("/{bno}/{page}")
	@ResponseBody
	public PagingHandler list(@PathVariable("bno") long bno, @PathVariable("page") int page){
		log.info(">>> bno >>> {}", bno, ">>> page >>> {}", page);
		//List<CommentVO> / PagingHandler
		//비동기는 한 객체만 전송가능.

		PagingVO pgvo = new PagingVO(page, 5); // 한 페이지에 5개씩 표시
		PagingHandler ph = csv.getList(bno, pgvo);
		return ph;
	}
	
	@PutMapping("/edit")
	@ResponseBody
	public String edit(@RequestBody CommentVO cvo) {
		log.info(">>> cvo >>> {}", cvo);
		int isOk = csv.edit(cvo);
		return isOk > 0 ? "1" : "0";
	}
	
}
