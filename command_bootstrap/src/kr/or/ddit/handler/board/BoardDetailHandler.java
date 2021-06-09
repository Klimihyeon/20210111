package kr.or.ddit.handler.board;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.BoardVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.BoardService;

public class BoardDetailHandler implements Handler{
	
	private BoardService boardService;//=BoardServiceImpl.getInstance();
	public void setBoardService(BoardService boardService) {
		this.boardService=boardService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="board/detail";
		

		int bno=Integer.parseInt(request.getParameter("bno"));
		String from=request.getParameter("from");
		
		try {
			BoardVO board=null;
			if(from!=null && from.equals("modify")) {
				board = boardService.getBoardForModify(bno);
			}else {
				board = boardService.getBoard(bno);
			}
			
			request.setAttribute("board", board);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}

}
