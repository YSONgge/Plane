package dao;

import entity.Flight;

public interface IFlightDao {
	public String queryFlightId(Flight f);

	public String queryFlightStartTime(Flight f);

	public String queryFlightArriveTime(Flight f);

	public int queryFlightFare(Flight f);
}
