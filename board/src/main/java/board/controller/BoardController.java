package board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="board")
public class BoardController {
	
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(Model model) {
		model.addAttribute("display", "/boardPage/boardList.jsp");
		return "/index";
		}

}
