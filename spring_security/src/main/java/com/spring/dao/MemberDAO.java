package com.spring.dao;

import java.sql.SQLException;

import com.spring.dto.MemberVO;

public interface MemberDAO {
	
	
	// 회원정보 조회
	MemberVO selectMemberById(String id) throws SQLException;
	
	
	// 회원 추가
	public void insertMember( MemberVO member) throws SQLException;

	// 회원 수정
	public void updateMember( MemberVO member) throws SQLException;
	
	// 회원정보 삭제
	void deleteMember(String id) throws SQLException;

	// 회원정지
	void disabledMember(String id) throws SQLException;

	// 회원 활성화
	void enabledMember(String id) throws SQLException;
}








