package board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardPaging;
import board.bean.BoardTableDTO;
import board.service.BoardService;
import index.controller.IndexController;

//@RestController 
//@RestController 는 @Controller 와 @ResponseBody 포함
@Controller
@RequestMapping(value="board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	private static final String FILEPATH = "D:\\Spring\\workspace\\board\\src\\main\\webapp\\storage";
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	

	@RequestMapping(value="/boardList", method=RequestMethod.GET) //requestMapping대신 getmapping을 사용하면서 method 안씀
	public String boardList(@RequestParam(required=false, defaultValue="1") String pg,
							@RequestParam(required=false, defaultValue="10") String viewNum,
							Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("viewNum", viewNum);
		model.addAttribute("display", "/jsp/board/boardList.jsp");
		return "/index";
	}
	
	//글목록 불러오기
	//@RequestMapping(value="/getBoardList", method=RequestMethod.POST)
	@GetMapping(value="/getBoardList")
	public ModelAndView getBoardList(@RequestParam(required=false, defaultValue="1") String pg,
									 @RequestParam(required=false, defaultValue="10") String viewNum,
									 HttpSession session,
									 HttpServletRequest request,
									 HttpServletResponse response) {
		List<BoardTableDTO> list = boardService.getBoardList(pg, viewNum);
		//페이징처리
		BoardPaging boardPaging = boardService.boardPaging(pg, viewNum);
		
		//세션으로 아이디 필요
		logger.info("info 로그"+request);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("viewNum", viewNum);
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView");
		return mav;		
	}
	
	
	//게시판 검색 
	//@RequestMapping(value="/getBoardListSearch", method=RequestMethod.POST)
	@GetMapping(value="/getBoardListSearch")
		public ModelAndView getBoardListSearch(@RequestParam Map<String,String> map) {
			List<BoardTableDTO> list = boardService.getBoardListSearch(map);
			//map에 담기는 것들pg, keyword, searchType, viewNum
			
			//페이징 처리
			BoardPaging boardPaging = boardService.boardPaging(map);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("pg", map.get("pg"));
			mav.addObject("list", list);
			mav.addObject("boardPaging", boardPaging);
			mav.setViewName("jsonView");
			return mav;
		}
	
		
	//====================================================================
	
	
	//글쓰기페이지
	@RequestMapping(value="/boardWriteForm", method=RequestMethod.GET)
	public String boardWriteForm(Model model) {
		model.addAttribute("display", "/jsp/board/boardWriteForm.jsp");
		return "/index";
	}
	
	
	//글등록
//	@RequestMapping(value="boardWrite", method=RequestMethod.POST)
//	@ResponseBody
	@PostMapping(value="boardWrite")
	public void boardWrite(@ModelAttribute BoardTableDTO boardTableDTO,
							@RequestParam MultipartFile file) {

		String fileName = file.getOriginalFilename(); //파일의 원래이름을 변수에 담기
		File file1 = new File(FILEPATH, fileName);//상단에 파일경로를 FILEPATH로 선언
		
		//파일 복사
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(file1));
			//16진수로 변한 파일이름을 원래 이름으로 복사
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boardTableDTO.setFile1(fileName);
		//dto에 원래이름 담기
		//DB
		boardService.boardWrite(boardTableDTO);
		//DB에 파일이름 저장
	}
	
	
	//====================================================================
	
	
	//글상세보기페이지
	@RequestMapping(value="/boardView", method=RequestMethod.GET)
	public String boardView(@RequestParam String seq,
							@RequestParam(required=false, defaultValue="1") String pg,
							Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/jsp/board/boardView.jsp");
		return "/index";
	}
	
	
	//글 불러오기
	@RequestMapping(value="/getBoard", method=RequestMethod.POST)
	public ModelAndView getBoard(@RequestParam String seq,								
								 @CookieValue(value="memHit", required=false) Cookie cookie,
								 HttpServletResponse response,
								 HttpSession session) {
		BoardTableDTO boardTableDTO = boardService.getBoard(seq);
		
		boardService.hitUpdate(seq); //조회수 증가
		if(cookie != null) {
			cookie.setMaxAge(0); //쿠키 삭제
			cookie.setPath("/"); //모든 경로에서 삭제 되었음을 알림
			response.addCookie(cookie); //쿠키 삭제된걸 클라이언트에게 보내주기.
		}
		
		//아이디 세션 필요

		ModelAndView mav = new ModelAndView();
		mav.addObject("boardTableDTO", boardTableDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	//글 삭제
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public ModelAndView boardDelete(@RequestParam String seq) {
		
		boardService.boardDelete(seq);
		
		ModelAndView mav = new ModelAndView("redirect:/board/boardList");
		return mav;
	}
	
	
//	//파일 다운로드
//	@RequestMapping(value="/fileDownload", method=RequestMethod.GET)
//	public ModelAndView download(@RequestParam String fileName) {
//		String originalFileName = fileName; //원래 파일이름 변수에 담기
//		String fullPath = FILEPATH + "/" + originalFileName; //파일경로
//		File file = new File(fullPath);
//		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("downloadFile", file);
//		mav.setViewName("downloadView");
//		return mav;
//	}
	
	
	
	
	
	
	
	
	
}
