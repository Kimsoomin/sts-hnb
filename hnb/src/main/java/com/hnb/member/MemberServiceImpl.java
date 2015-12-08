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
	/**
	 * DML
	 */
	// 회원가입
	@Override
	public int join(MemberVO member) {
		return dao.insert(member);
	}
	//비번변경
	@Override
	public int change(MemberVO o) {
		return dao.update(o);
	}
	 // 회원탈퇴
	@Override
	public int remove(String userid) {
		return dao.delete(userid);
	}
	/**
	 * DQL
	 */
	// 로그인
	@Override
	public MemberVO login(String id, String pass) {
		return dao.login(id, pass);
	}
	//전체 회원수 
	@Override
	public int count() {
		return dao.count();
	}
	//ID로 회원검색
	@Override
	public MemberVO searchById(String id) {
		return dao.selectOneBy(id);
	}
	// 검색어로 검색
	@Override
	public List<MemberVO> searchBySearchword(String domain,String searchword) {
		return dao.selectSomeBy(domain, searchword);
	}
	// 전체 회원목록 
	@Override
	public List<MemberVO> getList() {
		return dao.selectAll();
	}
}
