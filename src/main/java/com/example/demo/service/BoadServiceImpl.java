package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.repository.BoardMapper;
import com.example.demo.repository.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoadServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private FileMapper fmapper;

	@Transactional
	@Override
	public void register(BoardDTO boardDTO) {
		
		int isOk = mapper.insert(boardDTO.getBvo());
		if(isOk > 0 && boardDTO.getFlist().size() >0) {
			long bno = mapper.getBno();
			for(FileVO fvo : boardDTO.getFlist()) {
				fvo.setBno(bno);
				isOk *= fmapper.insertFile(fvo);
			}
		}
		
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return mapper.selectAll(pgvo);
	}

	@Override
	public BoardDTO getDetail(long bno) {
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(mapper.selectOne(bno));
		bdto.setFlist(fmapper.getFileList(bno));
		
		return bdto;
	}

	@Override
	public int modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		return mapper.update(bvo);
	}


	@Override
	public int delete(long bno) {
		return mapper.delete(bno);
		
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return mapper.getTotalCount(pgvo);
	}




	
}
