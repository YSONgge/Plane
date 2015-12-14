package service;

import entity.Airport;

public interface IAirportService {
	public long queryAirportId(Airport a);

	public long queryAirportPhone(Airport a);

	public String queryAirportName(Airport a);

}
