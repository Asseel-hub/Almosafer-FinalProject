package homepage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTest {

	WebDriver driver = new ChromeDriver();
	String almosaferURL = "https://global.almosafer.com/en";
	String ExpectedDefaultLanguge = "en";
	Random rand = new Random();

	@BeforeTest
	public void mySetUp() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(almosaferURL);
		driver.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']")).click();

	}

	@Test(priority = 1)
	public void CheckDefaultLangugeIsEnglish() {
		String ActualLanguge = driver.findElement(By.tagName("html")).getAttribute("lang");
		System.out.println(ActualLanguge);
		Assert.assertEquals(ActualLanguge, ExpectedDefaultLanguge);

	}

	@Test(priority = 2)
	public void CheckDefultCurrency() {
		String ExpectedCurrency = "SAR";
		WebElement currency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
		String ActualCurrency = currency.getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String ExpectedcontactNumber = "+966554400000";
		WebElement ContactNumber = driver.findElement(By.tagName("strong"));
		String ActualcontactNumber = ContactNumber.getText();
		Assert.assertEquals(ActualcontactNumber, ExpectedcontactNumber);

	}

	@Test(priority = 4)
	public void CheckQitafLogo() {
		boolean ExpectedQitafLogo = true;
		// XPATH -- THE SLOWEST
		WebElement footer = driver.findElement(By.tagName("footer"));
		// boolean
		// ActualQitafLogo=footer.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed();
		// NESTED
		WebElement div = footer.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"));
		WebElement logo = div.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		boolean AcutalQitafLogo = logo.isDisplayed();
		Assert.assertEquals(AcutalQitafLogo, ExpectedQitafLogo);
	}

	@Test(priority = 5)
	public void TestHotelTabIsNotSelected() {
		String ExpectedValue = "false";
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		// System.out.println(HotelTab.getAttribute("id"));
		// getAttribute --- retrieve value of attribute DtatType String
		String ActualValue = HotelTab.getAttribute("aria-selected");
		Assert.assertEquals(ActualValue, ExpectedValue);

	}

	@Test(priority = 6)
	public void CheckDepatureAndReturnDate() {
		// static LocalDate now() -- LocalDate : It is used to obtain the current date
		// from the system clock in the default time-zone.
		LocalDate todayDate = LocalDate.now();

		System.out.println(todayDate.getDayOfWeek()); // The function returns the day of the week and not null.

		int Today = todayDate.getDayOfMonth();
		int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
		int TheDayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();
		System.out.println(Today);
		System.out.println(Tomorrow);
		System.out.println(TheDayAfterTomorrow);
		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));
		String ActualDepatureDate = depatureAndArrivalDates.get(0).getText();
		int ActualDepatureDateAsInt = Integer.parseInt(ActualDepatureDate);
		String ActualReturnDate = depatureAndArrivalDates.get(1).getText();
		int ActualReturnDateAsInt = Integer.parseInt(ActualReturnDate);
		Assert.assertEquals(ActualDepatureDateAsInt, Tomorrow);
		Assert.assertEquals(ActualReturnDateAsInt, TheDayAfterTomorrow);

	}

	@Test(priority = 7)
	public void RandomlyChangeTheLanguage() {

		String[] Urls = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int RandomIndexUrl = rand.nextInt(Urls.length);
		driver.get(Urls[RandomIndexUrl]);

	}

	@Test(priority = 8)
	public void RandomlyChangeSearchForHotels() {

		WebElement hotelsTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelsTab.click();
		WebElement hotelSearch = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		//"input[placeholder='Search for hotels or places']"
		hotelSearch.click();
		String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };
		String[] ArabicCities = { "دبي", "جدة" };

		if (driver.getCurrentUrl().contains("en")) {
			int RandomIndex = rand.nextInt(EnglishCities.length);
			hotelSearch.sendKeys(EnglishCities[RandomIndex]);

		} else if (driver.getCurrentUrl().contains("ar")) {
			int RandomIndex = rand.nextInt(ArabicCities.length);
			hotelSearch.sendKeys(ArabicCities[RandomIndex]);

		}

	}

}
