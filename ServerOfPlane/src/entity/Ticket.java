/**
 * @Author yeye
 * @date 2015-12-11  下午9:57:01
 */
package entity;

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

	
}
