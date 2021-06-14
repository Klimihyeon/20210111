package kr.or.ddit.handler.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.handler.Handler;

public class PdsRegistFormHandler implements Handler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="pds/regist";
		return url;
	}

}
