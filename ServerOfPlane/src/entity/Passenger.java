package entity;

/**
 * @author yeye
 * 
 */
public class Passenger {

	private String name;
	private String cardNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Passenger(String name, String cardNumber) {
		super();
		this.name = name;
		this.cardNumber = cardNumber;
	}

}
