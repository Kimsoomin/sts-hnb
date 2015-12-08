package com.hnb.movie;

import java.util.List;

public interface MovieDAO {
	public List<MovieVO> selectAll();
	public List<MovieVO> selectChart();
	public int insert(MovieVO o);
	public int update(MovieVO o);
	public int delete(String filmNumber);
	public MovieVO selectNameBy(String movieName);
}
