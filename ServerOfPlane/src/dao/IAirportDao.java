package dao;

import entity.Airport;

public interface IAirportDao {
	public long queryAirportId(Airport a);

	public long quertAirportPhone(Airport a);

	public String queryAirportName(Airport a);

}
