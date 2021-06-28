package com.spring.service;

import java.util.List;

import com.spring.dto.LoginUserLogVO;

public interface LoginUserLogService {
	
	public void write(List<LoginUserLogVO> logVO)throws Exception;
}
