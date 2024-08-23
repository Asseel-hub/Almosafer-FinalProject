package homepage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

	WebDriver driver = new ChromeDriver();
	String almosaferURL = "https://global.almosafer.com/en";
	String ExpectedDefaultLanguge = "en";
	Random rand = new Random();
    
		
	String ExpectedCurrency = "SAR";
	String ExpectedcontactNumber = "+966554400000";
	boolean ExpectedQitafLogo = true;
	String ExpectedValue = "false";
	LocalDate todayDate = LocalDate.now();


	int Today = todayDate.getDayOfMonth();
	int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
	int TheDayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();
	
	String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };
	int RandomIndexcitesEn = rand.nextInt(EnglishCities.length);

	String[] ArabicCities = { "دبي", "جدة" } ;
	int RandomIndexcitesAr = rand.nextInt(ArabicCities.length);

	int randomIndex =rand.nextInt(2);
	
	boolean expectedResult=true ;
	
	boolean expectedResultsSort=true;

	public void RandomSelectTheLanguageOfTheWebSite() {
		
		String[] Urls = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int RandomIndexUrl = rand.nextInt(Urls.length);
		driver.get(Urls[RandomIndexUrl]);}
	
	
	public void GeneralSetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(almosaferURL);
	}
}

