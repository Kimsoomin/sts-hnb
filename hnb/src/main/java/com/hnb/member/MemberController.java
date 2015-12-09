package com.hnb.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnb.main.MainController;

@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	MemberServiceImpl service;
	@Autowired
	MemberVO member;
	
	@RequestMapping("/admin_home")
	public String admin_home(){
		logger.info("어드민홈 입장");
		return "admin/Admin";
	}
	@RequestMapping("/provision")
	public String provision(){
		logger.info("provision 진입");
		return "member/provision";
	}
	@RequestMapping("/join_member")
	public Model joinMember(
			String id, String password,
			String name,String birth,String addr,
			String gender,String email,String phone, 
			Model model){
		logger.info("조인멤버 진입");

		int result = service.join(member);
		if (result == 1) {
			logger.info("회원가입성공");
			model.addAttribute("result", "success");
			model.addAttribute("name", member.getName());
		} else {
			logger.info("회원가입 실패");
			model.addAttribute("result", "fail");
		}	
		return model;
	}
	@RequestMapping("/join_Result")
	public String joinResult(){
		logger.info("조인 리절트 진입");
		return "member/join_Result";
	}
	@RequestMapping("/logout")
	public Model logout(Model model){
		logger.info("로그아웃 진입");
//		session.invalidate();
		model.addAttribute("result", "success");
		return model;
	}
	@RequestMapping("/login")
	public Model login(String id,String password, Model model){
		logger.info("로그인 진입");
		logger.info("유저아이디 :" +id);
		logger.info("유저비번 :" +password);
		 member = service.login(id, password);
		 if(member==null){
			model.addAttribute("result", "fail");   	
         } else {
         //f로그인 성공시
//         	 session = request.getSession();
//           session.setAttribute("user",member);
        	 model.addAttribute("result", "success");
        	 model.addAttribute("id", id);
        	 model.addAttribute("pw", password);
             if (id.equals("choa")) {
             	model.addAttribute("admin", "yes");
 			} else {
 				model.addAttribute("admin", "no");
 			}
         }
		return model;
	}
	@RequestMapping("/check_Overlap")
	public Model checkOverlap(String id, Model model){
		logger.info("중복체크로 진입");
		if (service.selectById(id).getId() == null) {
			model.addAttribute("result", "usable");
			model.addAttribute("id", "id");
		} else {
			model.addAttribute("result", "unusable");
			model.addAttribute("id", "id");
		}
		return model;
	}
	@RequestMapping("/mypage")
	public String mypage(){
		logger.info("마이페이지 진입");
		return "member/mypage";
	}
	@RequestMapping("/detail")
	public Model detail(Model model){
		logger.info("디테일 진입");
		return model;
	}
	
}
