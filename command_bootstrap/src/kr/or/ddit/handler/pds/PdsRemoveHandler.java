package kr.or.ddit.handler.pds;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.PdsService;

public class PdsRemoveHandler implements Handler {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "pds/remove_success";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		// pno에 대한 attachList 확보
		List<AttachVO> attachList = pdsService.getPds(pno).getAttachList();
		
		
		//파일삭제
		if(attachList != null) {
			for (AttachVO attach : attachList) {
				String storedFilePath = attach.getUploadPath() + File.separator
						+ attach.getFileName();
				File file = new File(storedFilePath);
				if (file.exists()) {
					file.delete();
				}

			}
		}
		
		//DB 내용 삭제
		pdsService.remove(pno);
		
		return url;
	}

}
