/**
 * @Author yeye
 * @date 2015-12-11  下午9:57:01
 */
package entity;

import org.json.JSONObject;

import com.sun.xml.internal.bind.v2.TODO;

/**
 * @author yeye
 * 
 */
public class Ticket {
	private String orderId;
	private String pName;
	private String pCardNumber;
	private String pTicketType;
	private String pInsurance;
	private String cName;
	private String cPhone;
	private String cEmail;
	private String UId;
	private String flightId;

	public Ticket(String orderId, String pName, String pCardNumber,
			String pTicketType, String pInsurance, String cName, String cPhone,
			String cEmail, String uId, String flightId) {
		super();
		this.orderId = orderId;
		this.pName = pName;
		this.pCardNumber = pCardNumber;
		this.pTicketType = pTicketType;
		this.pInsurance = pInsurance;
		this.cName = cName;
		this.cPhone = cPhone;
		this.cEmail = cEmail;
		UId = uId;
		this.flightId = flightId;
	}

	// TODO:记得把对象提取出来

	public JSONObject getJsonObject() {
		JSONObject json = new JSONObject();
		json.put("orderId", orderId);
		json.put("pName", pName);
		json.put("pCardNumber", pCardNumber);
		json.put("pTicketType", pTicketType);
		json.put("pInsurance", pInsurance);
		json.put("cName", cName);
		json.put("cPhone", cPhone);
		json.put("cEmail", cEmail);
		json.put("UId", UId);
		json.put("flightId", flightId);

		return json;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpCardNumber() {
		return pCardNumber;
	}

	public void setpCardNumber(String pCardNumber) {
		this.pCardNumber = pCardNumber;
	}

	public String getpTicketType() {
		return pTicketType;
	}

	public void setpTicketType(String pTicketType) {
		this.pTicketType = pTicketType;
	}

	public String getpInsurance() {
		return pInsurance;
	}

	public void setpInsurance(String pInsurance) {
		this.pInsurance = pInsurance;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public String getUId() {
		return UId;
	}

	public void setUId(String uId) {
		UId = uId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

}
