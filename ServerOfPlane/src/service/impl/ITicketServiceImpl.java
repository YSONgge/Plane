/**
 * @Author yeye
 * @date 2015-12-11  下午10:01:59
 */
package service.impl;

import java.util.List;

import dao.ITicketDao;
import dao.impl.ITicketDaoImpl;
import entity.ContactPerson;
import entity.Passenger;
import entity.Ticket;

/**
 * @author yeye
 * 
 */
public class ITicketServiceImpl {
	ITicketDao dao = null;

	public ITicketServiceImpl() {
		dao = new ITicketDaoImpl();
	}

	public boolean insertTicket(long orderId, Passenger p, ContactPerson c,
			int uId, String flightId) {
		return dao.insertTicket(orderId, p, c, uId, flightId);
	}

	public List<Ticket> queryTicketContent(int uId) {
		return dao.queryTicketContent(uId);
	}

	public static void main(String[] args) {
		ContactPerson c = new ContactPerson("张三", "19231231", "wer@qq.com");
		Passenger p = new Passenger("李四", "1231231232312");
		ITicketServiceImpl IT = new ITicketServiceImpl();
		IT.insertTicket(0001, p, c, 202, "ye5112");
		
	}
}