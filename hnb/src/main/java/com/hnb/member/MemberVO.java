package com.hnb.member;

import java.io.Serializable;

public class MemberVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userid; // 아이디  =>hong => 'hong'
	private String password; // 비번
	private String name; // 회원이름
	private String birth; // 나이
	private String phone; // 전화번호
	private String email; // 이메일
	private String gender; // 성별
	private String addr; // 주소
	private String regdate; // 등록일
	private String profile; // 프로필사진
	public MemberVO() {}

	public MemberVO(String userid, String password, String name, 
			String birth, String phone, String email,
			String gender, String addr) {
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.addr = addr;
		this.profile = "default.png";
	}

	public String getUserid() {
		return userid;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getBirth() {
		return birth;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public String getAddr() {
		return addr;
	}

	public String getRegdate() {
		return regdate;
	}

	public String getProfile() {
		return profile;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public void setProfile(String profile) {
		this.profile = "default.png";
	}
	@Override
	public String toString() {
		return " 회원 [아이디=" + userid 
				+ ", 비밀번호=" + password 
				+ ", 이름=" + name 
				+ ", 생년=" + birth
				+ ", 전화번호=" + phone 
				+ ", 이메일=" + email 
				+ ", 성별=" + gender 
				+ ", 주소=" + addr 
				+ ", 등록일=" + regdate 
				+ ", 프로필 사진=" + profile + "]";
	}
}
