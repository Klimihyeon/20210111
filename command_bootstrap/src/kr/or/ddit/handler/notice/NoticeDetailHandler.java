package kr.or.ddit.handler.notice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.NoticeService;

public class NoticeDetailHandler implements Handler {
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String url="notice/detail";
		

		int nno=Integer.parseInt(request.getParameter("nno"));
		String from=request.getParameter("from");
		
		try {
			NoticeVO notice=null;
			if(from!=null && from.equals("modify")) {
				notice = noticeService.getNoticeForModify(nno);
			}else {
				 notice = noticeService.getNotice(nno);
			}
			
			request.setAttribute("notice", notice);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}

}
