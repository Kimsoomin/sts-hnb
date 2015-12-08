package com.hnb.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hnb.member.MemberService;
import com.hnb.member.MemberServiceImpl;
@Service
public class AdminServiceImpl implements AdminService{
	private static AdminService instance = new AdminServiceImpl();
	MemberService memberService = MemberServiceImpl.getInstance();
	private AdminServiceImpl() {}
	public static AdminService getInstance(){
		return instance;
	}
	AdminDAO dao = AdminDAOImpl.getInstance();
	@Override
	public List getMemberList() {
		return memberService.getList();
	}
}
