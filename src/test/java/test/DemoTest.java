package test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.AddUser;
import pages.HomePage;
import pages.Login;

public class DemoTest {
	private static WebDriver driver;
	private static ExtentReports extent;
	private static ExtentTest test;
	
	@BeforeClass
	public static void init() {
		extent = new ExtentReports("src/test/resources/reports/report1.html", false);
		test = extent.startTest("Google search Extent testing");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.setHeadless(true);

		driver = new ChromeDriver(cOptions);
//		driver.manage().window().maximize();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);

		driver.manage().window().setSize(new Dimension(1366, 768));

// use implicit in before - waits before running tests
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

	}
	@Before
	public void setup() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			driver.get(HomePage.URL);
		} catch (TimeoutException e) {
			System.out.println("Page: " + HomePage.URL + "unable to load within 20 seconds");
		}

	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
		extent.close();
	}
	
	@Test
	public void createUser() throws InterruptedException {
		String uName = "Testing";
		String pWord = "OneTwoThree";
		
		HomePage page =  PageFactory.initElements(driver, HomePage.class);
		AddUser user = PageFactory.initElements(driver, AddUser.class);
		Login userInfo = PageFactory.initElements(driver, Login.class);
		
		page.userPage();

		user.createUser(uName, pWord);
		
		page.loginPage();
		userInfo.loginUser(uName, pWord);
		
		if(driver.getPageSource().contains("Successful")) {
			test.log(LogStatus.PASS, "New User login: Successful");
		} else {
			test.log(LogStatus.FAIL,"New User login: Failed");
		}
		
	}

}	
