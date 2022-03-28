package katabank.issa.domain;

import java.util.Date;

/**
 * @author ilakkis
 *
 */
public class Account {

	private String number;
	private Long total = 0L; // starting total (if not filled in)

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @param total
	 * @param number
	 */
	public Account(Long total, String number) {
		super();
		this.total = total;
		this.number = number;

	}

	/**
	 * class constructor
	 * 
	 * @param number
	 */
	public Account(String number) {
		super();
		this.number = number;

	}

	/**
	 * Method for placing credits/debits on the identified account
	 * 
	 * @param account
	 * @param amount
	 * @param operation
	 */
	public void putOnAccount(Account account, Long amount, Operation operation) {
		new Action(new Date(), account, amount, operation);
	}

}
