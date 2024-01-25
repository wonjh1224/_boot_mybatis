package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;

@Mapper
public interface BoardMapper {

	void insert(BoardVO bvo);

	List<BoardVO> selectAll(PagingVO pgvo);

	BoardVO selectOne(long bno);

	int update(BoardVO bvo);

	int delete(long bno);

	int getTotalCount(PagingVO pgvo);

}
