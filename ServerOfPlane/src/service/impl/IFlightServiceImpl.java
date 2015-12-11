package service.impl;

import dao.IFlightDao;
import dao.impl.IFlightDaoImpl;
import entity.Airport;
import entity.Flight;
import service.IFlightService;

public class IFlightServiceImpl implements IFlightService {
	
	IFlightDao dao  = null;
	
	public IFlightServiceImpl() {
		dao = new IFlightDaoImpl();
	}

	public String queryFlightId(Flight f) {
		
		return dao.queryFlightId(f);
	}



	public String queryFlightStartTime(Flight f) {
		
		return dao.queryFlightStartTime(f);
	}

	public String queryFlightArriveTime(Flight f) {
		
		return dao.queryFlightArriveTime(f);
	}

	public int queryFlightFare(Flight f) {
		
		return dao.queryFlightFare(f);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IAirportServiceImpl i1 = new IAirportServiceImpl();
		Airport a = new Airport("北京");
		Airport b = new Airport("广州");
		
		System.out.println("id:\t" + i1.queryAirportId(a));
		IFlightServiceImpl i2 = new IFlightServiceImpl();
		Flight f = new Flight((int)i1.queryAirportId(a),(int)i1.queryAirportId(b));
		//Flight f = new Flight(100100,100102);
		System.out.println("机场ID:\t"+i2.queryFlightId(f));
		System.out.println("startTime:"+i2.queryFlightStartTime(f));
		System.out.println("arriveTime:"+i2.queryFlightArriveTime(f));
		System.out.println("fare:\t"+i2.queryFlightFare(f));
	}

}
