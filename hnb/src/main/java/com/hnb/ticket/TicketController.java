package com.hnb.ticket;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnb.member.MemberController;


@Controller
@RequestMapping("/ticket")

public class TicketController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	TicketService ticketService = TicketServiceImpl.getInstance();
	@Autowired
	TicketVO ticketVO;
	
	@RequestMapping("/Ticket")
	public String Ticket(){
		logger.info("Ticket 진입");
		return "ticket/Ticket";
	}
	
	@RequestMapping("/movieSelect")
	public Model movieSelect(
			String movie, String theater, 
			String date, Model model){
		logger.info("movieSelect 진입");
		List movieListRate = new ArrayList();
		List movieListAsc = new ArrayList();
		List theaterList = new ArrayList();
		List dateList = new ArrayList();
		List timeList = new ArrayList();
		List seatList = new ArrayList();
		
		if (theater==null && date!=null) {
			logger.info("극장널");
			theaterList = ticketService.getTheaterListByMD(movie,date);
		} else if (theater!=null && date==null) {
			logger.info("날짜널");
			dateList = ticketService.getShowDateListByMT(movie,theater);
		} else if (theater==null && date==null) {
			logger.info("모두 널");
			theaterList = ticketService.getTheaterListByM(movie);
			dateList = ticketService.getShowDateListByM(movie);
		} else if (movie!=null&&theater!=null&&date!=null) {
			timeList = ticketService.getTimeList(movie, theater, date);
		}
		List movieSelectList = new ArrayList();
		System.out.println("극장" +theaterList);
		System.out.println("날짜" +dateList);
		movieSelectList.add(theaterList);
		movieSelectList.add(dateList);
		movieSelectList.add(timeList);
		model.addAttribute(movieSelectList);
		
		return model;
	}
	
	@RequestMapping("/theaterSelect")
	public String theaterSelect(
			String movie, String theater, Model model){
		logger.info("theaterSelect 진입");
		return "ticket/theaterSelect";
	}
	
	@RequestMapping("/dateSelect")
	public String dateSelect(){
		logger.info("dateSelect 진입");
		return "ticket/dateSelect";
	}
	@RequestMapping("/choiceseat")
	public String choiceseat(){
		logger.info("choiceseat 진입");
		return "ticket/choiceseat";
	}
	
	@RequestMapping("/initList")
	public String initList(){
		logger.info("initList 진입");
		return "ticket/initList";
	}
	
	@RequestMapping("/Seats")
	public String Seats(){
		logger.info("Seats 진입");
		return "ticket/Seats";
	}
	@RequestMapping("/initSeats")
	public String initSeats(){
		logger.info("initSeats 진입");
		return "ticket/initSeats";
	}
	
	@RequestMapping("/infoSave")
	public String infoSave(){
		logger.info("infoSave 진입");
		return "ticket/infoSave";
	}
	
	@RequestMapping("/Confirm")
	public String Confirm(){
		logger.info("Confirm 진입");
		return "ticket/Confirm";
	}
	
}





