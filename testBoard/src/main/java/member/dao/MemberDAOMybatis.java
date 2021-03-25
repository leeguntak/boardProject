package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;
import member.service.MemberService;

@Repository
@Transactional
public class MemberDAOMybatis implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입
	@Override
	public int signUp(MemberDTO memberDTO) {
		return sqlSession.insert("boardMemberSQL.signUp", memberDTO);
	}
	//로그인
	@Override
	public MemberDTO login(Map<String, String> map) {
		return sqlSession.selectOne("boardMemberSQL.login", map);
	}

}
