package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.dao.MenuDAO;
import kr.or.ddit.dao.MenuDAOImpl;
import kr.or.ddit.dto.MenuVO;
import kr.or.ddit.mybatis.OracleMyBatisSqlSessionFactory;

public class MenuServiceImpl implements MenuService {
	

	private MenuDAO menuDAO;// = new MenuDAOImpl();
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	
	private SqlSessionFactory sqlSessionFactory;// = new OracleMyBatisSqlSessionFactory();
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	@Override
	public List<MenuVO> getMainMenuList() throws SQLException {
				
		List<MenuVO> menuList= null;
		SqlSession session = sqlSessionFactory.openSession(false);
		
		try {
			
			menuList =menuDAO.selectMainMenu(session);		
			
			session.commit();
		}catch(SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
			
		}finally {
			session.close();
		}
		
		
		return menuList;
	}

	@Override
	public List<MenuVO> getSubMenuList(String mCode) throws SQLException {
		List<MenuVO> menuList= null;
		
		SqlSession session = sqlSessionFactory.openSession();
		menuList =menuDAO.selectSubMenu(session, mCode);		
		session.close();
		
		return menuList;
	}

	@Override
	public MenuVO getMenuByMcode(String mCode) throws SQLException {
		MenuVO menu= null;
		
		SqlSession session = sqlSessionFactory.openSession();
		menu =menuDAO.selectMenuByMcode(session, mCode);
		session.close();
		
		return menu;
	}


	@Override
	public MenuVO getMenuByMname(String mName) throws SQLException {
		MenuVO menu= null;
		
		SqlSession session = sqlSessionFactory.openSession();
		menu =menuDAO.selectMenuByMname(session, mName);
		session.close();
		
		return menu;
	}

}







		
