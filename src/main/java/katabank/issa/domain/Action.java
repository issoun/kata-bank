package katabank.issa.domain;

import java.util.Date;

import katabank.issa.utils.ResultUtils;

/**
 * @author ilakkis
 *
 */
public class Action {

	private Long amount;

	private Date dateOperation;
	private Operation operation;
	private Account account;

	/**
	 * Constructeur de classe
	 * 
	 * @param dateOperation
	 * @param account
	 * @param amount
	 * @param operation
	 */
	public Action(Date dateOperation, Account account, Long amount, Operation operation) {
		super();
		this.amount = amount;
		this.dateOperation = dateOperation;
		this.operation = operation;
		this.account = account;
		addOperation(account, amount, operation);
	}

	/**
	 * Method for adding the current transaction to the account
	 * 
	 * @param account
	 * @param amount
	 * @param op
	 */
	private void addOperation(Account account, Long amount, Operation op) {
		account.setTotal((op == Operation.ADDITION) ? account.getTotal() + amount : account.getTotal() - amount);

		ResultUtils.generateResult(account, amount, dateOperation, operation);
	}

	public Long getAmount() {
		return amount;
	}

}
