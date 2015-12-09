package com.hnb.admin;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnb.main.MainController;
import com.hnb.member.MemberServiceImpl;
import com.hnb.member.MemberVO;
import com.hnb.movie.MovieServiceImpl;
import com.hnb.movie.MovieVO;


@Controller
@RequestMapping("/admin")
public class AdminController {	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	MemberServiceImpl service;
	@Autowired
	MemberVO member;
	@Autowired
	MovieVO movie;
	@Autowired
	MovieServiceImpl movieService;
	
	
	@RequestMapping("/Admin")
	public String home(){
		logger.info("AdminController-home() 진입");
		return "admin/Admin";
	}
	@RequestMapping("/movie_list")
	public Model movieList(Model model){
		logger.info("AdminController-movieList() 진입");
		List<MovieVO> movieList;
		movieList = movieService.getList();
		model.addAttribute("movieList",movieList);
		
		return model;
	}
	@RequestMapping("/member_list")
	public Model memberList(Model model){
		logger.info("AdminController-movieList() 진입");
		List<MemberVO> list;
		list = service.getList();
		model.addAttribute("memberList",list);
		
		return model;
	}
	
	@RequestMapping("/member_profile")
	public Model memberProfile(
			String id,Model model
			){
		logger.info("개인 프로필 진입");
		logger.info("가져온 아이디{}",id);
		member = service.selectById(id);
		model.addAttribute("member", member);
		return model;
		
	}
	@RequestMapping("/movie_profile")
	public Model movieProfile(String filmNumber,Model model){
		logger.info(" 영화 목록 진입");
		logger.info(" 가져온 영화 번호{}",filmNumber);
		movie = movieService.searchByName(filmNumber);
		model.addAttribute("movie", movie);
		
		return model;
	}
	@RequestMapping("/insert")
	public Model insert(
		String id, String password,
		String email, String phone, String addr, Model model){
		logger.info("insert 진입");
		logger.info("id{}",id);
		logger.info("password{}",password);
		logger.info("email{}",email);
		logger.info("phone{}",phone);
		logger.info("addr{}",addr);
		member = service.selectById(id);
		member.setPassword(password);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAddr(addr);
		int result = service.change(member);
		model.addAttribute("result", id + " 님의 정보수정을 완료했습니다.");
		return model;
	}
	@RequestMapping("/insert2")
	public Model insert2(String filmName,String story,Model model){
		logger.info("인서트 진입");
		logger.info("영화이름{}",filmName);
		logger.info("줄거리{}",story);
		movie = movieService.searchByName(filmName);
		movie.setStory(story);
		model.addAttribute("movie",movie);
		
		return model;
	}
	@RequestMapping("/delete")
	public Model delete(String id,Model model){
		service.remove(id);
		model.addAttribute("result",id+"님의 탈퇴를 완료했습니다.");
		return model;
	}
	
}
