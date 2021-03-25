package member.dao;

import java.util.Map;

import member.bean.MemberDTO;

public interface MemberDAO {
	
	//회원가입
	public int signUp(MemberDTO memberDTO);
	//로그인
	public MemberDTO login(Map<String, String> map);

}
