package board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardPaging;
import board.bean.BoardTableDTO;
import board.service.BoardService;

@Controller
@RequestMapping(value="board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//게시판목록
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(@RequestParam(required=false, defaultValue="1") String pg,
							Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/boardPage/boardList.jsp");
		return "/index";
	}
	
	//글쓰기페이지
	@RequestMapping(value="/boardWriteForm", method=RequestMethod.GET)
	public String boardWriteForm(Model model) {
		model.addAttribute("display", "/boardPage/boardWriteForm.jsp");
		return "/index";
	}
	
	//글등록
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	@ResponseBody
	public void boardWrite(@RequestParam Map<String, String> map) {
		boardService.boardWrite(map);
	}
	
	//글목록 불러오기
	@RequestMapping(value="/getBoardList", method=RequestMethod.POST)
	public ModelAndView getBoardList(@RequestParam(required=false, defaultValue="1") String pg,
									 HttpSession session,
									 HttpServletResponse response) {
		List<BoardTableDTO> list = boardService.getBoardList(pg);
		//페이징처리
		BoardPaging boardPaging = boardService.boardPaging(pg);
		
		//세션으로 아이디 필요
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView");
		return mav;		
	}
	
	//글상세보기페이지
	@RequestMapping(value="/boardView", method=RequestMethod.GET)
	public String boardView(@RequestParam String seq,
							@RequestParam(required=false, defaultValue="1") String pg,
							Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/boardPage/boardView.jsp");
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
//		if(cookie != null) {
//			cookie.setMaxAge(0); //쿠키 삭제
//			cookie.setPath("/"); //모든 경로에서 삭제 되었음을 알림
//			response.addCookie(cookie); //쿠키 삭제된걸 클라이언트에게 보내주기.
//		}
		
		//아이디 세션

		ModelAndView mav = new ModelAndView();
		mav.addObject("boardTableDTO", boardTableDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	//게시판 검색 
	@RequestMapping(value="/getBoardListSearch", method=RequestMethod.POST)
	public ModelAndView getBoardListSearch(@RequestParam Map<String,String> map) {
		List<BoardTableDTO> list = boardService.getBoardListSearch(map); 
		
		//페이징 처리
		BoardPaging boardPaging = boardService.boardPaging(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", map.get("pg"));
		mav.addObject("list", list);
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	/*
	//글등록
	@RequestMapping(value="boardWrite", method=RequestMethod.POST)
	@ResponseBody
	public void boardWrite(@ModelAttribute BoardTableDTO boardTableDTO,
						   @RequestParam("img[]") List<MultipartFile> list) {
		
		System.out.println("list:"+list);
		
		String filePath = "D:\\Spring\\workspace\\board\\src\\main\\webapp\\storage";
		
		for(MultipartFile img : list) {
			System.out.println("사진이름:"+img.getOriginalFilename());
			System.out.println(list.size());
			String fileName = img.getOriginalFilename();
			File file = new File(filePath, fileName);

			//파일 복사
			try {
				FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("2 : "+fileName);
			boardTableDTO.setImage1(fileName);
			boardTableDTO.setImage2("");
			//DB
			boardService.boardWrite(boardTableDTO);
		}//for
	}
	*/

}
