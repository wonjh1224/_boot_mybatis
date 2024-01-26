package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;

@Mapper
public interface CommentMapper {

	int insert(CommentVO cvo);

	List<CommentVO> selectAll(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

	int update(CommentVO cvo);

	int getTotalCount(long bno);

}
