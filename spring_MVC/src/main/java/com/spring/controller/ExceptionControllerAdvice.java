package com.spring.controller;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;
import com.spring.utils.ExceptionLoggerHelper;


@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
		
	@ExceptionHandler(SQLException.class)
	public String sqlExceptionPage(Exception e,Model model,HttpSession session){
		String url="error/sqlException2";
		
		logger.info(e.toString());
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		model.addAttribute("exception",e);
		model.addAttribute("user",loginUser!=null ? loginUser.getName()+"님":"");
		
		
		return url;		
	}
	
	
	@ExceptionHandler(Exception.class)
	public void ExceptionPage(Exception e,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info(e.toString());		
		
		ExceptionLoggerHelper.write(request, e, new Object());
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();		
		out.println("<script>");
		out.println("alert('서버장애가 발생했습니다.\\n관리자에게 연락바랍니다.');");
		out.println("history.go(-1);");
		out.println("</script>");
		
		out.close();
	}
	
	@Autowired
	private MemberService memberService;

	@ModelAttribute("admin")
	public MemberVO getAdmin() throws Exception{
		MemberVO member = memberService.getMember("mimi");
		return member;
	}
}




