package kr.or.ddit.handler.pds;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.PdsService;
import kr.or.ddit.utils.ExceptionLoggerHelper;

public class PdsListHandler implements Handler{
	
	private PdsService pdsService;
	public void setPdsService (PdsService pdsService) {
		this.pdsService=pdsService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="pds/list";

		String page=request.getParameter("page");
		String perPageNum=request.getParameter("perPageNum");	
		String searchType=request.getParameter("searchType");
		String keyword=request.getParameter("keyword");
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		
		
		try {
			Map<String,Object> dataMap=pdsService.getList(cri);			
			request.setAttribute("dataMap", dataMap);			
		} catch (SQLException e) {
			e.printStackTrace();
			//exception helper : 로그 기록
			ExceptionLoggerHelper.write(request, e, pdsService);
			throw e;
		}	
		
		return url;
	}

}







