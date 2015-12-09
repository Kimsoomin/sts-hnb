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
	MovieServiceImpl movieService;
	@Autowired
	MemberVO member;
	@Autowired
	MovieVO movie;
	
	@RequestMapping("/Admin")
	public String home(){
		logger.info("AdminController-home 진입");
		return "admin/Admin";
	}
	@RequestMapping("/movie_list")
	public Model movieList(
			Model model){
		logger.info("AdminController-movie_list 진입");
		List<MovieVO> movie_list;
		movie_list = movieService.getList();
		model.addAttribute("movie_list", movie_list);

		return model;
	}
	@RequestMapping("/member_list")
	public Model memberList(
			Model model){
		logger.info("AdminController-member_list 진입");
		List<MemberVO> list;
		list = service.getList();
		model.addAttribute("memberlist", list);
		return model;
	}
	@RequestMapping("/member_profile")
	public String memberProfile(
			String id, Model model){
		logger.info("AdminController-member_profile 진입");
		member = service.selectById(id);
		model.addAttribute("member", member);
		return "admin/member_profile";
	}
	
	@RequestMapping("/movie_profile")
	public String movieProfile(
			String filmNumber, Model model){
		logger.info("AdminController-movie_profile 진입");
		movie = movieService.searchByName(filmNumber);
		model.addAttribute("movie", movie);
		return "admin/movie_profile";
	}
	
	@RequestMapping("/insert")
	public Model insert(String id, String password,
			String email, String phone, 
			String addr, Model model){
		logger.info("AdminController-insert 진입");
		member = service.selectById(id);
		member.setPassword(password);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAddr(addr);
		int result = 0;
		result = service.change(member);
		model.addAttribute("result", id + " 님의 정보수정을 완료했습니다.");		
		return model;
	}
	
	@RequestMapping("/insert2")
	public Model insert2(
			String filmName, String story,
			Model model){
		logger.info("AdminController-insert2 진입");
		movie = movieService.searchByName(filmName);
		movie.setStory(story);
		model.addAttribute("result", filmName + " 정보수정을 완료했습니다.");
		return model;
	}
	@RequestMapping("/delete")
	public Model delete(
			String id, Model model){
		logger.info("AdminController-delete 진입");
		service.remove(id);
		model.addAttribute("result", id + " 님의 탈퇴를 완료했습니다.");
		return model;
	}
}
