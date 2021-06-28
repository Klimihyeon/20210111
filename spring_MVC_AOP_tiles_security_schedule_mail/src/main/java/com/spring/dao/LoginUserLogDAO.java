package com.spring.dao;

import java.sql.SQLException;

import com.spring.dto.LoginUserLogVO;

public interface LoginUserLogDAO {
	
	public void insertLoginUserLog(LoginUserLogVO logVO)throws SQLException;
	public void deleteLoginUserLog()throws SQLException;

}
