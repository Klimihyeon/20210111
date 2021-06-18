package com.spring.command;

import java.util.Date;

import com.spring.dto.BoardVO;

public class BoardRegistCommand {
	
	private String writer;
	private String title;
	private String content;
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "BoardRegistCommand [writer=" + writer + ", title=" + title + ", content=" + content + "]";
	}
	
	public BoardVO toBoardVO() {
		BoardVO board = new BoardVO();
		board.setContent(this.content);
		board.setRegDate(new Date());
		board.setTitle(this.title);
		board.setWriter(this.writer);
		
		return board;
	}
}
