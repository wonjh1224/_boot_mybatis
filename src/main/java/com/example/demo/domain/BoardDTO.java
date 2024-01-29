package com.example.demo.domain;

import java.util.List;

import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class BoardDTO {

	private BoardVO bvo;
	private List<FileVO> flist;
	
	public BoardVO getBvo() {
		return bvo;
	}
	public void setBvo(BoardVO bvo) {
		this.bvo = bvo;
	}
	public List<FileVO> getFlist() {
		return flist;
	}
	public void setFlist(List<FileVO> flist) {
		this.flist = flist;
	}
	@Override
	public String toString() {
		return "BoardDTO [bvo=" + bvo + ", flist=" + flist + "]";
	}
	public BoardDTO() {
		super();
	}
	public BoardDTO(BoardVO bvo, List<FileVO> flist) {
		super();
		this.bvo = bvo;
		this.flist = flist;
	}
	
	
	
}
