package fitpeo.pageobjects;

import org.openqa.selenium.WebDriver;
import utils.Utilities;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean verifyHomePage() {
		return driver.getTitle().trim().contains("Remote Patient Monitoring");
	}

	public void navigateToRevenueCalculatorPage() {
		driver.navigate().to(Utilities.getProperty("revenue.calculator.url"));
	}

}
