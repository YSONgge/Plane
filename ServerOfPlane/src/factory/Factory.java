package factory;


import service.impl.IAirportServiceImpl;
import service.impl.IFlightServiceImpl;
import service.impl.ITicketServiceImpl;
import service.impl.IUserServiceImpl;

public class Factory {
	public static IUserServiceImpl getIUserService() {
		return new IUserServiceImpl();
	}
	public static IAirportServiceImpl getIAirportService(){
		return new IAirportServiceImpl();
	}
	public static IFlightServiceImpl getIFlightService(){
		return new IFlightServiceImpl();
	}
	
	public static ITicketServiceImpl getITicketService(){
		return new ITicketServiceImpl();
	}
}
