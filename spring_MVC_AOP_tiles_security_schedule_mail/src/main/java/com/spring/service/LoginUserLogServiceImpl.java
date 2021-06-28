package com.spring.service;

import java.util.List;

import com.spring.dao.LoginUserLogDAO;
import com.spring.dto.LoginUserLogVO;

public class LoginUserLogServiceImpl implements LoginUserLogService{
	
	private LoginUserLogDAO logDAO;
	public void setLoginUserLogDAO(LoginUserLogDAO logDAO) {
		this.logDAO=logDAO;
	}
	
	@Override
	public void write(List<LoginUserLogVO> logList) throws Exception {
		
		logDAO.deleteLoginUserLog();
		
		for(LoginUserLogVO logVO : logList) {
			logDAO.insertLoginUserLog(logVO);
		}
	}


}
