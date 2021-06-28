package com.spring.scheduler;

import java.io.File;
import java.util.List;

import com.spring.service.BoardService;
import com.spring.service.NoticeService;
import com.spring.service.PdsService;

public class SummernoteImageRemoveScheduler {
	
	private NoticeService noticeService;
	private BoardService boardService;
	private PdsService pdsService;
	private String filePath;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void fileRemove()throws Exception {
		boolean existFile = false;
		
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		if(files!=null) {
			for(File file : files) {
				existFile = false;
				
				existFile = existFile ||noticeService.findFileInContent(file.getName())!=null; 
				existFile = existFile ||boardService.findFileInContent(file.getName())!=null;
				existFile = existFile ||pdsService.findFileInContent(file.getName())!=null;
				
				
				if(existFile) {
					System.out.println(file.getName()+" 파일은 존재합니다.");
					continue;
				}else {
					System.out.println(file.getName()+" 파일은 존재하지 않습니다. 삭제합니다.");
					file.delete();
				}
			}
		}
	}
	
	
}










