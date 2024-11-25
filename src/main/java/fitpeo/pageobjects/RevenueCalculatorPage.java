package fitpeo.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RevenueCalculatorPage extends BasePage {

	public RevenueCalculatorPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h4[contains(text(),'Medicare Eligible Patients')]")
	private WebElement medicareEligiblePatientsHeader;

	@FindBy(xpath = "//span[contains(@class,'MuiSlider-root')]")
	private WebElement sliderBarElement;

	@FindBy(xpath = "//span[contains(@class,'MuiSlider-thumb')]")
	private WebElement silderPointElement;

	@FindBy(xpath = "//input[contains(@class,'MuiInputBase-input') and @type='number']")
	private WebElement sliderBottonTextFiled;

	@FindBy(xpath = "//span[contains(text(),'Patients should be between')]")
	private WebElement sliderRangeElement;

	@FindBy(xpath = "//p[contains(text(),'Total Recurring Reimbursement for all Patients Per Month:')]/p")
	private WebElement totalRecurrReemValElement;

	public boolean verifyRevenueCalculatorPage() {
		return driver.getCurrentUrl().contains("revenue-calculator") && medicareEligiblePatientsHeader.isDisplayed();
	}

	public void scrollToSilderBar() {
		scrollToElement(medicareEligiblePatientsHeader);
	}

	public int getSliderPercenatgeOfGivenNumber(String number) {
		int maxRange = Integer.parseInt(sliderRangeElement.getText().trim().split("to")[1].trim());
		int calPercentageOfGevenNumber = (Integer.parseInt(number) * 100) / maxRange;
		return calPercentageOfGevenNumber;
	}

	public void slidePointToGivenNumber(String number) {
		String numberPercentage = String.valueOf(getSliderPercenatgeOfGivenNumber(number)) + "%";
		Actions actions = new Actions(driver);
		setAttrbuteToElement(silderPointElement, "style", "left: " + numberPercentage);
		try {
			scrollToElement(medicareEligiblePatientsHeader);
			Thread.sleep(3000);
			actions.click(silderPointElement).build().perform();
			Thread.sleep(3000);
		} catch (Exception e) {

		}
	}

	public String getSliderBottonTextFiledValue() {
		return sliderBottonTextFiled.getAttribute("value").trim();
	}

	public boolean verifySliderBottonTextFiledValue(String number) {
		int actualValue = Integer.parseInt(getSliderBottonTextFiledValue());
		int expectedValue = Integer.parseInt(number);

		return actualValue >= expectedValue - 3 && actualValue <= expectedValue + 3;
	}

	public void setSliderBottonTextFiledValue(String number) {
		Actions actions = new Actions(driver);
		Action action = actions.moveToElement(sliderBottonTextFiled).doubleClick(sliderBottonTextFiled)
				.sendKeys(Keys.DELETE).sendKeys(sliderBottonTextFiled, number).build();
		action.perform();
	}

	public boolean verifyUpdatedSliderPosition(String number) {
		String numberPercentage = getSliderPercenatgeOfGivenNumber(number) + "%";
		return silderPointElement.getAttribute("style").trim().contains(numberPercentage);
	}

	public void selectCPTCheckbox(String checkboxName) {
		WebElement checkBox = driver.findElement(
				By.xpath("//p[text()='" + checkboxName + "']//following-sibling::label//input[@type='checkbox']"));
		scrollToElement(checkBox);
		clickElementUsingJavaSript(checkBox);
	}

	public boolean verifyTotalRecurringReimbursementForAllPatientsPerMonthValue(String value) {
		return totalRecurrReemValElement.getText().trim().equals(value);
	}

}
