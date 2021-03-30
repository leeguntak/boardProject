package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardTableDTO;

public interface BoardDAO {

	//글등록
	//public void boardWrite(Map<String, String> map);
	public void boardWrite(BoardTableDTO boardTableDTO);
	//글목록 출력
	public List<BoardTableDTO> getBoardList(Map<String, Integer> map);
	public int getTotalA();
	//글상세보기
	public BoardTableDTO getBoard(String seq);
	//조회수 증가
	public void hitUpdate(String seq);
	//게시판 검색
	public List<BoardTableDTO> getBoardListSearch(Map<String, String> map);
	//게시판 검색에 쓰는 페이징
	public int getBoardSearchTotalA(Map<String, String> map);
	
	//엑셀출력
	public List<BoardTableDTO> selectAll(Map<String, Integer> map);
	
	//글삭제
	public void boardDelete(String seq);

}
