package kr.or.ddit.handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.BoardService;

public class BoardRemoveHandler implements Handler {
	
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="board/remove_success";
		
		int bno=Integer.parseInt(request.getParameter("bno"));
		
		boardService.remove(bno);
		return url;
	}

}
