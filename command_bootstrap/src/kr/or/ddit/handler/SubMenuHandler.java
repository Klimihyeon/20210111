package kr.or.ddit.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.JSONResolver;
import kr.or.ddit.dto.MenuVO;
import kr.or.ddit.service.MenuService;

public class SubMenuHandler implements Handler {


	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mCode = request.getParameter("mCode");
		List<MenuVO> subMenu =null;
		
		try {
			 subMenu=menuService.getSubMenuList(mCode);		
			
			JSONResolver.view(response, subMenu);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();			
		}
		return null;
	}

}
