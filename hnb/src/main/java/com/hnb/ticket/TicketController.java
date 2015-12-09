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
		model.addAttribute("movieSelectList", movieSelectList);
		
		return model;
	}
	
	@RequestMapping("/theaterSelect")
	public Model theaterSelect(
			String movie, String theater, 
			String date, Model model){
		List movieListRate = new ArrayList();
		List movieListAsc = new ArrayList();
		List theaterList = new ArrayList();
		List dateList = new ArrayList();
		List timeList = new ArrayList();
		List seatList = new ArrayList();
		
		logger.info("theaterSelect 진입");
		if (movie==null && date!=null) {
			movieListRate = ticketService.getMovieRateByTD(theater,date);
			movieListAsc = ticketService.getMovieAscByTD(theater,date);
		} else if (movie!=null && date==null) {
			dateList = ticketService.getShowDateListByMT(movie, theater);
		} else if (movie==null && date==null) {
			movieListRate = ticketService.getMovieRateByT(theater);
			movieListAsc = ticketService.getMovieAscByT(theater);
			dateList = ticketService.getShowDateListByT(theater);
		} else if (movie!=null&&theater!=null&&date!=null) {
			timeList = ticketService.getTimeList(movie, theater, date);
		}
		List theaterSelectList = new ArrayList();
		theaterSelectList.add(movieListRate);
		theaterSelectList.add(movieListAsc);
		theaterSelectList.add(dateList);
		theaterSelectList.add(timeList);
		model.addAttribute("theaterSelectList", theaterSelectList);
		return model;
	}
	
	@RequestMapping("/dateSelect")
	public Model dateSelect(
			String movie, String theater, 
			String date, Model model){
		logger.info("dateSelect 진입");
		List movieListRate = new ArrayList();
		List movieListAsc = new ArrayList();
		List theaterList = new ArrayList();
		List dateList = new ArrayList();
		List timeList = new ArrayList();
		List seatList = new ArrayList();
		
		if (movie==null && theater!=null) {
			movieListRate = ticketService.getMovieRateByTD(theater,date);
			movieListAsc = ticketService.getMovieAscByTD(theater,date);
		} else if (movie!=null && theater==null) {
			theaterList = ticketService.getTheaterListByMD(movie,date);
		} else if (movie==null && theater==null) {
			movieListRate = ticketService.getMovieRateByD(date);
			movieListAsc = ticketService.getMovieAscByD(date);
			theaterList = ticketService.getTheaterListByD(date);
		} else if (movie!=null&&theater!=null&&date!=null) {
			timeList = ticketService.getTimeList(movie, theater, date);
		}
		List dateSelectList = new ArrayList();
		dateSelectList.add(movieListRate);
		dateSelectList.add(movieListAsc);
		dateSelectList.add(theaterList);
		dateSelectList.add(timeList);
		
		model.addAttribute("dateSelectList",dateSelectList);
		return model;
	}
	@RequestMapping("/choiceseat")
	public Model choiceseat(
			String movie, String filmNumber, 
			String theater, String date,
			String time, Model model){
		logger.info("choiceseat 진입");
		ticketVO.setFilmNumber(filmNumber);
		ticketVO.setTheaterName(theater);
		ticketVO.setDate(date);
		ticketVO.setRoomName(time.split(" ")[0]);
		ticketVO.setStartTime(time.split(" ")[1]);
		
		logger.info(ticketVO.getFilmNumber());
		logger.info(ticketVO.getTheaterName());
		logger.info(ticketVO.getDate());
		logger.info(ticketVO.getRoomName());
		logger.info(ticketVO.getStartTime());
		int result = 0;
		if(result == 1) {
			model.addAttribute("result", "success");
		} else {
			model.addAttribute("result", "fail");
		}
		return model;
	}
	
	@RequestMapping("/initList")
	public Model initList(
			Model model){
		logger.info("initList 진입");
		List movieListRate = new ArrayList();
		List movieListAsc = new ArrayList();
		List theaterList = new ArrayList();
		List dateList = new ArrayList();
		List timeList = new ArrayList();
		List seatList = new ArrayList();
		List initList = new ArrayList();
		
		movieListRate = ticketService.getRateList();
		movieListAsc = ticketService.getAscList();
		theaterList = ticketService.getTheaterList();
		dateList = ticketService.getShowDateList();
		
		initList.add(movieListAsc);
		initList.add(movieListAsc);
		initList.add(theaterList);
		initList.add(dateList);
		
		model.addAttribute("initList", initList);
		return model;
	}
	
	@RequestMapping("/Seats")
	public Model Seats(
			Model model){
		logger.info("Seats 진입");
		String movie = null;
		String date = null;
		model.addAttribute("movie", movie);
		model.addAttribute("date", date);
		model.addAttribute("time", ticketVO.getStartTime());
		return model;
	}
	@RequestMapping("/initSeats")
	public Model initSeats(
			Model model){
		logger.info("initSeats 진입");
		List seatList = new ArrayList();
		seatList = ticketService.getSeatList(ticketVO.getTheaterName(),ticketVO.getRoomName());
		model.addAttribute("seatList", seatList);
		return model;
	}
	
	@RequestMapping("/infoSave")
	public Model infoSave(
			String adult, String old_man,
			String teenager, String seat_number,
			String price, Model model){
		logger.info("infoSave 진입");
		ticketVO.setAdult(Integer.parseInt(adult));
		ticketVO.setOldMan(Integer.parseInt(old_man));
		ticketVO.setTeenager(Integer.parseInt(teenager));
		ticketVO.setPrice(Integer.parseInt(price));
		ticketVO.setSeatNumber(seat_number);
		
		int result = 0;
		if(result == 1) {
			model.addAttribute("result", "success");

		} else {
			model.addAttribute("result", "fail");
		}
		
		return model;
	}
	
	@RequestMapping("/Confirm")
	public String Confirm(
			Model model){
		logger.info("Confirm 진입");
		String movie = null;
		model.addAttribute("movie", movie);
		model.addAttribute("ticket", ticketVO);
		return "ticket/Confirm";
	}
	
}





