package com.hnb.member;

import java.util.List;

public interface MemberDAO {
	//CRUD
	
		// C : 추가(회원가입)
		public int insert(MemberVO member);
		
		// R : 조회
		public List<MemberVO> selectAll();
		public List<MemberVO> selectSomeBy(String column,String keyword); //임의의 값으로 검색
		public MemberVO selectOneBy(String id); //아이디로 조회
		public int count(); //전체회원수 조회
		public MemberVO login(String id, String pass); //로그인
		
		// U : 업데이트
		public int update(MemberVO member);
		
		// D : 삭제 (회원탈퇴)
		public int delete(String id);
}
