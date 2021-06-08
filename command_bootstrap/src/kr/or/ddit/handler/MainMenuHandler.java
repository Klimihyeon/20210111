package kr.or.ddit.handler;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.MenuVO;
import kr.or.ddit.service.MenuService;

public class MainMenuHandler implements Handler {

	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="common/indexPage";
		
		String mCode = request.getParameter("mCode");
		
		if(mCode ==null) mCode="M000000";
		
		try {
			List<MenuVO> menuList = menuService.getMainMenuList();			
			MenuVO menu = menuService.getMenuByMcode(mCode);
			
			request.setAttribute("menuList",menuList);
			request.setAttribute("menu",menu);
		} catch (SQLException e) {			
			e.printStackTrace();
			url=null;
		}
		return url;
	}

}
