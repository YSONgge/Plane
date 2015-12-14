package entity;

public class Airport {
	private String aLocation;
	private long aId;
	private String aName;
	private long aPhone;
	
	public String getaLocation() {
		return aLocation;
	}
	public void setaLocation(String aLocation) {
		this.aLocation = aLocation;
	}
	public long getaId() {
		return aId;
	}
	public void setaId(long aId) {
		this.aId = aId;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public long getaPhone() {
		return aPhone;
	}
	public void setaPhone(long aPhone) {
		this.aPhone = aPhone;
	}
	public Airport(String aLocation, long aId, String aName, long aPhone) {
		super();
		this.aLocation = aLocation;
		this.aId = aId;
		this.aName = aName;
		this.aPhone = aPhone;
	}
	public Airport(String aLocation) {
		super();
		this.aLocation = aLocation;
	}

}
