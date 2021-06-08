package kr.or.ddit.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.dto.MenuVO;

public class TestMenuService {
	
	private MenuServiceImpl service;
	
	@Before
	public void init() {
		service = new MenuServiceImpl();
		//service.setMenuDAO(new MockMenuDAO());
	}
	
	@Test
	public void test()throws Exception{
		List<MenuVO> menuList = service.getSubMenuList("M010000");
		
		Assert.assertEquals(1, menuList.size());
		Assert.assertEquals("회원가입", menuList.get(0).getMname());
	}
}


