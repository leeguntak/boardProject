package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Repository
@Transactional
public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	//회원가입
	@Override
	public int signUp(MemberDTO memberDTO) {
		return sqlSession.insert("memberSQL.signUp", memberDTO);
	}
	//로그인
	@Override
	public MemberDTO login(Map<String, String> map) {
		System.out.println("dao도착");
		System.out.println("mem_id: "+map.get("mem_id") +", "+"mem_pwd: "+ map.get("mem_pwd"));
		/*
		 여기까지 데이터는 가져오는데 mapper에서 null을 반환 이유를 모르겠음
		 */
		return sqlSession.selectOne("memberSQL.login", map);
	}

}
