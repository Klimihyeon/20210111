package com.spring.scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.spring.dto.LoginUserLogVO;
import com.spring.service.LoginUserLogService;

public class LoginUserLogTaskScheduler {
	
	
	private LoginUserLogService logService;
	public void setLoginUserLogService(LoginUserLogService logService) {
		this.logService=logService;
	}
	
	public void logScheduler() throws Exception{
		String filePath="d:\\log\\login_user_log.csv";
		
		FileReader reader = new FileReader(filePath);		
		BufferedReader in=new BufferedReader(reader);
		
		String textLine=null;
		List<LoginUserLogVO> logList=new ArrayList<LoginUserLogVO>();
		while((textLine=in.readLine())!=null) {
			
			String[] logData = textLine.replace("[login:user]", "").split(",");
			LoginUserLogVO logVO=new LoginUserLogVO();
			logVO.setId(logData[0]);
			logVO.setPhone(logData[1]);
			logVO.setEmail(logData[2]);
			logVO.setIpAddress(logData[3]);
			logVO.setIndate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(logData[4]));
			
			logList.add(logVO);		
			
		}
		
		logService.write(logList);
		
		in.close();
		
		
	}
}









