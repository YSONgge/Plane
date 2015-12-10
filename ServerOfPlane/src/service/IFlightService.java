package service;

import entity.Flight;

public interface IFlightService {
	public String queryFlightId(Flight f);

	public String queryFlightStartTime(Flight f);

	public String queryFlightArriveTime(Flight f);

	public int queryFlightFare(Flight f);
}
