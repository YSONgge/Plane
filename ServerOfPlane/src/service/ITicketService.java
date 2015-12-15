package service;

import java.util.List;

import entity.ContactPerson;
import entity.Passenger;
import entity.Ticket;

public interface ITicketService {
	public boolean insertTicket(Passenger p, ContactPerson c,
			int uId, String flightId);

	public List<Ticket> queryTicketContent(int uId);
}
