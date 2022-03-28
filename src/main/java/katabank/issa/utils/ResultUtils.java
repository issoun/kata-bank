package katabank.issa.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import katabank.issa.domain.Account;
import katabank.issa.domain.Operation;

/**
 * @author ilakkis
 *
 */
public class ResultUtils {

	public static final String POINT_MEANS = " : ";
	public static final String WHITE_SPACES = "    ";

	static PropertiesUtils p = new PropertiesUtils();

	/**
	 * Writing the current operation in the history file
	 * 
	 * @param fileName
	 * @param number
	 * @param total
	 * @param amount
	 * @param dateOperation
	 * @param operation
	 * @throws IOException
	 */
	private static void writeOnFile(String fileName, String number, Long total, Long amount, Date dateOperation,
			Operation operation) throws IOException {
		String str = p.getTitleAccountNumber() + POINT_MEANS + number + WHITE_SPACES + p.getTitleAccountCreditDebit()
				+ POINT_MEANS + operation.getLabel() + amount + WHITE_SPACES + p.getTitleAccountTotal() + POINT_MEANS
				+ total + WHITE_SPACES + p.getTitleAccountDate() + POINT_MEANS + dateOperation;
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
		writer.append(' ');
		writer.append(str);
		writer.append("\n");

		writer.close();
	}

	/**
	 * Utility function to delete a directory
	 */
	private static void createDirecotry() {
		File directory = new File(p.getDirectoryName()); // windows only
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	/**
	 * Method of storing the current operation in a history file
	 * 
	 * @param account       : account (number, total)
	 * @param amount        : total amount remaining on the account
	 * @param dateOperation : date of operation
	 * @param operation     : type of operation
	 */
	public static void generateResult(Account account, Long amount, Date dateOperation, Operation operation) {
		p.loadPropertiesFile();
		createDirecotry();

		// writing in a file
		try {
			writeOnFile(
					p.getDirectoryName() + "/" + p.getResultFileName() + "_" + account.getNumber()
							+ p.getExtensionFile(),
					account.getNumber(), account.getTotal(), amount, dateOperation, operation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Utility function to delete a directory
	 * 
	 * @throws IOException
	 */
	public static void deleteDirectory(String directoryName) throws IOException {
		File directory = new File(directoryName);
		FileUtils.deleteDirectory(directory);
	}
}
