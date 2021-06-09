package kr.or.ddit.handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.XSSResolver;
import kr.or.ddit.dto.BoardVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.BoardService;

public class BoardRegistHandler implements Handler {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "board/regist_success";
	
		//String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		XSSResolver.parseXSS(request);
		
		BoardVO board = new BoardVO(); 
		board.setContent(content);
		board.setTitle((String)request.getAttribute("XSStitle"));
		board.setWriter(writer);
		
		boardService.regist(board);
		
		
		return url;
	}

}
