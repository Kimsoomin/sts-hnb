package com.hnb.member;

import java.util.List;

public interface MemberService {
	/**
	 * DML -> pstmt.executeUpdate()
	 */
	// 회원가입
	public int join(MemberVO o);
	// 비번 변경
	public int change(MemberVO o);
	// 회원 탈퇴
	public int remove(String userid);
	/**
	 * DQL -> stmt.executeQuery()
	 */
	// 로그인
	public MemberVO login(String id, String pass);
	// 총회원수
	public int count();
	// 아이디 검색
	public MemberVO searchById(String id);
	// 키워드 검색(중복 허용)
	public List<MemberVO> searchBySearchword(String domain,String searchword);
	// 전체 회원 목록
	public List<MemberVO> getList();
}
