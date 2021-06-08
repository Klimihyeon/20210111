package kr.or.ddit.handler.member;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;

public class MemberIdCheckHandler implements Handler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		String id=request.getParameter("id");
		
		MemberVO member=null;	
		
		try {
			member = memberService.getMember(id);
			
			if(member!=null) {
				PrintWriter out=response.getWriter();
				out.print("duplicated");
				out.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return url;
	}

}
