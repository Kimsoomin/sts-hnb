package com.hnb.admin;

public class AdminDAOImpl implements AdminDAO{
	private static AdminDAO instance = new AdminDAOImpl();
	private AdminDAOImpl() {}
	public static AdminDAO getInstance(){
		return instance;
	}
}
