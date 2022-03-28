package katabank.issa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ilakkis
 *
 */
public class PropertiesUtils {

	private Properties properties = new Properties();

	public void loadPropertiesFile() {
		InputStream iStream = null;
		try {
			// Loading properties file from the classpath
			iStream = this.getClass().getClassLoader().getResourceAsStream("katabank.properties");
			if (iStream == null) {
				throw new IOException("File not found");
			}
			properties.load(iStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (iStream != null) {
					iStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Methods to read the properties from resource loaded property file
	 */
	public String getDirectoryName() {
		return properties.getProperty("directoryName");
	}

	public String getResultFileName() {
		return properties.getProperty("resultFileName");
	}

	public String getExtensionFile() {
		return properties.getProperty("extensionFile");
	}

	public String getTitleAccountNumber() {
		return properties.getProperty("titleAccountNumber");
	}

	public String getTitleAccountCreditDebit() {
		return properties.getProperty("titleAccountCreditDebit");
	}

	public String getTitleAccountTotal() {
		return properties.getProperty("titleAccountTotal");
	}

	public String getTitleAccountDate() {
		return properties.getProperty("titleAccountDate");
	}

}
