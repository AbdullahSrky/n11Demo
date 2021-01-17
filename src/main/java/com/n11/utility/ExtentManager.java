package com.n11.utility;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	static ExtentReports extent;
	public static final String REPORT_FILE_PATH = "ExtentReport.html";

	private ExtentManager() {

		throw new IllegalStateException("Utility class");
	}

	public static synchronized ExtentReports getReporter() {

		log.info("Report Path: " + REPORT_FILE_PATH);

		if (extent == null) {
			extent = new ExtentReports(REPORT_FILE_PATH, true);

			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.loadConfig(ClassLoader.getSystemResource("extent-config.xml"));
		}

		return extent;
	}
}