package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardTableDTO;

public interface BoardDAO {

	//글등록
	public void boardWrite(Map<String, String> map);
	//글목록 출력
	public List<BoardTableDTO> getBoardList(Map<String, Integer> map);
	public int getTotalA();
	//글상세보기
	public BoardTableDTO getBoard(String seq);

}
