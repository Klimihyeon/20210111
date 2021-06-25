package com.spring.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.spring.command.Criteria;
import com.spring.command.SearchCriteria;
import com.spring.dto.MemberVO;
import com.spring.exception.InvalidPasswordException;
import com.spring.exception.NotFoundIDException;

public interface MemberService {
	
	// 로그인
	void login(String id, String pwd) throws SQLException, NotFoundIDException, 
															InvalidPasswordException;
	
	// 회원정보조회
	MemberVO getMember(String id) throws SQLException;
	
	// 회원리스트조회
	List<MemberVO> getMemberList()throws SQLException;
	List<MemberVO> getMemberList(Criteria cri)throws SQLException;
	Map<String,Object> getMemberList(SearchCriteria cri)throws SQLException;
	
	//회원등록
	public void regist(MemberVO member) throws SQLException;
	
	// 회원수정		
	void modify(MemberVO member) throws SQLException;
	
	// 회원삭제
	void remove(String id) throws SQLException;

	// 회원정지
	void disabled(String id) throws SQLException;

	// 회원활성
	void enabled(String id) throws SQLException;
}









