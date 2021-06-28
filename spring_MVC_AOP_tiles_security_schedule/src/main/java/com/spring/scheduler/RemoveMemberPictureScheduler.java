package com.spring.scheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;

public class RemoveMemberPictureScheduler {
	
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}

	private String picturePath;
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	public void removePicture()throws Exception{
		
			
		File dir = new File(picturePath);
		File[] files = dir.listFiles();
		
		List<String> pitureFiles = new ArrayList<String>();
		
		List<MemberVO> memberList = memberService.getMemberList();
		for(MemberVO member : memberList) {
			pitureFiles.add(member.getPicture());
		}
		
		
		if(files!=null) {
			for(File file : files) {
				if(!pitureFiles.contains(file.getName()))file.delete();
			}
		}
	}
}










