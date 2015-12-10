package entity;

public class Flight {
	private int originId;
	private int destId;
	private String flightId;
	private String flightStartTime;
	private String flightOrriveTime;
	private int flightFare;

	public Flight(int originId, int destId) {
		super();
		this.originId = originId;
		this.destId = destId;
	}

	public int getOriginId() {
		return originId;
	}

	public void setOriginId(int originId) {
		this.originId = originId;
	}

	public int getDestId() {
		return destId;
	}

	public void setDestId(int destId) {
		this.destId = destId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getFlightStartTime() {
		return flightStartTime;
	}

	public void setFlightStartTime(String flightStartTime) {
		this.flightStartTime = flightStartTime;
	}

	public String getFlightOrriveTime() {
		return flightOrriveTime;
	}

	public void setFlightOrriveTime(String flightOrriveTime) {
		this.flightOrriveTime = flightOrriveTime;
	}

	public int getFlightFare() {
		return flightFare;
	}

	public void setFlightFare(int flightFare) {
		this.flightFare = flightFare;
	}

}
