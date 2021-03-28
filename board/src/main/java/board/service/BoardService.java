package board.service;

import java.util.List;
import java.util.Map;

import board.bean.BoardPaging;
import board.bean.BoardTableDTO;

public interface BoardService {
	
	//글 등록
	//public void boardWrite(Map<String, String> map);
	public void boardWrite(BoardTableDTO boardTableDTO);
	//글목록
	public List<BoardTableDTO> getBoardList(String pg, String viewNum);
	//글목록페이지에서 쓰는 페이징 메소드
	public BoardPaging boardPaging(String pg, String viewNum);
	//글 상세보기내용
	public BoardTableDTO getBoard(String seq);
	//조회수증가
	public void hitUpdate(String seq);
	//게시판 검색
	public List<BoardTableDTO> getBoardListSearch(Map<String, String> map);
	//게시판 검색에 쓰는 페이징
	public BoardPaging boardPaging(Map<String, String> map);

}
