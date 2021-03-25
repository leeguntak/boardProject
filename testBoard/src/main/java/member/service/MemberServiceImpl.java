package member.service;

import java.util.Map;

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
	public String login(Map<String, String> map, HttpSession session) {
		MemberDTO memberDTO = memberDAO.login(map);
		System.out.println(memberDTO);
		
		if(memberDTO == null) {
			return "fail";
		}else {
			//세션
			session.setAttribute("mem_name", memberDTO.getMem_name());
			session.setAttribute("mem_id", map.get("id"));			
			return "success";
		}
	}

}
