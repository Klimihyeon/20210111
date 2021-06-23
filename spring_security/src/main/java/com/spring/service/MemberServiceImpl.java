package com.spring.service;

import java.sql.SQLException;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberVO;

public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}


	@Override
	public MemberVO getMember(String id) throws SQLException {

		MemberVO member = memberDAO.selectMemberById(id);
		return member;
	}

	
	@Override
	public void regist(MemberVO member) throws SQLException {

		memberDAO.insertMember(member);
	}

	@Override
	public void modify(MemberVO member) throws SQLException {

	}

	@Override
	public void remove(String id) throws SQLException {

		memberDAO.deleteMember(id);

	}

	@Override
	public void disabled(String id) throws SQLException {

		memberDAO.disabledMember(id);

	}

	@Override
	public void enabled(String id) throws SQLException {

		memberDAO.enabledMember(id);
	}

}
