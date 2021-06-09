package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ReplyVO;

public interface ReplyDAO {
	
	
	void insertReply(SqlSession session,ReplyVO reply)throws SQLException;
	void updateReply(SqlSession session,ReplyVO reply)throws SQLException;
	void deleteReply(SqlSession session,int rno)throws SQLException;
	
	int selectReplySeqNextValue(SqlSession session)throws SQLException;
	List<ReplyVO> selectReplyListPage(SqlSession session,int bno,SearchCriteria cri)throws SQLException;
	int countReply(SqlSession session,int bno) throws SQLException;
	
}
