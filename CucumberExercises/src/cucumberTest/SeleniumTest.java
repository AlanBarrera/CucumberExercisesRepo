package cucumberTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTest {

	public static WebDriver driver;
	
	public static void main(String[] args)
	{
		// Set the Gecko Driver.
		System.setProperty("webdriver.gecko.driver", "/Users/alanbarrera/git/CucumberExercisesRepo/CucumberExercises/geckodriver");
		
		try
		{
			// Run the exercises.
			ScenarioOne();
	
		}
		catch(Exception ex)
		{
			// Print in the console any error.
			System.out.println("An error has ocurred: " + ex);
		}
		finally
		{
			// Make sure to quit the driver.
			if(driver != null)
			{
				driver.quit();
			}
				
		}
	}
	
	public static void ScenarioOne() throws InterruptedException
	{
		// Launch a new Firefox browser.
		driver = new FirefoxDriver();
				
		// Open http://newtours.demoaut.com/.
		driver.get(Constants.HOME_URL);
		
		// Click on Register link
		driver.findElement(By.linkText("REGISTER")).click();
		
		// Delay to see what is happening.
		Thread.sleep(1500);
		
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
		Thread.sleep(1000);
		
		// Check whether the registration was successful.
		boolean isRegistrationSuccessful = driver.getCurrentUrl().equalsIgnoreCase(Constants.SUCCESSFUL_REGISTRATION_URL);
		
		// Print the result.
		System.out.println("The registration was " + (isRegistrationSuccessful ? "successful" : "unsuccessful") + ".");
		
		// Delay to see what is happening.
		Thread.sleep(3000);
		
		// Close the browser.
		driver.quit();
	}

}
