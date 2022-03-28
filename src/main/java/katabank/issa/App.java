package katabank.issa;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import katabank.issa.domain.Account;
import katabank.issa.domain.Operation;
import katabank.issa.utils.PropertiesUtils;
import katabank.issa.utils.ResultUtils;

/**
 * @author ilakkis
 *
 */
public class App {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Example Run App Mode

		// loading properties file for reading parameters
		PropertiesUtils p = new PropertiesUtils();
		p.loadPropertiesFile();

		// prerequisite: deletion of the test directory
		ResultUtils.deleteDirectory(p.getDirectoryName());

		// account identification
		Account account = new Account("account_run_app");

		// credit or debit transactions on the current account
		account.putOnAccount(account, 1000L, Operation.ADDITION);
		account.putOnAccount(account, 2500L, Operation.ADDITION);
		account.putOnAccount(account, 2000L, Operation.SUBSTRACTION);

		// reading results from a file
		Scanner scan = new Scanner(new File(
				p.getDirectoryName() + "/" + p.getResultFileName() + "_account_run_app" + p.getExtensionFile()));
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			System.out.println(line);
		}

	}
}
