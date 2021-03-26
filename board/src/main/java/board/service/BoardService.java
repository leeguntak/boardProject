package board.service;

import java.util.List;
import java.util.Map;

import board.bean.BoardPaging;
import board.bean.BoardTableDTO;

public interface BoardService {
	
	//글 등록
	public void boardWrite(Map<String, String> map);
	//글목록
	public List<BoardTableDTO> getBoardList(String pg);
	//글목록페이지에서 쓰는 페이징 메소드
	public BoardPaging boardPaging(String pg);
	//글 상세보기내용
	public BoardTableDTO getBoard(String seq);

}
