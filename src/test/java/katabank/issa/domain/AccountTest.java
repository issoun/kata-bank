package katabank.issa.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

import katabank.issa.utils.PropertiesUtils;
import katabank.issa.utils.ResultUtils;

/**
 * @author ilakkis
 *
 */
public class AccountTest {

	private static final String ACCOUNT_1 = "test_makeDeposit";
	private static final String ACCOUNT_2 = "test_makeWithdrawal";
	private static final String ACCOUNT_3 = "test_makeTwoDeposits";
	private static final String ACCOUNT_4 = "test_makeDepositsAndWithdrawals";

	static PropertiesUtils p = new PropertiesUtils();

	@BeforeClass
	public static void deleteDirectory() throws IOException {
		// Charge property file for reading parameters
		p.loadPropertiesFile();
		ResultUtils.deleteDirectory(p.getDirectoryName());
	}

	@Test
	public void identifyAccount() {
		identifyAccountNumber("1");
		identifyAccountNumber("2");
	}

	/*
	 * US 1: In order to save money As a bank client I want to make a deposit in my
	 * account
	 */
	@Test
	public void makeDeposit() throws FileNotFoundException {
		Account account = new Account(ACCOUNT_1);
		account.putOnAccount(account, 1000L, Operation.ADDITION);

		// vérify line on file
		verifyLineResult(ACCOUNT_1, 1, "+1000", "1000");
	}

	/*
	 * US 2: In order to retrieve some or all of my savings As a bank client I want
	 * to make a withdrawal from my account
	 */
	@Test
	public void makeWithdrawal() throws FileNotFoundException {
		Account account = new Account(ACCOUNT_2);
		account.putOnAccount(account, 1000L, Operation.SUBSTRACTION);

		// vérify line on file
		verifyLineResult(ACCOUNT_2, 1, "-1000", "-1000");
	}

	@Test
	public void makeTwoDeposits() throws FileNotFoundException {
		Account account = new Account(ACCOUNT_3);
		account.putOnAccount(account, 2500L, Operation.ADDITION);
		account.putOnAccount(account, 3000L, Operation.ADDITION);

		// vérify lines on file
		verifyLineResult(ACCOUNT_3, 1, "+2500", "2500");
		verifyLineResult(ACCOUNT_3, 2, "+3000", "5500");
	}

	/*
	 * US 3: In order to check my operations As a bank client I want to see the
	 * history (operation, date, amount, balance) of my operations"
	 */
	@Test
	public void makeDepositsAndWithdrawals() throws FileNotFoundException {
		Account account = new Account(ACCOUNT_4);
		account.putOnAccount(account, 2500L, Operation.ADDITION);
		account.putOnAccount(account, 3000L, Operation.ADDITION);
		account.putOnAccount(account, 3000L, Operation.SUBSTRACTION);
		account.putOnAccount(account, 4000L, Operation.ADDITION);
		account.putOnAccount(account, 1500L, Operation.SUBSTRACTION);

		// vérify lines on file
		verifyLineResult(ACCOUNT_4, 1, "+2500", "2500");
		verifyLineResult(ACCOUNT_4, 2, "+3000", "5500");
		verifyLineResult(ACCOUNT_4, 3, "-3000", "2500");
		verifyLineResult(ACCOUNT_4, 4, "+4000", "6500");
		verifyLineResult(ACCOUNT_4, 5, "-1500", "5000");
	}

	private void verifyLineResult(String fileName, int lineNumber, String versement, String totalAmount)
			throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(
				new File(p.getDirectoryName() + "/" + p.getResultFileName() + "_" + fileName + p.getExtensionFile()));
		int i = 1;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (i == lineNumber) {
				assertTrue(line.contains(p.getTitleAccountNumber() + ResultUtils.POINT_MEANS + fileName));
				assertTrue(line.contains(p.getTitleAccountCreditDebit() + ResultUtils.POINT_MEANS + versement));
				assertTrue(line.contains(p.getTitleAccountTotal() + ResultUtils.POINT_MEANS + totalAmount));
				assertTrue(line.contains(p.getTitleAccountDate()));
				return;
			}
			i++;
		}
	}

	private void identifyAccountNumber(String numero) {
		Account account = new Account(numero);
		assertEquals("failure - Account number is wrong", account.getNumber(), numero);
	}
}
