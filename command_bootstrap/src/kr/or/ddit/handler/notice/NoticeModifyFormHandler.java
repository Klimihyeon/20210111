package kr.or.ddit.handler.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.NoticeService;

public class NoticeModifyFormHandler implements Handler {
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="notice/modify";
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		NoticeVO notice=noticeService.getNoticeForModify(nno);
		
		request.setAttribute("notice", notice);
		
		return url;
	}

}
