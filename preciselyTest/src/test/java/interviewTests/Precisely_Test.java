package interviewTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Precisely_Test{
	
	WebDriver driver;
	
	@BeforeMethod
	public void launchbrowser() {
		driver=new ChromeDriver();
		driver.get(Infogix_HomePage.url);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}


	@Test
	public void testOne() {		
		try {
			Infogix_HomePage pageObj = PageFactory.initElements(driver, Infogix_HomePage.class);
			pageObj.fillContactForm(driver);
		}
		catch(Exception e) {
			System.out.println("An Exception has occured"+e);
		}
			finally {
				driver.close();
			}
	}

		@Test
	public void testTwo() {

			try {
				String searchText="govern";
				Infogix_HomePage pageObj = PageFactory.initElements(driver, Infogix_HomePage.class);
				pageObj.searchAndVerifyLink(driver,searchText );
			}
			catch(Exception e) {
				System.out.println("An Exception has occured"+e);
			}
				finally {
					driver.close();
				}
		}
	
	
	@AfterTest
	public void closeDriver() {		
	
			driver.quit();
		
	}

}

