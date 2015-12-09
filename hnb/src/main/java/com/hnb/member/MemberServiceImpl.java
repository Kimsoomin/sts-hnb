package com.hnb.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl  implements MemberService{
	private static MemberService instance = new MemberServiceImpl();
	public static MemberService getInstance() {
		return instance;
	} 
	MemberDAO dao = MemberDAOImpl.getInstance();
	
	
	// 회원가입
	@Override
	public int join(MemberVO member) {
		return dao.insert(member);
	}
	
	// 전체 회원목록 조회
	@Override
	public List<MemberVO> getList() {
		return dao.selectAll();
	}
	
	// 임의의 검색어로 조회
	@Override
	public List<MemberVO> searchByKeyword(String column, String keyword) {
		return dao.selectSomeBy(column, keyword);
	}
	
	// ID로 회원검색
	@Override
	public MemberVO selectById(String id) {
		return dao.selectOneBy(id);
	}
	
	// 총 회원 수 검색
	@Override
	public int count() {
		return dao.count();
	}
	
	// 로그인
	@Override
	public MemberVO login(String id, String pass) {
		return dao.login(id, pass);
	}
	
	// 회원정보 변경
	@Override
	public int change(MemberVO member) {
		return dao.update(member);
	}
	
	// 회원탈퇴
	@Override
	public int remove(String id) {
		return dao.delete(id);
	}
}
