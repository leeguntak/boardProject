package member.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.bean.MemberDTO;

public interface MemberService {

	//회원가입
	public int signUp(MemberDTO memberDTO);
	//로그인
	public String login(Map<String, String> map, HttpSession session, HttpServletResponse response);

}
