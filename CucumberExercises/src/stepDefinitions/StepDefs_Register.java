package stepDefinitions;

import main.Constants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs_Register 
{
	private WebDriver driver;
	
	@Before("@register")
	public void SetUp()
	{
		// Set the Gecko Driver.
		System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_ROUTE);
		
		// Initialize driver
		driver = new FirefoxDriver();
	}
	
	@Given("^I am a new user$")
	public void i_am_a_new_user() throws InterruptedException 
	{
		// Open http://newtours.demoaut.com/.
		driver.get(Constants.HOME_URL);
		
		// Click on Register link
		driver.findElement(By.linkText("REGISTER")).click();
		
		// Delay to see what is happening.
		Thread.sleep(1500);
	}

	@When("^I register my data correctly in the page for a new user$")
	public void i_register_my_data_correctly_in_the_page_for_a_new_user() throws InterruptedException 
	{
		// Fill the form.
		
		// Contact information
		driver.findElement(By.name("firstName")).sendKeys(Constants.FIRST_NAME);
		driver.findElement(By.name("lastName")).sendKeys(Constants.LAST_NAME);
		driver.findElement(By.name("phone")).sendKeys(Constants.PHONE);
		
		// I don't know if it was done on purpose but the email and the
		// username fields names and ids are inverted.
		driver.findElement(By.id("userName")).sendKeys(Constants.EMAIL);
		
		// Mailing information
		driver.findElement(By.name("address1")).sendKeys(Constants.ADDRESS);
		driver.findElement(By.name("city")).sendKeys(Constants.CITY);
		driver.findElement(By.name("state")).sendKeys(Constants.STATE);
		driver.findElement(By.name("postalCode")).sendKeys(Constants.ZIP_CODE);
		new Select(driver.findElement(By.name("country"))).selectByVisibleText(Constants.COUNTRY); 
		
		// User Information
		
		// The email and the username fields names and ids are inverted.
		driver.findElement(By.id("email")).sendKeys(Constants.USERNAME);
		driver.findElement(By.name("password")).sendKeys(Constants.PASSWORD);
		driver.findElement(By.name("confirmPassword")).sendKeys(Constants.PASSWORD);
		
		// Delay to see what is happening.
		Thread.sleep(1500);
		
		// Submit the form.
		driver.findElement(By.name("register")).submit();
		
		// Give time to the page to load.
		Thread.sleep(3000);
	}

	@Then("^I should get a welcome message$")
	public void i_should_get_a_welcome_message() throws InterruptedException
	{
		// Check whether the registration was successful.
		boolean isRegistrationSuccessful = driver.getCurrentUrl().equalsIgnoreCase(Constants.SUCCESSFUL_REGISTRATION_URL);
		
		// Print the result.
		System.out.println("The registration was " + (isRegistrationSuccessful ? "successful" : "unsuccessful") + ".");
	}
	
	@After("@register")
	public void CleanUp()
	{
		if (driver != null)
			driver.quit();
	}

}
