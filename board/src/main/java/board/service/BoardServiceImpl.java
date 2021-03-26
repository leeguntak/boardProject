package board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardPaging;
import board.bean.BoardTableDTO;
import board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BoardPaging boardPaging;
	
	@Override
	public void boardWrite(Map<String, String> map) {
		//로그인 완성해서 세션으로 id,name을 mpa으로 넣어야 함
		boardDAO.boardWrite(map);
	}

	@Override
	public List<BoardTableDTO> getBoardList(String pg) {
		System.out.println("서비스도착");
		//1페이지당 10개씩
		int endNum = Integer.parseInt(pg)*5;
		int startNum = endNum-4;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<BoardTableDTO> list = boardDAO.getBoardList(map);
		if( list != null) {
			System.out.println("있음");
		}
		return list;
	}

	@Override
	public BoardPaging boardPaging(String pg) {
		int totalA = boardDAO.getTotalA();//총글수
		
		boardPaging.setCurrentPage(Integer.parseInt(pg));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
	}

	@Override
	public BoardTableDTO getBoard(String seq) {
		return boardDAO.getBoard(seq);
	}
	

	/*
	//글등록
	@Override
	public void boardWrite(BoardTableDTO boardTableDTO) {
		System.out.println("서비스 진입");
		boardDAO.boardWrite(boardTableDTO);
	}
	 */
}
