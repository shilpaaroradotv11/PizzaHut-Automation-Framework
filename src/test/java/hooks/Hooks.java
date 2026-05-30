package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import com.aventstack.extentreports.ExtentReports;

import utilities.DriverSetup;
import utilities.ExtentManager;
import utilities.ExtentTestManager;

public class Hooks {

	public static ExtentReports extent;

	@Before
	public void setup() {

		
		extent = ExtentManager.getReport();

		ExtentTestManager.test = extent.createTest("Pizza Hut Order Flow");
		
	}

	@After
	public void tearDown() {

		extent.flush();

		DriverSetup.closeBrowser();
	}
}