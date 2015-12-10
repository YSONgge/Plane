package service.impl;

import dao.IAirportDao;
import dao.impl.IAirportDaoImpl;
import entity.Airport;
import service.IAirportService;

public class IAirportServiceImpl implements IAirportService {

	IAirportDao dao = null;

	public IAirportServiceImpl() {
		dao = new IAirportDaoImpl();
	}

	public long queryAirportId(Airport a) {

		return dao.queryAirportId(a);
	}

	public long quertAirportPhone(Airport a) {

		return dao.quertAirportPhone(a);
	}

	public String queryAirportName(Airport a) {

		return dao.queryAirportName(a);
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		IAirportServiceImpl i = new IAirportServiceImpl();
		Airport a = new Airport("±±¾©");
		System.out.println("name:\t" + i.queryAirportName(a));
		System.out.println("phone:\t" + i.quertAirportPhone(a));
		System.out.println("id:\t" + i.queryAirportId(a));

	}

}
