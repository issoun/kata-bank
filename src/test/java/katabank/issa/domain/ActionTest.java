package katabank.issa.domain;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ilakkis
 * 
 */
public class ActionTest {

	private Account account;

	@Before
	public void initialize() {
		account = new Account("1");
	}

	@Test
	public void addOperation() {
		Action action = new Action(new Date(), account, 2500L, Operation.ADDITION);
		assertTrue(account.getTotal().equals(2500L));
		assertTrue(action.getAmount().equals(2500L));
	}
}
