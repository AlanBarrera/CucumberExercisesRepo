package stepDefinitions;

import main.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs_Booking 
{
	private WebDriver driver;
	private int numberOfPassengers;
	
	@Before("@booking")
	public void SetUp()
	{
		// Set the Gecko Driver.
		System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_ROUTE);
		
		// Initialize driver.
		driver = new FirefoxDriver();
		
		// Since the page is having problems, wait up to 1 minute
		// before throwing an error.
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	}
	
	@Given("I am an user to book a first class round trip for {int} passengers on December\\/{int} in Unified Airlines")
	public void i_am_an_user_to_book_a_first_class_round_trip_for_passengers_on_December_in_Unified_Airlines(Integer numberOfPassengers, Integer monthDay) throws InterruptedException
	{	
		// Initialize numberOfPassengers variable.
		this.numberOfPassengers = numberOfPassengers;
		
		// Open http://newtours.demoaut.com/.
		driver.get(Constants.HOME_URL);
		
		// Send login keys.
		driver.findElement(By.name("userName")).sendKeys(Constants.USER_PASSWORD);
		driver.findElement(By.name("password")).sendKeys(Constants.USER_PASSWORD);
		
		// Submit
		driver.findElement(By.name("password")).submit();
		
		// Make sure "Round trip" is selected
		List<WebElement> tripTypeRadioButtons = driver.findElements(By.name("tripType"));
		
		for(WebElement tripType: tripTypeRadioButtons)
		{
			if(tripType.getAttribute("value").equals("roundtrip"))
			{
				tripType.click();
				break;
			}
		}
				
		// Select number of passengers.
		new Select(driver.findElement(By.name("passCount"))).selectByValue(numberOfPassengers.toString());
		
		// Select departing month.
		new Select(driver.findElement(By.name("fromMonth"))).selectByVisibleText("December");
		
		// Select departing day.
		new Select(driver.findElement(By.name("fromDay"))).selectByValue(monthDay.toString());
		
		// Choose "First Class" service.
		List<WebElement> servClassRadioButtons = driver.findElements(By.name("servClass"));
		
		for(WebElement servClass: servClassRadioButtons)
		{
			if(servClass.getAttribute("value").equals("First"))
			{
				servClass.click();
				break;
			}
		}
		
		// Select Airline.
		new Select(driver.findElement(By.name("airline"))).selectByVisibleText("Unified Airlines");
			
	}

	@And("Departing from Paris")
	public void departing_from_Paris()
	{
	    // Select departing location.
		new Select(driver.findElement(By.name("fromPort"))).selectByValue("Paris");
	}

	@And("Arriving to Acapulco")
	public void arriving_to_Acapulco() throws InterruptedException
	{
		// Select arriving location.
		new Select(driver.findElement(By.name("toPort"))).selectByValue("Acapulco");
		
		// Delay to wee what is happening.
		Thread.sleep(3000);
		
		// Submit form.
		driver.findElement(By.name("findFlights")).submit();
		
		// Give time to the page to load.
		Thread.sleep(1000);
		
		// Select Unified Airlines flights.
		
		// For departing.
		List<WebElement> departFlights = driver.findElements(By.name("outFlight"));
		
		for(WebElement flight : departFlights)
		{
			if(flight.getAttribute("value").contains("Unified"))
			{
				flight.click();
				break;
			}
		}
		
		// For returning.
		List<WebElement> returnFlights = driver.findElements(By.name("inFlight"));
				
		for(WebElement flight : returnFlights)
		{
			if(flight.getAttribute("value").contains("Unified"))
			{
				flight.click();
				break;
			}
		}
		
		// Delay to wee what is happening.
		Thread.sleep(1500);
				
		// Submit.
		driver.findElement(By.name("reserveFlights")).submit();
	}

	@When("I register my data to book a trip correctly in the page")
	public void i_register_my_data_to_book_a_trip_correctly_in_the_page() throws InterruptedException
	{
	    // Fill the form
		
		// Passengers.
		for(int i = 0; i < this.numberOfPassengers; i++)
		{
			driver.findElement(By.name("passFirst" + i)).sendKeys(Constants.FIRST_NAME + " " + i);
			driver.findElement(By.name("passLast" + i)).sendKeys(Constants.LAST_NAME + " " + i);
			new Select(driver.findElement(By.name("pass." + i + ".meal"))).selectByVisibleText("Vegetarian");
		}
		
		// Credit Card.
		new Select(driver.findElement(By.name("creditCard"))).selectByVisibleText("MasterCard");
		driver.findElement(By.name("creditnumber")).sendKeys(Constants.CREDIT_CARD);
		new Select(driver.findElement(By.name("cc_exp_dt_mn"))).selectByIndex(1);
		new Select(driver.findElement(By.name("cc_exp_dt_yr"))).selectByIndex(1);
		driver.findElement(By.name("cc_frst_name")).sendKeys(Constants.FIRST_NAME);
		driver.findElement(By.name("cc_mid_name")).sendKeys(Constants.MIDDLE_NAME);
		driver.findElement(By.name("cc_last_name")).sendKeys(Constants.LAST_NAME);
		
		// Billing Address
		driver.findElements(By.name("ticketLess")).get(0).click();
		driver.findElement(By.name("billAddress1")).sendKeys(Constants.ADDRESS);
		driver.findElement(By.name("billCity")).sendKeys(Constants.CITY);
		driver.findElement(By.name("billState")).sendKeys(Constants.STATE);
		driver.findElement(By.name("billZip")).sendKeys(Constants.ZIP_CODE);
		new Select(driver.findElement(By.name("billCountry"))).selectByVisibleText(Constants.COUNTRY);
		
		// Delivery Address
		driver.findElements(By.name("ticketLess")).get(1).click();
		driver.findElement(By.name("delAddress1")).sendKeys(Constants.ADDRESS);
		driver.findElement(By.name("delCity")).sendKeys(Constants.CITY);
		driver.findElement(By.name("delState")).sendKeys(Constants.STATE);
		driver.findElement(By.name("delZip")).sendKeys(Constants.ZIP_CODE);
		new Select(driver.findElement(By.name("delCountry"))).selectByVisibleText(Constants.COUNTRY);
		
		// Delay to wee what is happening.
		Thread.sleep(1000);
		
		// Accept alert.
		driver.switchTo().alert().accept();
		
		// Delay to wee what is happening.
		Thread.sleep(1000);
		
		// Submit form.
		driver.findElement(By.name("buyFlights")).submit();	
		
		// Delay to wee what is happening.
		Thread.sleep(5000);
	}

	@Then("I should get confirmation message for my trip")
	public void i_should_get_confirmation_message_for_my_trip()
	{
		// Xpath for confirmation text.
		String confirmationXpath = "/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/p/font/b/font[2]";
	    
		// Find confirmation message.
		WebElement confirmation = driver.findElement(By.xpath(confirmationXpath));
	    
		// Show confirmation message.
		System.out.println(confirmation.getText());
	}
	
	@After("@booking")
	public void CleanUp()
	{
		if (driver != null)
			driver.quit();
	}
}
