package kr.or.ddit.handler.pds;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.controller.FileUploadResolver;
import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.dto.PdsVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.PdsService;
import kr.or.ddit.utils.ExceptionLoggerHelper;
import kr.or.ddit.utils.GetUploadPath;
import kr.or.ddit.utils.MultipartHttpServletRequestParser;

public class PdsModifyHandler implements Handler {

	private PdsService pdsService;

	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "pds/modify_success";

		PdsVO pds = null;
		try {
			pds = modifyFile(request, response);
			pdsService.modify(pds);

		} catch (SQLException e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, pdsService);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			request.setAttribute("pds", pds);
		}

		return url;
	}

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB

	private PdsVO modifyFile(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, Exception {
		PdsVO pds = null;

		MultipartHttpServletRequestParser multi = new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD,
				MAX_FILE_SIZE, MAX_REQUEST_SIZE);

		FileItem[] fileItems = multi.getFileItems("uploadFile");

		// 추가된 파일 저장
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		List<AttachVO> attachList = FileUploadResolver.fileUpload(fileItems, uploadPath);

		pds = new PdsVO();
		pds.setPno(Integer.parseInt(multi.getParameter("pno")));
		pds.setTitle(multi.getParameter("title"));
		pds.setWriter(multi.getParameter("writer"));
		pds.setContent(multi.getParameter("content"));
		pds.setAttachList(attachList);

		// 파일 삭제 및 DB삭제
		String[] deleteFiles = multi.getParameterValues("deleteFile");
		if (deleteFiles != null && deleteFiles.length > 0) {
			for (String anoStr : deleteFiles) {
				int ano = Integer.parseInt(anoStr);
				AttachVO attach = pdsService.getAttachByAno(ano);

				String filePath = attach.getUploadPath() + File.separator + attach.getFileName();
				File targetFile = new File(filePath);

				if (targetFile.exists()) {
					targetFile.delete(); // 파일 삭제
				}
				pdsService.removeAttachByAno(ano); // DB 삭제
			}

		}

		return pds;
	}

}
