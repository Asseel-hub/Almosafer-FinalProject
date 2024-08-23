package homepage;
import java.awt.RenderingHints.Key;
import java.security.KeyStoreSpi;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.html.HTMLBaseElement;

public class MyTest extends Parameters{
	//By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']")
	@BeforeTest
	public void mySetUp() {
		 GeneralSetup();
		 WebElement GreenButton= driver .findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
         GreenButton.click();
		 
	}

	@Test(priority = 1)
	public void CheckDefaultLangugeIsEnglish() {
		String ActualLanguge = driver.findElement(By.tagName("html")).getAttribute("lang");
		System.out.println(ActualLanguge);
		Assert.assertEquals(ActualLanguge, ExpectedDefaultLanguge);

	}

	@Test(priority = 2)
	public void CheckDefultCurrency() {
		//WebElement currency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
		//String ActualCurrency = currency.getText();
		String ActualCurrency=driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
		Assert.assertEquals(ActualCurrency,ExpectedCurrency);

	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String ActualcontactNumber = driver.findElement(By.tagName("strong")).getText();
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
		//WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		// System.out.println(HotelTab.getAttribute("id"));
		// getAttribute --- retrieve value of attribute DtatType String
		//String ActualValue = HotelTab.getAttribute("aria-selected");		
		String ActualValue = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
		Assert.assertEquals(ActualValue, ExpectedValue);
	}

	@Test(priority = 6)
	public void CheckDepatureAndReturnDate() {
		// static LocalDate now() -- LocalDate : It is used to obtain the current date
		// from the system clock in the default time-zone.
		
		/*LocalDate todayDate = LocalDate.now();

		System.out.println(todayDate.getDayOfWeek()); // The function returns the day of the week and not null.

		int Today = todayDate.getDayOfMonth();
		int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
		int TheDayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();
		System.out.println(Today);
		System.out.println(Tomorrow);
		System.out.println(TheDayAfterTomorrow);*/
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
		 RandomSelectTheLanguageOfTheWebSite();
	}

	@Test(priority = 8)
	public void RandomlyChangeSearchForHotels() {

		WebElement hotelsTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelsTab.click();
		WebElement hotelSearch = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		//"input[placeholder='Search for hotels or places']"
		//hotelSearch.click();
		
		if (driver.getCurrentUrl().contains("en")) {
			hotelSearch.sendKeys(EnglishCities[RandomIndexcitesEn]);

		} else if (driver.getCurrentUrl().contains("ar")) {
			hotelSearch.sendKeys(ArabicCities[RandomIndexcitesAr]);
		
		}
        // hotelSearch.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
		
		WebElement ListOfLocation = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		WebElement firstResult= ListOfLocation.findElements(By.tagName("li")).get(1);
		firstResult.click();
		
		
	}
	
	@Test(priority = 9)
	public void RandomlySelectTheNumberOfVistor() {
		
		WebElement selectorOfTheVistor = driver.findElement(By.xpath("// select [@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select select = new  Select(selectorOfTheVistor);
		//select.selectByIndex(1);
		//select.selectByValue("B");
		//select.selectByVisibleText("1 Room, 1 Adult, 0 Children");
		select.selectByIndex(randomIndex);
		WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchButton.click();
	}
	
	@Test(priority = 10)
	public void CheckThePageFullyLoaded() throws InterruptedException {
	     Thread.sleep(10000);
	 	String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
	     boolean finished = results.contains("وجدنا") || results.contains("found"); 
		Assert.assertEquals(finished, expectedResult);
		
	}
	
	@Test(priority = 11)
	public void SortItemsLowestToHighestPrice() {
		
		WebElement lowestPriceButton =driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		lowestPriceButton.click();
		WebElement pricesContainer= driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> allPrices =pricesContainer.findElements(By.className("Price__Value"));
	String lowestPrice= allPrices.get(0).getText();
	String highestPrice= allPrices.get(allPrices.size()-1).getText();
	System.out.println(lowestPrice);
	System.out.println(highestPrice);
	
		int lowestPriceAsInt = Integer.parseInt(lowestPrice);
		int highestPriceAsInt=Integer.parseInt(highestPrice);
		
		boolean ActualResults=lowestPriceAsInt< highestPriceAsInt;
		Assert.assertEquals(ActualResults, expectedResultsSort);
		
	}
	
	
	
	
	
	
	
	

}
