package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;

public interface BoardService {

	void register(BoardDTO boardDTO);

	List<BoardVO> getList(PagingVO pgvo);

	int modify(BoardVO bvo);

	BoardDTO getDetail(long bno);

	int delete(long bno);

	int getTotalCount(PagingVO pgvo);

}
