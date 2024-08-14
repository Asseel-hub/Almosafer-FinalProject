package homepage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTest {

	WebDriver driver = new ChromeDriver();
	String almosaferURL="https://global.almosafer.com/en";
	String ExpectedDefaultLanguge="en";
	
	@BeforeTest
	public void mySetUp() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(almosaferURL);
		driver.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']")).click();
		
	}
	
	
	
	@Test (priority = 1)
	public void CheckDefaultLangugeIsEnglish() {
		String ActualLanguge = driver.findElement(By.tagName("html")).getAttribute("lang");
		System.out.println(ActualLanguge);
		Assert.assertEquals(ActualLanguge, ExpectedDefaultLanguge);
		
	}
	
	@Test (priority = 2)
	public void CheckDefultCurrency() {
		String ExpectedCurrency="SAR";
		WebElement currency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
		String ActualCurrency = currency.getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
		
	}
	
	@Test(priority = 3)
	public void CheckContactNumber() {
		String ExpectedcontactNumber="+966554400000";
		WebElement ContactNumber= driver.findElement(By.tagName("strong"));
		String ActualcontactNumber= ContactNumber.getText();
		Assert.assertEquals(ActualcontactNumber, ExpectedcontactNumber);
		
	}
	@Test (priority = 4)
	public void CheckQitafLogo() {
		boolean ExpectedQitafLogo=true;
		//XPATH -- THE SLOWEST
		WebElement footer = driver.findElement(By.tagName("footer"));
		//boolean ActualQitafLogo=footer.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed();
		//NESTED
		WebElement div=   footer.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"));
		WebElement logo=  div.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		boolean AcutalQitafLogo =logo.isDisplayed();
		Assert.assertEquals(AcutalQitafLogo, ExpectedQitafLogo);
	}
	
	
	
	
	
	
	
	
	
	
}
