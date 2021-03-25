package member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.bean.MemberDTO;
import member.service.MemberService;

@Controller
@RequestMapping(value="member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	//회원가입화면
	@RequestMapping(value="/signUpForm", method=RequestMethod.GET)
	public String signUpForm(Model model) {
		model.addAttribute("display", "/memberPage/signUpForm.jsp");
		return "/index";
	}
	
	//회원가입
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String signUp(@ModelAttribute MemberDTO memberDTO,
						 Model model) {
		int signUpCheck = memberService.signUp(memberDTO);
		System.out.println(signUpCheck);
		
		model.addAttribute("signUpCheck", signUpCheck);
		model.addAttribute("display", "/memberPage/signUpForm.jsp");
		return "/index";
	}
	
	//로그인화면
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String loginForm() {
		return "/memberPage/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam Map<String, String> map,
						HttpSession session) {
		System.out.println("컨트롤러");
		return memberService.login(map, session);
	}

}