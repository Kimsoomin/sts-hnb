package com.hnb.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hnb.global.Constants;
import com.hnb.global.DatabaseFactory;
import com.hnb.global.Vendor;

@Repository
public class MemberDAOImpl implements MemberDAO{
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	/*private List<MemberVO> list = new ArrayList<MemberVO>();*/
	private MemberVO member = new MemberVO();
	
	
	private static MemberDAO instance = new MemberDAOImpl();
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAOImpl() {
		con = DatabaseFactory.getDatabase(Vendor.ORACLE, Constants.ORACLE_ID, Constants.ORACLE_PASSWORD)
				.getConnection();
	}
	
	
	// C : 추가
	@Override
	public int insert(MemberVO member) {
		int result = 0;
		System.out.println("DAO impl로 들어옴");
		try {
		String sql = "INSERT INTO MEMBER (ID,PASSWORD,NAME,BIRTH,GENDER,PHONE,ADDR,EMAIL,REGDATE) VALUES (?,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddr());
			pstmt.setString(8, member.getEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("에러발생"+e);
			e.printStackTrace();
		}
		System.out.println("DAOImpl 회원가입 결과:"+result);
		return result;
	}
	
	
	
	// R : 조회 - 전체회원 조회
	@Override
	public List<MemberVO> selectAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			stmt = con.createStatement(); // 쿼리를 실행하는 객체
			rs = stmt.executeQuery("select * from member");
			while (rs.next()) {
				MemberVO temp = new MemberVO(); 
				temp.setId(rs.getString("id"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setBirth(rs.getString("birth"));
				temp.setAddr(rs.getString("addr"));
				temp.setGender(rs.getString("gender"));
				temp.setEmail(rs.getString("email"));
				temp.setPhone(rs.getString("phone"));
				temp.setRegdate(rs.getString("regdate"));
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// R - 임의의 값으로 조회
	@Override
	public List<MemberVO> selectSomeBy(String column, String keyword) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			String sql = "select * from member where"+column+" = "+keyword;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberVO temp = new MemberVO();
				temp.setId(rs.getString("id"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setBirth(rs.getString("birth"));
				temp.setAddr(rs.getString("addr"));
				temp.setGender(rs.getString("gender"));
				temp.setEmail(rs.getString("email"));
				temp.setPhone(rs.getString("phone"));
				temp.setRegdate(rs.getString("regdate"));
				list.add(temp); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	// R - id로 조회
	@Override
	public MemberVO selectOneBy(String id) {
		MemberVO temp = new MemberVO();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from member where id = '"+id+"'");
			while (rs.next()) {
				temp.setId(rs.getString("id"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setBirth(rs.getString("birth"));
				temp.setAddr(rs.getString("addr"));
				temp.setGender(rs.getString("gender"));
				temp.setEmail(rs.getString("email"));
				temp.setPhone(rs.getString("phone"));
				temp.setRegdate(rs.getString("regdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DAOImpl 조회된 ID는"+temp.getId());
		return temp;
	}
	// R - 총 회원수 조회
	@Override
	public int count() {
		int temp = 0;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select count(*) as count from member");
			while (rs.next()) {
				temp = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	
	// R - 로그인
	@Override
	public MemberVO login(String id, String password) {
		MemberVO loginMember = null;
		loginMember = this.selectOneBy(id);
		if (loginMember.getId() == null) {
			return null;
		} 
		if (loginMember.getPassword().equals(password)) {
			return loginMember;
		}else{
			return null;
		}
	}
	
	
	
	// U : 수정(회원정보수정)
	@Override
	public int update(MemberVO member) {
		int result = 0;
		try {
			String sql = "update member set password = ?, addr = ?, phone = ?, email = ? "
					+ "where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getAddr());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DAOImpl : DB 수정 후 결과 리턴값 :"+result);
		return result;
	}
	
	
	// D : 회원 탈퇴
	@Override
	public int delete(String id) {
		int result = 0;
		try {
			String sql = "delete from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
