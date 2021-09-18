// Page class for Infogix - Contains relevant WebElements and methods to perform tests
package interviewTests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Infogix_HomePage {

	@FindBy(xpath="//a[contains(text(),'Contact')]")
	protected WebElement CONTACT;

	@FindBy(xpath="//input[@id='FirstName']")
	protected WebElement CONTACT_FIRSTNAME;

	@FindBy (xpath ="//input[@id='LastName']")
	protected WebElement CONTACT_LASTNAME;

	@FindBy (xpath="//input[@id='Company']")
	protected WebElement CONTACT_COMPANYNAME;

	@FindBy(xpath ="//input[@id='Email']")
	protected WebElement CONTACT_WORKEMAIL; 

	@FindBy  (xpath ="//input[@id='Phone']")
	protected WebElement CONTACT_PHONENUMBER;

	@FindBy  (xpath ="//select[@id='HQ_Location_Country__c']")
	protected WebElement CONTACT_HQCOUNTRY; 

	@FindBy(xpath = "//select[@id='Industry__c']")
	protected WebElement CONTACT_INDUSTRY; 

	@FindBy(xpath = "//textarea[@id='Next_Step_Comments__c']")  
	protected WebElement CONTACT_COMMENTS; 

	@FindBy (xpath = "//label[@id='LblConsent_to_Processing__c']") 
	protected WebElement CONTACT_AGREECHECKBOX; 

	@FindBy (xpath ="//button[@type='submit']")
	protected WebElement CONTACT_SUBMIT;

	protected By SEARCH_BOX= By.cssSelector("[class='search-site'] svg");


	@FindBy(css="a[class='search-site']+form input")
	protected By SEARCH_INPUT= By.cssSelector("a[class='search-site']+form input");



	protected By CONTACT_SUBMITFORM_SUCCESS= By.xpath("//h1[contains(text(),'Thank You!')]");
	protected By SEARCH_RESULTS= By.xpath("//h1[contains(text(),'Search Results')]");
	static String url="https://www.infogix.com/";
	WebDriverWait wait;


	protected void selectUsingIndex(WebElement element, int index) {
		Select industry=new Select(element);
		industry.selectByIndex(index);

	}

	protected void selectUsingVisbleText(WebElement element, String visibleText ){
		Select country=new Select(element);
		country.selectByVisibleText(visibleText);

	}


	protected void fillContactForm(WebDriver driver) {


		//Enter Form Values
		CONTACT.click();
		CONTACT_FIRSTNAME.sendKeys("First");
		CONTACT_LASTNAME.sendKeys("Last");
		CONTACT_COMPANYNAME.sendKeys("Infogix HR");
		CONTACT_WORKEMAIL.sendKeys("xyz@gmail.com");
		CONTACT_PHONENUMBER.sendKeys("9191919191");
		CONTACT_COMMENTS.sendKeys("This is a coding challenge for Test Automation position. Please disregard this message");
		CONTACT_AGREECHECKBOX.click();

		//Select dropdowns
		selectUsingVisbleText(CONTACT_HQCOUNTRY,"United States");	
		selectUsingIndex(CONTACT_INDUSTRY,1);

		//Submit form
		CONTACT_SUBMIT.click();

		//Check for Success message
		wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_SUBMITFORM_SUCCESS));
		Assert.assertTrue(driver.findElement(CONTACT_SUBMITFORM_SUCCESS).isDisplayed());

	}

	public void searchAndVerifyLink(WebDriver driver , String searchText) {

		
		driver.findElement(SEARCH_BOX).click();
		
		List<WebElement> lst = driver.findElements(SEARCH_INPUT);
		lst.get(0).sendKeys(searchText);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(SEARCH_INPUT).sendKeys(Keys.ENTER);
		
		//Check for Success message
		wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_RESULTS));
		Assert.assertTrue(driver.findElement(SEARCH_RESULTS).isDisplayed());



	}

}
