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
	public void boardWrite(BoardTableDTO boardTableDTO) {
		sqlSession.insert("boardSQL.boardWrite", boardTableDTO);
	}
	
	//글목록 출력
	@Override
	public List<BoardTableDTO> getBoardList(Map<String, Integer> map) {
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
	
	//조회수 증가
	@Override
	public void hitUpdate(String seq) {
		sqlSession.update("boardSQL.hitUpdate", Integer.parseInt(seq));		
	}
	//게시판 검색
	@Override
	public List<BoardTableDTO> getBoardListSearch(Map<String, String> map) {
		return sqlSession.selectList("boardSQL.getBoardListSearch", map);
	}
	//게시판 검색에 쓰는 페이징
	@Override
	public int getBoardSearchTotalA(Map<String, String> map) {
		return sqlSession.selectOne("boardSQL.getBoardSearchTotalA", map);
	}
	
	
	//엑셀출력
	@Override
	public List<BoardTableDTO> selectAll(Map<String, Integer> map) {
		return sqlSession.selectList("boardSQL.selectAll", map);
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
