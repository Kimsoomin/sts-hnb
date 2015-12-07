package com.hnb.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hnb.global.Constants;
import com.hnb.global.DatabaseFactory;
import com.hnb.global.Vendor;


public class MemberDAOImpl implements MemberDAO{
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<MemberVO> list = new ArrayList<MemberVO>();
	private MemberVO member = new MemberVO();
	
	private static MemberDAO instance = new MemberDAOImpl();
	public static MemberDAO getInstance(){
		return instance;
	}
	private MemberDAOImpl() {
		con = DatabaseFactory
				.getDatabase(Vendor.ORACLE, Constants.ORACLE_ID, Constants.ORACLE_PASSWORD)
				.getConnection();
	}
	public List<MemberVO> selectAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			String sql = "";
			stmt = con.createStatement(); // 쿼리를 실행하는 객체
			rs = stmt.executeQuery(sql);
		     
			while (rs.next()) {
				MemberVO temp = new MemberVO(); 
				temp.setUserid(rs.getString("userid"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setBirth(rs.getString("birth"));
				temp.setGender(rs.getString("gender"));
				temp.setPhone(rs.getString("phone"));
				temp.setProfile(rs.getString("profile"));
				temp.setRegdate(rs.getString("regdate"));
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insert(MemberVO o) {
		int result = 0;
		try {
			String sql = "insert into member("
					+ "userid,password,name,birth,phone,email,gender,addr,profile,regdate"
					+ ") values(?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, o.getUserid());
			pstmt.setString(2, o.getPassword());
			pstmt.setString(3, o.getName());
			pstmt.setString(4, o.getBirth());
			pstmt.setString(5, o.getPhone());
			pstmt.setString(6, o.getEmail());
			pstmt.setString(7, o.getGender());
			pstmt.setString(8, o.getAddr());
			pstmt.setString(9, o.getProfile());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DAO : 회원가입 결과  "+result);
		return result;
	}
	public int update(MemberVO member) {
		int result = 0;
		try {
			String sql = "update member set password = ?, addr = ?, phone = ?, email = ?"
					+ "where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getAddr());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getUserid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DAO : 오라클 변경후 리턴값 : "+result);
		return result;
	}

	public List<MemberVO> selectSomeBy(String s1,String s2) {
		try {
			String sql = "";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberVO temp = new MemberVO(); 
				temp.setUserid(rs.getString("userid"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setBirth(rs.getString("birth"));
				temp.setGender(rs.getString("gender"));
				temp.setPhone(rs.getString("phone"));
				temp.setProfile(rs.getString("profile"));
				temp.setRegdate(rs.getString("regdate"));
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public MemberVO selectOneBy(String key) {
		MemberVO temp = new MemberVO();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from member where userid = '"+key+"'");
			
			while (rs.next()) {

				temp.setUserid(rs.getString("userid"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setBirth(rs.getString("birth"));
				temp.setGender(rs.getString("gender"));
				temp.setPhone(rs.getString("phone"));
				temp.setProfile(rs.getString("profile"));
				temp.setRegdate(rs.getString("regdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("홍길동 명세DAO "+temp.getName());
		return temp;
	}

	@Override
	public int count() {
		int temp = 0;
		try {
			String sql = "";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				temp = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int delete(String key) {
		int result = 0;
		try {
			String sql = "delete from member where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("삭제 성공 !");
		return result;
	}
	public MemberVO login(String id, String pass){
		MemberVO loginMember = new MemberVO();
		loginMember = this.selectOneBy(id);
		System.out.println("로그인DAO 이름출력 : "+loginMember.getName());
		if (loginMember.getUserid() == null) {
			return null;
		} 
		if (loginMember.getPassword().equals(pass)) {
			return loginMember;
		}else{
			return null;
		}
	}
}
