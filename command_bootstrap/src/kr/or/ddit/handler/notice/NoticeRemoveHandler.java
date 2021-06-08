package kr.or.ddit.handler.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.NoticeService;

public class NoticeRemoveHandler implements Handler {
	

	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="notice/remove_success";
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		noticeService.remove(nno);
		
		return url;
	}

}
