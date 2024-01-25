package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CommentService;



@RestController
public class CommentController {
	
	private final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private CommentService csv;
	
}
