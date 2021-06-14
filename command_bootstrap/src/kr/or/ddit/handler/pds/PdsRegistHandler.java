package kr.or.ddit.handler.pds;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.josephoconnell.html.HTMLInputFilter;

import kr.or.ddit.controller.FileUploadResolver;
import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.dto.PdsVO;
import kr.or.ddit.exception.NotMultipartFormDataException;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.PdsService;
import kr.or.ddit.utils.ExceptionLoggerHelper;
import kr.or.ddit.utils.GetUploadPath;
import kr.or.ddit.utils.MultipartHttpServletRequestParser;

public class PdsRegistHandler implements Handler{
	

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	
	// 1. 입력 : commons-fileupload.jar 패키지를 이용하여 FileItem  형태로 변화된  MultipartResolver 를 받아 PdsVO 를 완성함.
	// 업로드 파일 환경 설정
	final private int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	final private int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	final private int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/pds/regist_success";

		MultipartHttpServletRequestParser multi = null;
		List<AttachVO> attachList=null;
		
		try {
			multi = new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		
			// 실제 저장 경로를 설정.
			String uploadPath = GetUploadPath.getUploadPath("pds.upload");
			
			//파일 저장후 List<AttachVO>를 리턴..
			attachList = FileUploadResolver.fileUpload(multi.getFileItems("uploadFile"), uploadPath);
		} catch (NotMultipartFormDataException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, new FileUploadResolver());
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		PdsVO pds = new PdsVO();
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(multi.getParameter("title")));
		pds.setContent(multi.getParameter("content"));
		pds.setWriter(multi.getParameter("writer"));
		pds.setAttachList(attachList);
		
		try {
			pdsService.regist(pds);
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, pdsService);
			throw e;
		}
		
		return url;
	}

}
