package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.FileHandler;
import com.example.demo.handler.PagingHandler;
import com.example.demo.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
	
	private final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService bsv;
	
	@Autowired
	private FileHandler fh;
	
	
	@GetMapping("register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, 
			@RequestParam(name="files", required = false) MultipartFile[] files) {
		
		log.info("@@bvo >> {}", bvo);
		List<FileVO> flist = null;
		if(files[0].getSize() > 0 || files != null) {
			flist = fh.uploadFiles(files);
		}
		
		bsv.register(new BoardDTO(bvo, flist));
		
		return "redirect:/";
		
	}
	
	@GetMapping("/list")
	public void list(Model m, PagingVO pgvo) {
		log.info(">>> pgvo >>> {}", pgvo);
		
		int totalCount = bsv.getTotalCount(pgvo);
		
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		
		List<BoardVO> list = bsv.getList(pgvo);
		
		m.addAttribute("list", list);
		m.addAttribute("ph", ph);

	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(@RequestParam("bno") long bno, Model m) {
		
		BoardDTO bdto = bsv.getDetail(bno);
		log.info(">>>>>>>>>>>> flist>>> "+bdto.getFlist());
		m.addAttribute("bdto", bdto);
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re) {
		long bno = bvo.getBno();
		
		int isMod = bsv.modify(bvo);
		
		re.addFlashAttribute("isMod", isMod);
		
		return "redirect:/board/detail?bno="+bno;
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") long bno, RedirectAttributes re) {
		int isDel = bsv.delete(bno);
		
		re.addFlashAttribute("isDel", isDel);
		
		return "redirect:/board/list";
	}
	
//	@GetMapping("/test")
//	public String test() {
//		for(int i=0; i<10; i++) {
//			BoardVO bvo = new BoardVO();
//			bvo.setTitle("title"+"["+i+"]");
//			bvo.setWriter("Writer"+"["+i+"]");
//			bvo.setContent("Content"+"["+i+"]");
//			bsv.register(bvo);
//		}
//		
//		return "redirect:/board/list";
//		
//	}
	
}
