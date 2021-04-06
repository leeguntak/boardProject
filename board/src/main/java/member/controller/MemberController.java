package member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import index.controller.IndexController;
import member.bean.MemberDTO;
import member.service.MemberService;

@Controller
@RequestMapping(value="member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	
	//회원가입화면
	@RequestMapping(value="/signUpForm", method=RequestMethod.GET)
	public String signUpForm(Model model) {
		logger.info("회원가입화면");
		model.addAttribute("display", "/jsp/login/signUpForm.jsp");
		return "/index";
	}
	
	//회원가입
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String signUp(@ModelAttribute MemberDTO memberDTO,
						 Model model) {
		int signUpCheck = memberService.signUp(memberDTO);
		System.out.println(signUpCheck);
		
		model.addAttribute("signUpCheck", signUpCheck);
		model.addAttribute("display", "/jsp/login/signUpForm.jsp");
		return "/index";
	}
	
	//로그인화면
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String loginForm() {
		return "/jsp/login/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam Map<String, String> map, HttpSession session, 
						HttpServletRequest request, HttpServletResponse response) {
		// login.jsp에서 아이디기억하기 name값(remember) 가져오기
		String user_check = request.getParameter("remember_userId");
		map.put("user_check", user_check);

		System.out.println("컨트롤러");
		return memberService.login(map, session, response);
	}

}
