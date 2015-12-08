package com.hnb.movie;

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
public class MovieDAOImpl implements MovieDAO{
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static MovieDAO instance = new MovieDAOImpl();
	public static MovieDAO getInstance(){
		return instance;
	}
	private MovieDAOImpl(){
		con = DatabaseFactory.getDatabase(Vendor.ORACLE, Constants.ORACLE_ID, Constants.ORACLE_PASSWORD).getConnection();
	}
	public List<MovieVO> selectAll() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		try {
			stmt = con.createStatement(); // 쿼리를 실행하는 객체
			rs = stmt.executeQuery("select * from movie");
			while (rs.next()) {
				MovieVO temp = new MovieVO(); 
				temp.setFilmNumber(rs.getString("film_Number"));
				temp.setFilmName(rs.getString("film_Name"));
				temp.setDirector(rs.getString("director"));
				temp.setActor(rs.getString("actor"));
				temp.setRate(rs.getString("rate"));
				temp.setRuntime(rs.getInt("runtime"));
				temp.setPrice(rs.getInt("price"));
				temp.setGenre(rs.getString("genre"));
				temp.setReleaseDate(rs.getDate("release_Date"));
				temp.setEndDate(rs.getDate("end_Date"));
				temp.setStory(rs.getString("story"));
				temp.setCut(rs.getString("cut"));
				temp.setTrailer(rs.getString("trailer"));
				temp.setCountry(rs.getString("country"));
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insert(MovieVO o) {
		int result = 0;
		try {
			String sql = "insert into  movie("
					+"film_number, film_name, director, actor, rate, runtime, price, genre, release_date, end_date, story, cut, trailer, country"
					+") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, o.getFilmNumber());
			pstmt.setString(2, o.getFilmName());
			pstmt.setString(3, o.getDirector());
			pstmt.setString(4, o.getActor());
			pstmt.setString(5, o.getRate());
			pstmt.setInt(6, o.getRuntime());
			pstmt.setInt(7, o.getPrice());
			pstmt.setString(8, o.getGenre());
			pstmt.setDate(9, o.getReleaseDate());
			pstmt.setDate(10, o.getEndDate());
			pstmt.setString(11, o.getStory());
			pstmt.setString(12, o.getCut());
			pstmt.setString(13, o.getTrailer());
			pstmt.setString(14, o.getCountry());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DAO : 영화 결과 = "+result);
		return result;
	}
	public int update(MovieVO o) {
		int result = 0;
		try {
			/*String sql = DML.update("movie", "?,?,?", "userid", o.getUserid());*/
			String sql = "update movie set film_number= ?, film_name=?, director=?, actor=?, rate=?, runtime=?, price=?, genre=?, release_date=?, end_date=?, story=?, cut=?, trailer=?, country=? "
					+"where film_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, o.getFilmNumber());
			pstmt.setString(2, o.getFilmName());
			pstmt.setString(3, o.getDirector());
			pstmt.setString(4, o.getActor());
			pstmt.setString(5, o.getRate());
			pstmt.setInt(6, o.getRuntime());
			pstmt.setInt(7, o.getPrice());
			pstmt.setString(8, o.getGenre());
			pstmt.setDate(9, o.getReleaseDate());
			pstmt.setDate(10, o.getEndDate());
			pstmt.setString(11, o.getStory());
			pstmt.setString(12, o.getCut());
			pstmt.setString(13, o.getTrailer());
			pstmt.setString(14, o.getCountry());
			pstmt.setString(15, o.getFilmNumber());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DAO : 오라클 변경후 리턴값 : "+result);
		return result;
		
	}

	public MovieVO selectNameBy(String key) {
		MovieVO temp = new MovieVO();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from movie where film_number = '"+key+"'");
			while (rs.next()) {
				temp.setFilmNumber(rs.getString("film_number"));
				temp.setFilmName(rs.getString("film_name"));
				temp.setDirector(rs.getString("director"));
				temp.setActor(rs.getString("actor"));
				temp.setRate(rs.getString("rate"));
				temp.setRuntime(rs.getInt("runtime"));
				temp.setPrice(rs.getInt("price"));
				temp.setGenre(rs.getString("genre"));
				temp.setReleaseDate(rs.getDate("release_Date"));
				temp.setEndDate(rs.getDate("end_Date"));
				temp.setStory(rs.getString("story"));
				temp.setCut(rs.getString("cut"));
				temp.setTrailer(rs.getString("trailer"));
				temp.setCountry(rs.getString("country"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("영화 DAO 이름검색 "+ temp.getFilmName());
		return temp;
	}


	public int delete(String key) {
		int result = 0;
		try {
			String sql = "delete from movie where film_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<MovieVO> selectChart() {
		List<MovieVO> ChartList = new ArrayList<MovieVO>();
		try {
			stmt = con.createStatement(); // 쿼리를 실행하는 객체
			rs = stmt.executeQuery("select film_number from movie");
			while (rs.next()) {
				MovieVO temp = new MovieVO(); 
				temp.setFilmNumber(rs.getString("film_Number"));
				
				ChartList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ChartList;
	}
}
