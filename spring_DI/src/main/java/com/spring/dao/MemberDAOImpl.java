package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.command.Criteria;
import com.spring.command.SearchCriteria;
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
	public List<MemberVO> selectMemberList() throws SQLException {
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
		return memberList;
	}

	@Override
	public List<MemberVO> selectMemberList( Criteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList",null,rowBounds);
		return memberList;
		
	}

	@Override
	public List<MemberVO> selectMemberList( SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectSearchMemberList",cri,rowBounds);
		return memberList;
	}

	@Override
	public int selectMemberListCount( SearchCriteria cri) throws SQLException {
		int count=0;		
		count=session.selectOne("Member-Mapper.selectSearchMemberListCount",cri);
		return count;
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










