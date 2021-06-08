package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.NoticeDAO;
import kr.or.ddit.dao.NoticeDAOImpl;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.mybatis.OracleMyBatisSqlSessionFactory;

public class TestNoticeServiceImpl {
	
	private NoticeDAO noticeDAO = new NoticeDAOImpl();	
	private SqlSessionFactory factory = new OracleMyBatisSqlSessionFactory();	
	
	private NoticeService service;
	
	{
		service=new NoticeServiceImpl();
		((NoticeServiceImpl)service).setNoticeDAO(noticeDAO);
		((NoticeServiceImpl)service).setSqlSessionFactory(factory);
	}
	

	
	@Test
	public void testGetNoticeList()throws SQLException{
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setPerPageNum(10);
		cri.setSearchType("w");
		cri.setKeyword("mimi");
		
		
		Map<String,Object> dataMap = service.getNoticeList(cri);
		
		PageMaker pageMaker = (PageMaker)dataMap.get("pageMaker");
		List<NoticeVO> noticeList = (List<NoticeVO>)dataMap.get("noticeList");
		
		NoticeVO notice = noticeList.get(0);
		int page = pageMaker.getCri().getPage();
		
		Assert.assertEquals("mimi", notice.getWriter());
		Assert.assertEquals(9, noticeList.size());
		Assert.assertEquals(1, page);
		
	}
	
	
}








