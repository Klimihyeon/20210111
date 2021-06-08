package kr.or.ddit.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.dto.MenuVO;

public class TestOracleMyBatisSqlSessionFactory {

	private SqlSessionFactory factory= new OracleMyBatisSqlSessionFactory();
	private	SqlSession session;
	
	@Before
	public void openSession() {
	 session = factory.openSession();
	}
	
	@Test
	public void testSqlSession() throws Exception{
		Assert.assertNotNull(session.getConnection());
	}
	
	@Test
	public void testSQL() {
		MenuVO menu = session.selectOne("Menu-Mapper.selectMenuByMcode","M010100");
		Assert.assertEquals("회원목록", menu.getMname());
	}
	@After
	public void closeSession() {
		session.close();
	}
}
