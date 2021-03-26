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
		//1페이지당 10개씩
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<BoardTableDTO> list = boardDAO.getBoardList(map);
		return list;
	}

	@Override
	public BoardPaging boardPaging(String pg) {
		int totalA = boardDAO.getTotalA();//총글수
		
		boardPaging.setCurrentPage(Integer.parseInt(pg));
		boardPaging.setPageBlock(10);
		boardPaging.setPageSize(10);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
	}
	//글 상세보기내용
	@Override
	public BoardTableDTO getBoard(String seq) {
		return boardDAO.getBoard(seq);
	}
	//조회수 증가
	@Override
	public void hitUpdate(String seq) {
		boardDAO.hitUpdate(seq);
	}
	//게시판 검색
	@Override
	public List<BoardTableDTO> getBoardListSearch(Map<String, String> map) {
		int endNum = Integer.parseInt(map.get("pg"))*5;
		int startNum = endNum-4;
		
		//map에는 pg, searchType, keyword, startNum, endNum
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return boardDAO.getBoardListSearch(map);
	}
	//게시판 검색에 쓰는 페이징
	@Override
	public BoardPaging boardPaging(Map<String, String> map) {
		int totalA = boardDAO.getBoardSearchTotalA(map);
		//총글수 - searchType, keyword 가져가야함
		
		boardPaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		boardPaging.setPageBlock(10);
		boardPaging.setPageSize(10);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
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
