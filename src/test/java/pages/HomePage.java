package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

// Attributes
	
	public static final String URL = "https://thedemosite.co.uk/";
	
	@FindBy(xpath = "html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")
	private WebElement addAUserLink;

	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")
	private WebElement loginLink;
	
	public void userPage() {
		addAUserLink.click();
	}	
	
	public void loginPage() {
		loginLink.click();
	}
}
