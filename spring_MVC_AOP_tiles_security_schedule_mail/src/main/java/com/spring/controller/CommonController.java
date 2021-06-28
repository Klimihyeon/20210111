package com.spring.controller;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MenuVO;
import com.spring.service.MemberService;
import com.spring.service.MenuService;

@Controller
public class CommonController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/common/loginForm",method=RequestMethod.GET)
	public String loginForm(@RequestParam(defaultValue="")String error,HttpServletResponse response)throws Exception{
		String url = "common/login";
		
		if(error.equals("1")) {
			response.setStatus(302);
		}
		return url;
	}
	
	@RequestMapping("/common/loginTimeOut")
	public void loginTimeOut(HttpServletRequest request, HttpServletResponse response)throws Exception{		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('세션이 만료되었습니다.\\n다시 로그인 하세요!');");
		out.println("location.href='"+request.getContextPath()+"';");
		out.println("</script>");
		out.close();
	}
	
	@RequestMapping("/common/loginExpired")
	public void loginExpired(HttpServletRequest request, HttpServletResponse response)throws Exception{
				
			
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('중복 로그인이 확인되었습니다.\\n다시 로그인하면 다른 장치에서 로그인은 취소됩니다.!');");
		out.println("location.href='"+request.getContextPath()+"';");
		out.println("</script>");
		out.close();
	}
	
	/*@RequestMapping(value="/common/login",method=RequestMethod.POST)
	public String loginPost(LoginCommand loginReq, HttpServletRequest request,
							RedirectAttributes rttr)throws Exception{
		String url="redirect:/index.do";			
		
		HttpSession session = request.getSession();
		
		try {
			memberService.login(loginReq.getId(), loginReq.getPwd());
			session.setAttribute("loginUser", memberService.getMember(loginReq.getId()));
			session.setMaxInactiveInterval(6*60);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (NotFoundIDException | InvalidPasswordException e) {			
			url="redirect:/common/login.do";
			rttr.addFlashAttribute("msg",e.getMessage());
		} 
		
		return url;
	}*/
	
	@RequestMapping("/index")
	public ModelAndView indexPage(ModelAndView mnv,
								  @RequestParam(defaultValue="M000000")String mCode, 
								  HttpServletRequest request, HttpServletResponse response) 
										  										throws Exception{
		String url = "common/indexPage.page";
		
		try {
			List<MenuVO> menuList = menuService.getMainMenuList();			
			MenuVO menu = menuService.getMenuByMcode(mCode);
			
			mnv.addObject("menuList",menuList);
			mnv.addObject("menu",menu);
		} catch (SQLException e) {			
			e.printStackTrace();
			throw e;
		}
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="/main")
	public String main() {
		String url="/common/main";
		return url;
	}
	
	@RequestMapping("/getMcode")
	@ResponseBody
	public ResponseEntity<MenuVO> getMcode(String mName)throws Exception{
		
		ResponseEntity<MenuVO> entity = null;
		
		try {
				MenuVO menu = menuService.getMenuByMname(mName);
				
				entity = new ResponseEntity<MenuVO>(menu, HttpStatus.OK) ;
		}catch(SQLException e) {
				entity = new ResponseEntity<MenuVO>(HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
	
		
		return entity;
	}
	
	@RequestMapping("/subMenu")
	@ResponseBody
	public ResponseEntity<List<MenuVO>> subMenu(String mCode)throws Exception{
		
		ResponseEntity<List<MenuVO>> entity = null;
		
		try {
				List<MenuVO> menuList = menuService.getSubMenuList(mCode);
				
				entity = new ResponseEntity<List<MenuVO>>(menuList, HttpStatus.OK) ;
		}catch(SQLException e) {
				entity = new ResponseEntity<List<MenuVO>>(HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
	
		
		return entity;
	}
	
	/*@RequestMapping(value="/common/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		String url="redirect:/";
		session.invalidate();
		
		return url;
	}*/
	@RequestMapping("/security/accessDenied")
	public String accessDenied(HttpServletResponse response) {
		String url="security/accessDenied";
		
		response.setStatus(302);
		
		return url;		
	}
}







