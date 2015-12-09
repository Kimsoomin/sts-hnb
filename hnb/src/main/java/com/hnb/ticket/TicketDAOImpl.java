package com.hnb.ticket;

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

public class TicketDAOImpl implements TicketDAO {

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<TicketVO> list = new ArrayList<TicketVO>();
	private TicketVO ticket = new TicketVO();
	
	private static TicketDAO instance = new TicketDAOImpl();
	public static TicketDAO getInstance(){
		return instance;
	}
	private TicketDAOImpl() {
		con = DatabaseFactory
				.getDatabase(Vendor.ORACLE, Constants.ORACLE_ID, Constants.ORACLE_PASSWORD)
				.getConnection();
	}
	
	@Override
	public int insert(TicketVO ticket) {
		int result = 0;
		try {
			String sql = "insert into ticket values(?,?,?,?,?,?,sysdate,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ticket.getTicketNumber());
			pstmt.setString(2, ticket.getId());
			pstmt.setString(3, ticket.getFilmNumber());
			pstmt.setString(4, ticket.getTheaterName());
			pstmt.setString(5, ticket.getRoomName());
			pstmt.setString(6, ticket.getSeatNumber());
			pstmt.setInt(7, ticket.getAdult());
			pstmt.setInt(8, ticket.getOldMan());
			pstmt.setInt(9, ticket.getTeenager());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DAO 티켓정보생성 : "+result);
		return result;
	}

	@Override
	public List<TicketVO> selectAll() {
		List<TicketVO> list = new ArrayList<TicketVO>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from ticket");
			while (rs.next()) {
				TicketVO temp = new TicketVO(); 
				temp.setTicketNumber(rs.getString("ticket_number"));
				temp.setId(rs.getString("id"));
				temp.setFilmNumber(rs.getString("film_number"));
				temp.setTheaterName(rs.getString("theater_name"));
				temp.setRoomName(rs.getString("room_name"));
				temp.setSeatNumber(rs.getString("seat_number"));
				temp.setDate(rs.getString("date"));
				temp.setAdult(rs.getInt("adult"));
				temp.setOldMan(rs.getInt("old_man"));
				temp.setTeenager(rs.getInt("teenager"));
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TicketVO selectOneBy(String filmNumber) {
		TicketVO temp = new TicketVO();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from ticket where film_number = '"+filmNumber+"'");
			while (rs.next()) {
				temp.setTicketNumber(rs.getString("ticket_number"));
				temp.setId(rs.getString("id"));
				temp.setFilmNumber(rs.getString("film_number"));
				temp.setTheaterName(rs.getString("theater_name"));
				temp.setRoomName(rs.getString("room_name"));
				temp.setSeatNumber(rs.getString("seat_number"));
				temp.setDate(rs.getString("date"));
				temp.setAdult(rs.getInt("adult"));
				temp.setOldMan(rs.getInt("old_man"));
				temp.setTeenager(rs.getInt("teenager"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DAO 영화번호"+ temp.getFilmNumber());
		return temp;
	}

	@Override
	public int countBy(String filmNumber) {
		int temp = 0;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select count(*) as count from ticket where film_number = '"+filmNumber+"'");
			while (rs.next()) {
				temp = rs.getInt("count");	// 쿼리문에 as count
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int count() {
		int temp = 0;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select count(*) as count from ticket");
			while (rs.next()) {
				temp = rs.getInt("count");	// 쿼리문에 as count
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int update(TicketVO ticket) {
		int result = 0;
		/*try {
			String sql = "update ticket set password = ?, addr = ?, phone = ?, email = ? where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ticket.getPassword());
			pstmt.setString(2, ticket.getAddr());
			pstmt.setString(3, ticket.getPhone());
			pstmt.setString(4, ticket.getEmail());
			pstmt.setString(5, ticket.getUserid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DAO 오라클 변경후 리턴값 : "+result);*/
		return result;
	}

	@Override
	public int delete(String ticketNumber) {
		int result = 0;
		try {
			pstmt = con.prepareStatement("delete from ticket where ticket_number = ?");
			pstmt.setString(1, ticketNumber);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List selectRoom(String theater, String room) {
		List<Integer> temp = new ArrayList<Integer>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from room where theater_name = '"+theater+"' and room_name = '"+room+"'");
			while (rs.next()) {
				temp.add(rs.getInt("a"));
				temp.add(rs.getInt("b"));
				temp.add(rs.getInt("c"));
				temp.add(rs.getInt("d"));
				temp.add(rs.getInt("e"));
				temp.add(rs.getInt("f"));
				temp.add(rs.getInt("g"));
				temp.add(rs.getInt("h"));
				temp.add(rs.getInt("i"));
				temp.add(rs.getInt("j"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("좌석"+ temp);
		return temp;
	}

}
