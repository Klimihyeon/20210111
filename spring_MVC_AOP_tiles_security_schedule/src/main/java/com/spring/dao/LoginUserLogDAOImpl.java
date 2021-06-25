package com.spring.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.spring.dto.LoginUserLogVO;

public class LoginUserLogDAOImpl implements LoginUserLogDAO{
	
	private SqlSession session;
	public void setSqlsession(SqlSession session) {
		this.session = session;
	}

	@Override
	public void insertLoginUserLog(LoginUserLogVO logVO) throws SQLException {
		session.update("LoginUserLog-Mapper.insertLoginUserLog",logVO);
		
	}

	@Override
	public void deleteLoginUserLog() throws SQLException {
		session.update("LoginUserLog-Mapper.deleteLoginUserLog");
	}

}
