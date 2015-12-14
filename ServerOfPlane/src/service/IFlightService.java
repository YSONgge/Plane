package service;

import java.util.List;

import entity.Flight;


public interface IFlightService {
	public String queryFlightId(String origin,String dest,String flightDate);

	public String queryFlightStartTime(String origin,String dest,String flightDate);

	public String queryFlightArriveTime(String origin,String dest,String flightDate);

	public int queryFlightFare(String origin,String dest,String flightDate);

	public List<Flight> selectFlight(String origin,String dest,String flightDate);
}

/*public interface IFlightService {
	public String queryFlightId(Flight f);

	public String queryFlightStartTime(Flight f);

	public String queryFlightArriveTime(Flight f);

	public int queryFlightFare(Flight f);

	public List<Flight> selectFlight(Flight f);
}
*/