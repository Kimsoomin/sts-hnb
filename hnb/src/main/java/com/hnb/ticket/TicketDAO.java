package com.hnb.ticket;

import java.util.List;

public interface TicketDAO {
	// C
	public int insert(TicketVO o);
	
	// R
	public List<TicketVO> selectAll();
	public TicketVO selectOneBy(String filmNumber);
	public int countBy(String filmNumber);
	public int count();
	
	// U
	public int update(TicketVO o);
	
	// D
	public int delete(String filmNumber);

	public List selectRoom(String theater, String room);
}
