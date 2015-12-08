package com.hnb.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	MovieDAOImpl dao;
	@Override	//영화등록
	public int register(MovieVO o) {
		return dao.insert(o);
	}
	@Override	//영화수정
	public int change(MovieVO o) {
		return dao.update(o);
	}
	@Override	//영화삭제
	public int remove(String filmNumber) {
		return dao.delete(filmNumber);
	}
	@Override	//영화제목으로 검색
	public MovieVO searchByName(String filmName) {
		return dao.selectNameBy(filmName);
	}
	@Override	//영화전체목록
	public List<MovieVO> getList() {
		return dao.selectAll();
	}
	@Override
	public List<MovieVO> getFilmNum() {
		return dao.selectChart();
	}
}
