package dao;

import java.util.List;

import entity.Flight;

public interface IFlightDao {
	public String queryFlightId(String origin, String dest, String flightDate);

	public String queryFlightStartTime(String origin, String dest, String flightDate);

	public String queryFlightArriveTime(String origin, String dest, String flightDate);

	public int queryFlightFare(String origin, String dest, String flightDate);
	
	public List<Flight> selectFlight(String origin, String dest, String flightDate);
}
