package kr.or.ddit.handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.BoardVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.BoardService;

public class BoardModifyFormHandler implements Handler{
	
	private BoardService boardService; //= BoardServiceImpl.getInstance();
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/board/modify";
		
		try{
			int bno = Integer.parseInt(request.getParameter("bno"));			
			
			BoardVO board=boardService.getBoardForModify(bno);
			
			request.setAttribute("board", board);
			
		}catch(Exception e){
			e.printStackTrace();			
		}
		
		return url;
	}

}
