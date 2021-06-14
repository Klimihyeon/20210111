package kr.or.ddit.handler.pds;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.dto.PdsVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.PdsService;
import kr.or.ddit.utils.MakeFileName;

public class PdsDetailHandler implements Handler{
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="pds/detail";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		String from = request.getParameter("from");
		
		
		try {
			PdsVO pds =null; 
			if(from!=null && from.equals("modify")) {
				pds = pdsService.getPds(pno);
			}else {
				pds = pdsService.read(pno);
			}
			
			List<AttachVO> renamedAttachList=
					MakeFileName.parseFileNameFromAttaches(pds.getAttachList(), "\\$\\$");
			pds.setAttachList(renamedAttachList);
			
			request.setAttribute("pds", pds);
			
		} catch (Exception e) {			
			e.printStackTrace();
			url=null;
		}		
		
		return url;
	}

}









