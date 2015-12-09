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


public class ScheduleDAOImpl implements ScheduleDAO {

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<ScheduleVO> list = new ArrayList<ScheduleVO>();
	private ScheduleVO schedule = new ScheduleVO();
	
	private static ScheduleDAO instance = new ScheduleDAOImpl();
	public static ScheduleDAO getInstance(){
		return instance;
	}
	private ScheduleDAOImpl() {
		con = DatabaseFactory
				.getDatabase(Vendor.ORACLE, Constants.ORACLE_ID, Constants.ORACLE_PASSWORD)
				.getConnection();
	}
	
	@Override
	public int insert(ScheduleVO o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> selectMovieRateAll() {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct movie.film_name as moviename from schedule, movie where schedule.film_number = movie.film_number order by movie.film_name asc");
			while (rs.next()) {
				list.add(rs.getString("moviename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<String> selectMovieAscAll() {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct movie.film_name as moviename from schedule, movie where schedule.film_number = movie.film_number order by movie.film_name asc");
			while (rs.next()) {
				list.add(rs.getString("moviename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<String> selectTheaterAll() {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct theater_name from schedule order by theater_name asc");
			while (rs.next()) {
				list.add(rs.getString("theater_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<String> selectDateAll() {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct show_date from schedule order by show_date asc");
			while (rs.next()) {
				list.add(rs.getString("show_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public ScheduleVO selectOneBy(String filmNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countBy(String filmNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ScheduleVO o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String filmNumber) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<String> selectTheaterByM(String movie) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct schedule.theater_name as theatername from schedule, movie where schedule.film_number = movie.film_number and movie.film_name = '"+movie+"' and seat_status > 0 order by schedule.theater_name asc");
			while (rs.next()) {
				list.add(rs.getString("theatername"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<String> selectDateByM(String movie) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct schedule.show_date as showdate from schedule, movie where schedule.film_number = movie.film_number and movie.film_name = '"+movie+"' and seat_status > 0 order by schedule.show_date asc");
			while (rs.next()) {
				list.add(rs.getString("showdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectTheaterByMD(String movie, String date) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct schedule.theater_name as theatername from schedule, movie where schedule.film_number = movie.film_number and movie.film_name = '"+movie+"' and schedule.show_date = '"+date+"' and seat_status > 0 order by schedule.theater_name asc");
			while (rs.next()) {
				list.add(rs.getString("theatername"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectDateByMT(String movie, String theater) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct schedule.show_date as showdate from schedule, movie where schedule.film_number = movie.film_number and movie.film_name = '"+movie+"' and schedule.theater_name = '"+theater+"' and seat_status > 0 order by schedule.show_date asc");
			while (rs.next()) {
				list.add(rs.getString("showdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectMovieRateByTD(String theater, String date) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct movie.film_name as moviename from schedule, movie where schedule.film_number = movie.film_number and theater_name = '"+theater+"' and show_date = '"+date+"' and seat_status > 0 order by movie.film_name asc");
			while (rs.next()) {
				list.add(rs.getString("moviename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectMovieAscByTD(String theater, String date) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct movie.film_name as moviename from schedule, movie where schedule.film_number = movie.film_number and theater_name = '"+theater+"' and show_date = '"+date+"' and seat_status > 0 order by movie.film_name asc");
			while (rs.next()) {
				list.add(rs.getString("moviename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectMovieRateByT(String theater) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct movie.film_name as moviename from schedule, movie where schedule.film_number = movie.film_number and theater_name = '"+theater+"' and seat_status > 0 order by movie.film_name asc");
			while (rs.next()) {
				list.add(rs.getString("moviename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectMovieAscByT(String theater) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct movie.film_name as moviename from schedule, movie where schedule.film_number = movie.film_number and theater_name = '"+theater+"' and seat_status > 0 order by movie.film_name asc");
			while (rs.next()) {
				list.add(rs.getString("moviename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectDateByT(String theater) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct schedule.show_date as showdate from schedule, movie where schedule.theater_name = '"+theater+"' and seat_status > 0 order by schedule.show_date asc");
			while (rs.next()) {
				list.add(rs.getString("showdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectMovieRateByD(String date) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct movie.film_name as moviename from schedule, movie where schedule.film_number = movie.film_number and show_date = '"+date+"' and seat_status > 0 order by movie.film_name asc");
			while (rs.next()) {
				list.add(rs.getString("moviename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectMovieAscByD(String date) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct movie.film_name as moviename from schedule, movie where schedule.film_number = movie.film_number and show_date = '"+date+"' and seat_status > 0 order by movie.film_name asc");
			while (rs.next()) {
				list.add(rs.getString("moviename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectTheaterByD(String date) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct schedule.theater_name as theatername from schedule, movie where schedule.film_number = movie.film_number and schedule.show_date = '"+date+"' and seat_status > 0 order by schedule.theater_name asc");
			while (rs.next()) {
				list.add(rs.getString("theatername"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List selectTime(String movie, String theater, String date) {
		List<String> list = new ArrayList<String>();
		//String roomName="";
		String roomtime="";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct schedule.show_time as showtime, schedule.room_name as roomname from schedule, movie where schedule.film_number = movie.film_number and movie.film_name = '"+movie+"' and schedule.theater_name = '"+theater+"' and schedule.show_date = '"+date+"' and seat_status > 0 order by schedule.theater_name asc");
			while (rs.next()) {
				roomtime=rs.getString("roomname")+" "+rs.getString("showtime");
				list.add(roomtime);
				/*if (!roomName.equals(rs.getString("roomname"))) {
					list.add(rs.getString("roomname"));
				}
				list.add(rs.getString("showtime"));
				roomName=rs.getString("roomname");*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public String selectFilmNumber(String movie) {
		String filmNumber = "";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select film_number from movie where film_name = '"+movie+"'");
			while (rs.next()) {
				filmNumber = rs.getString("film_number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmNumber;
	}
	

}
