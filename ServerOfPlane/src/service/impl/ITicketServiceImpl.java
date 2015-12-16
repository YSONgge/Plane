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

	public boolean insertTicket(Passenger p, ContactPerson c, int uId,
			String flightId, boolean delay, boolean safe, int type) {
		String tType;
		switch (type) {
		case 1:
			tType = "经济舱";
			break;
		case 2:
			tType = "公务舱";
			break;
		case 3:
			tType = "头等舱";
			break;
		default:
			tType = "经济舱";
			break;
		}
		StringBuilder stringBuilder = new StringBuilder();
		if (delay) {
			stringBuilder.append("延误险,");
		}
		if (safe) {
			stringBuilder.append("航意险");
		}
		return dao.insertTicket(p, c, uId, flightId, stringBuilder.toString(),
				tType);
	}

	public List<Ticket> queryTicketContent(String username) {
		return dao.queryTicketContent(username);
	}

	public static void main(String[] args) {
		ContactPerson c = new ContactPerson("张三", "19231231", "wer@qq.com");
		Passenger p = new Passenger("李四", "1231231232312");
		ITicketServiceImpl IT = new ITicketServiceImpl();
		IT.insertTicket(p, c, 202, "ye5112", true, true, 1);

	}
}