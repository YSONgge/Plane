/**
 * @Author yeye
 * @date 2015-12-11  下午9:13:38
 */
package dao;

import java.util.List;

import entity.ContactPerson;

import entity.Passenger;
import entity.Ticket;

/**
 * @author yeye
 *
 */
public interface ITicketDao {
	public boolean insertTicket(Passenger p,ContactPerson c,int uId,String flightId);
	
	public List<Ticket> queryTicketContent(String username);
}
