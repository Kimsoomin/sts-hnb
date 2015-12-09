package com.hnb.movie;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnb.main.MainController;

@Controller
@RequestMapping("/movie")
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired	
	MovieServiceImpl service;
	@Autowired
	MovieVO movie;
	
	@RequestMapping("/Movie")
	public String home(){
		logger.info("MovieController-home 진입");
		return "movie/movie";
	}
	@RequestMapping("/movie_info")
	public Model movieInfo(Model model){
		logger.info("MovieController-movie_info 진입");
		List<MovieVO> list = service.getList();
		model.addAttribute("movieList", list);
		logger.info("영화리스트 조회 결과: {}", list);
		return model;
	}
	@RequestMapping("/movie_name/{movieName}")
	public @ResponseBody MovieVO movieName(
			@PathVariable("movieName")String name,
			Model model
			){
		logger.info("MovieController-movie_name 진입");
		logger.info("영화 아이디: {}", name);
		movie = service.searchByName(name);
		logger.info("영화 제목: {}",movie);
		/* model.addAttribute("movie", movie);
		 * 객체 리턴할 때는(ajax 사용) model.addAttribute 사용 안 함 
		 * */	
		return movie;
	}
	@RequestMapping("/movie_Cut")
	public Model movieCut(String filmNumber,Model model){
		logger.info("MovieController-movie_Cut 진입");
		logger.info("무비컷 영화 아이디 : {}", filmNumber);
		movie = service.searchByName(filmNumber);
		String cut = movie.getCut();
		String[]arr = cut.split("/");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		model.addAttribute("arr", arr);
		return model;
	}
	@RequestMapping("/movie_Tra")
	public Model movieTra(String filmNumber,Model model){
		logger.info("MovieController-movie_Tra 진입");
		logger.info("테라의 영화 아이디 : {}", filmNumber);
		movie = service.searchByName(filmNumber);
		String tra = movie.getTrailer();
		System.out.println("getTrailer : "+ tra);
		String[]arrt = tra.split("/");
		logger.info("트레일러 : {}", arrt);
		model.addAttribute("arrt", arrt);
		return model;
	}
	@RequestMapping("/movie_Basic")
	public @ResponseBody MovieVO movieBasic(String filmNumber,Model model){
		logger.info("MovieController-movie_Basic 진입");
		logger.info("베이직 영화 아이디 : {}", filmNumber );
		movie = service.searchByName(filmNumber);
		logger.info("베이지 영화 제목: {}", movie.getFilmName());
		model.addAttribute("movie", movie);
		return movie;
	}
	@RequestMapping("/movieChart")
	public Model movie_Chart(String filmNumber,Model model){
		logger.info("MovieController-movie_Chart 진입");
		List<MovieVO> list = service.getList();
		model.addAttribute("modellist2",list);
		
		logger.info("MovieController-movie_Chart 진입");
		return model;
	}


	
	
}
