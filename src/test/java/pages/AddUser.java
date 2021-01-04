package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddUser {

	// Attributes using Page Object Model (POM) Design
	
	@FindBy(name = "username")
	WebElement uName;
	
	@FindBy(name = "password")
	WebElement pWord;
	
	// Sets up new user details

	public void createUser(String username, String password) {
		uName.sendKeys(username);
		pWord.sendKeys(password);
		pWord.submit();
	}

}
