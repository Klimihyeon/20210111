package kr.or.ddit.handler.reply;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.command.ReplyModifyCommand;
import kr.or.ddit.dto.ReplyVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ReplyService;

public class ReplyModifyHandler implements Handler {
	
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService=replyService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		ObjectMapper mapper=new ObjectMapper();		
		ReplyModifyCommand replyReq 
			= mapper.readValue(request.getReader(), ReplyModifyCommand.class);
		
		ReplyVO reply = replyReq.toReplyVO();
		
		try {
			replyService.modifyReply(reply);			
			response.sendError(HttpServletResponse.SC_OK);
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();					
		}
		
		
		return url;
	}

}
