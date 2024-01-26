package com.example.demo.handler;

import java.util.List;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;

public class PagingHandler {

	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int totalCount;
	private PagingVO pgvo;

	private List<CommentVO> cmtList;
	
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}


	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;

		this.endPage = (int) (Math.ceil(pgvo.getPageNo() / (double) pgvo.getQty())) * pgvo.getQty();
		this.startPage = endPage - 9;

		int realEndPage = (int) (Math.ceil(totalCount) / (double) pgvo.getQty());

		if (realEndPage < endPage) {
			endPage = realEndPage;
		}
		prev = startPage > 1;
		next = endPage < realEndPage;

	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}
	public List<CommentVO> getCmtList() {
		return cmtList;
	}
	
	public void setCmtList(List<CommentVO> cmtList) {
		this.cmtList = cmtList;
	}

	@Override
	public String toString() {
		return "PagingHandler [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", totalCount=" + totalCount + ", pgvo=" + pgvo + "]";
	}

}
