package com.spring.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.command.NoticeModifyCommand;
import com.spring.command.NoticeRegistCommand;
import com.spring.command.SearchCriteria;
import com.spring.dto.NoticeVO;
import com.spring.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/main")
	public String main()throws Exception{
		String url = "notice/main.open";
		return url;
	}
	
	@RequestMapping("/list")
	public String list(SearchCriteria cri, Model model)throws SQLException{
		
		String url="notice/list.open";
		
		Map<String,Object> dataMap = noticeService.getNoticeList(cri);
		
		model.addAttribute("dataMap",dataMap);
		
		return url;
		
	}
	
	@RequestMapping("/registForm")
	public String registForm(){
		String url = "notice/regist.open";
		return url;
	}
	
	@RequestMapping("/regist")
	public String regist(NoticeRegistCommand regReq,HttpServletRequest request) throws Exception{
		String url = "notice/regist_success";
		
		NoticeVO notice = regReq.toNoticeVO();
		notice.setTitle((String)request.getAttribute("XSStitle"));
		
		noticeService.regist(notice);
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int nno,
							   @RequestParam(defaultValue="") String from,
							   ModelAndView mnv ) throws SQLException{
		String url="notice/detail.open";
		
		NoticeVO notice = null;
		
		if(from.equals("modify")) {
			notice = noticeService.getNoticeForModify(nno);
		}else {
			notice = noticeService.getNotice(nno);
		}
		
		mnv.addObject("notice",notice);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int nno,ModelAndView mnv) throws Exception{
		String url="notice/modify.open";
		
		NoticeVO notice = noticeService.getNoticeForModify(nno);
		
		mnv.addObject("notice",notice);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPost(NoticeModifyCommand modifyReq,HttpServletRequest request)throws Exception{
		String url = "redirect:/notice/detail.do?from=modify&nno="+request.getParameter("nno");
		
		NoticeVO notice = modifyReq.toNoticeVO();		
		notice.setTitle((String)request.getAttribute("XSStitle"));
		
		noticeService.modify(notice);
		
		return url;
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(int nno) throws Exception{
		String url="notice/remove_success";
			
		noticeService.remove(nno);		
		
		return url;
	}
	
	
	
}










