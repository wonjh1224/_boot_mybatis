package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;

public interface BoardService {

	void register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	int modify(BoardVO bvo);

	BoardVO getDetail(long bno);

	int delete(long bno);

	int getTotalCount(PagingVO pgvo);

}
