package member.service;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	//회원가입
	@Override
	public int signUp(MemberDTO memberDTO) {
		return memberDAO.signUp(memberDTO);
	}
	
	//로그인
	@Override
	public String login(Map<String, String> map, HttpSession session, 
						HttpServletResponse response) {
		//System.out.println("서비스 도착, id: "+map.get("mem_id")+", pwd: "+map.get("mem_pwd"));
		MemberDTO memberDTO = memberDAO.login(map);
		System.out.println(memberDTO);
		if(memberDTO == null) {
			return "fail";
		}else {
			Cookie cookie = new Cookie("user_check", map.get("user_id"));
			
			if (map.get("user_id").equals("true")) {
				//아이디 저장 o
				response.addCookie(cookie);
			}else {
				//아이디 저장 x
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

			session.setAttribute("mem_name", memberDTO.getMem_name());
			session.setAttribute("mem_id", map.get("mem_id"));
			return "success";
		}
	}

	
	

}
