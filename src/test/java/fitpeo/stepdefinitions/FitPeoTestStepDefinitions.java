package fitpeo.stepdefinitions;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import fitpeo.pageobjects.HomePage;
import fitpeo.pageobjects.RevenueCalculatorPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Utilities;

public class FitPeoTestStepDefinitions {

	WebDriver driver;
	HomePage homePage;
	RevenueCalculatorPage revenueCalculatorPage;

	@Before
	public void setup() {
		driver = new ChromeDriver();

		driver.get(Utilities.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		homePage = new HomePage(driver);
		revenueCalculatorPage = new RevenueCalculatorPage(driver);
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) throws Exception {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		String screenshotName = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()).replaceAll("[^0-9]", "")
				+ ".png";
		scenario.attach(screenshot, "image/png", screenshotName);
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Given("user is on FitPeo Home Page")
	public void userIsOnFitPeoHomePage() {
		Assert.assertTrue(homePage.verifyHomePage(), "FitPeo Home Page is not Diplayed");
	}

	@Given("user navigates to Revenue Calculator Page")
	public void userNavigatesToRevenueCalculatorPage() {
		homePage.navigateToRevenueCalculatorPage();
		Assert.assertTrue(revenueCalculatorPage.verifyRevenueCalculatorPage(),
				"Revenue Calculator page is not displayed");

	}

	@Given("user scroll down the browser until Revenue Calculator slider visible")
	public void userScrollDownTheBrowserUntilRevenueCalculatorSliderVisible() {
		revenueCalculatorPage.scrollToSilderBar();
	}

	@When("user adjust the slider position to set its value to  {string}")
	public void userAdjustTheSliderPositionToSetItsValueTo(String number) {
		revenueCalculatorPage.slidePointToGivenNumber(number);
	}

	@Then("slider should moved and bottom test filed value should be updated to {string}")
	public void sliderShouldMovedAndBottomTestFiledValueShouldBeUpdatedTo(String number) {
		Assert.assertTrue(revenueCalculatorPage.verifySliderBottonTextFiledValue(number));
		revenueCalculatorPage.refreshBrowser();
	}

	@When("user updates the text field value associated to slider to {string}")
	public void userUpdatesTheTextFieldValueAssociatedToSliderTo(String number) {
		revenueCalculatorPage.setSliderBottonTextFiledValue(number);
	}

	@Then("slider position should updated and refelctes the value to {string}")
	public void sliderPositionShouldUpdatedAndRefelctesTheValueTo(String number) {
		Assert.assertTrue(revenueCalculatorPage.verifyUpdatedSliderPosition(number));
	}

	@When("user selects check boxes of below CPT codes")
	public void userSelectsCheckBoxesFOfBelowCPTCodes(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> allCPTCodesList = dataTable.asLists(String.class);
		List<String> allCPTCodes = allCPTCodesList.get(0);

		for (String eachCPTCode : allCPTCodes) {
			revenueCalculatorPage.selectCPTCheckbox(eachCPTCode.trim());
		}
	}

	@Then("the Total Recurring Reimbursement for all Patients Per Month shows the value to {string}")
	public void theTotalRecurringReimbursementForAllPatientsPerMonthShowsTheValueTo(String value) {
		Assert.assertTrue(revenueCalculatorPage.verifyTotalRecurringReimbursementForAllPatientsPerMonthValue(value));
	}

}
