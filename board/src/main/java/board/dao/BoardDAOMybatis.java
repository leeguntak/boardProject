package board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import board.bean.BoardTableDTO;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Repository
@Transactional
public class BoardDAOMybatis implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void boardWrite(Map<String, String> map) {
		sqlSession.insert("boardSQL.boardWrite", map);
	}
	//글목록 출력
	@Override
	public List<BoardTableDTO> getBoardList(Map<String, Integer> map) {
		System.out.println("dao도착");
		return sqlSession.selectList("boardSQL.getBoardList", map);
	}
	
	@Override
	public int getTotalA() {
		return sqlSession.selectOne("boardSQL.getTotalA");
	}
	//글상세보기
	@Override
	public BoardTableDTO getBoard(String seq) {
		return sqlSession.selectOne("boardSQL.getBoard", Integer.parseInt(seq));
	}
	
	/*
	//글등록
	@Override
	public void boardWrite(BoardTableDTO boardTableDTO) {
		System.out.println("dao진입");
		sqlSession.insert("boardSQL.boardWrite", boardTableDTO);
	}
	 */
	

}
