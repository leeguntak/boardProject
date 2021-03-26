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
		System.out.println("서비스 접근");
		MemberDTO memberDTO = memberDAO.login(map);
		System.out.println("dao갔다옴");
		System.out.println(memberDTO);
		if(memberDTO == null) {
			return "fail";
		}else {
			session.setAttribute("mem_name", memberDTO.getMem_name());
			session.setAttribute("mem_id", map.get("mem_id"));
			return "success";
		}
	}
	

}
