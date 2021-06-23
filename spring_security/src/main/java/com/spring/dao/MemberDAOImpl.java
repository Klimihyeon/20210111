package com.spring.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.spring.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	private SqlSession session;
	public void setSqlSession(SqlSession session) {
		this.session=session;
	}
	
	@Override
	public MemberVO selectMemberById( String id) throws SQLException {
		MemberVO member=session.selectOne("Member-Mapper.selectMemberById",id);			
		return member;
	}


	@Override
	public void insertMember( MemberVO member) throws SQLException {
		session.update("Member-Mapper.insertMember",member);
		
	}

	@Override
	public void updateMember( MemberVO member) throws SQLException {
		session.update("Member-Mapper.updateMember",member);

	}

	@Override
	public void deleteMember( String id) throws SQLException {
		session.update("Member-Mapper.deleteMember",id);

	}

	@Override
	public void disabledMember( String id) throws SQLException {
		session.update("Member-Mapper.disabledMember",id);

	}

	@Override
	public void enabledMember( String id) throws SQLException {
		session.update("Member-Mapper.enabledMember",id);

	}
}










