package com.example.demo.domain;

public class CommentVO {
	private long cno;
	private long bno;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
	public long getCno() {
		return cno;
	}
	public void setCno(long cno) {
		this.cno = cno;
	}
	public long getBno() {
		return bno;
	}
	public void setBno(long bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegAt() {
		return regAt;
	}
	public void setRegAt(String regAt) {
		this.regAt = regAt;
	}
	public String getModAt() {
		return modAt;
	}
	public void setModAt(String modAt) {
		this.modAt = modAt;
	}
	public CommentVO(long cno, long bno, String writer, String content, String regAt, String modAt) {

		this.cno = cno;
		this.bno = bno;
		this.writer = writer;
		this.content = content;
		this.regAt = regAt;
		this.modAt = modAt;
	}
	public CommentVO() {
	
	}
	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", writer=" + writer + ", content=" + content + ", regAt="
				+ regAt + ", modAt=" + modAt + "]";
	}
	
	
}
