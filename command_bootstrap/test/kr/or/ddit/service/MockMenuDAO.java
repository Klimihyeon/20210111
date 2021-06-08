package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dao.MenuDAO;
import kr.or.ddit.dto.MenuVO;

public class MockMenuDAO implements MenuDAO {

	@Override
	public List<MenuVO> selectMainMenu(SqlSession session) throws SQLException {
		List<MenuVO> menuList = null;
		
		if (session.getConnection()==null) throw new SQLException();
		
		
		MenuVO menu = new MenuVO();
		menu.setMname("회원가입");
		
		menuList= new ArrayList<MenuVO>();
		menuList.add(menu);
		
		return menuList;
	}

	@Override
	public List<MenuVO> selectSubMenu(SqlSession session, String mCode) throws SQLException {
		List<MenuVO> menuList = null;
		
		if (session.getConnection()==null) throw new SQLException();
		
		MenuVO menu = new MenuVO();
		menu.setMname("회원가입");
		
		menuList= new ArrayList<MenuVO>();
		menuList.add(menu);
		
		if(!mCode.equals("M010000")) menuList=null;
		
		return menuList;
	}

	@Override
	public MenuVO selectMenuByMcode(SqlSession session, String mCode) throws SQLException {
		MenuVO menu =null;
		
		if (session.getConnection()==null) throw new SQLException();
		
		if(mCode.equals("M010200")) {
			menu=new MenuVO();
			menu.setMname("회원가입");
		}
		
		return menu;
	}

	@Override
	public MenuVO selectMenuByMname(SqlSession session, String mName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
