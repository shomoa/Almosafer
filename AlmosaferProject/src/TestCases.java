import java.awt.Window;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases extends ParameterClass {

	@BeforeTest
	public void MyBeforTest() {
		Driver.get(Website);
	    Driver.manage().window().maximize();

	}

	@Test(priority=1)
	public void CheckLanguage() {
		String ActualLanguage = Driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/a[1]"))
				.getText();
		Assert. assertEquals(ActualLanguage, ExpectedLanguageArabic, "The expectd language equal the Actual Lang");
	}

	@Test(priority=2)
	public void CheckCurrency() {

		String ActualCurrency = Driver
				.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/div[1]/div/button")).getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency, "The expectd Currency equal the Actual Curr");
	}

	@Test(priority=3)
	public void CheckContactNumber() {

		String ActualContactNumber = Driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/a[2]/strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);

	}

    @Test(description = "Test # 4", priority=4) 
    public void CheckQitafLogoIsThere() {
    	//String QitafLogo=Driver.findElement(By.xpath("//*[@id=\"__next\"]/footer/div[3]/div[3]/div[1]/div[2]/div/div[2]/svg")).getText();
    	
    	WebElement MyFooter = Driver.findElement(By.tagName("footer"));
    	
    	List <WebElement> MyListOfSVGImagesInFooter = MyFooter.findElements(By.tagName("svg"));
    	

    	for(int i=0 ; i<MyListOfSVGImagesInFooter.size();i++) 
    	{    	  
    		String CheckAttribute = MyListOfSVGImagesInFooter.get(i).getAttribute("data-testid");

    		if(CheckAttribute == null) continue;
    		else if(CheckAttribute.equals("Footer__MasterCardLogo")) {
    			ActiualValueOfQitafIsThere = true;
    			System.out.println("Finally  done");
    		}
    		
    		
         	//System.out.println(MyListOfSVGImagesInFooter.get(i).getAttribute("data-testid"));
    		
    		
    
    	//System.out.println(CheckAttribute);
    	//if(CheckAttribute=="Footer__QitafLogo") System.out.println(CheckAttribute);
    	}
    	Assert.assertEquals(ActiualValueOfQitafIsThere, true);
    	
    }
   //in inspect data-testid="Footer__QitafLogo"

    
    @Test(description = "Test # 5",priority=5)
    public void Hotels_Tab_Not_selected() {
    	WebElement Hotel_tab = Driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tab-hotels\"]"));
    
      		ExpectedAttValue = "false";
      		String Actual = Hotel_tab.getAttribute("aria-selected");
    		
    		Assert.assertEquals(Actual, ExpectedAttValue, "The Hotel tab search not selected");	 	
    }
    //the default date of departure in website is the date+1
    @Test(description ="Test # 6 ",priority=6)
    public void Check_departure_date() {
    	//Date TodayDate=new Date();
    	Date today = new Date();
    	int currentDateInt= today.getDate();
    	String ActualDateOfDepartureInWebsite=Driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-flights\"]/div/div[2]/div[1]/div/div[3]/div/div/div[1]/div[1]/span[2]")).getText();
        int ActualDateInt= Integer.parseInt(ActualDateOfDepartureInWebsite);
    	Assert.assertEquals(ActualDateInt, currentDateInt+1);
    }
    ///////////////////////
    @Test(description ="Test # 7 ",priority=7)
    public void Check_return_date() {
    	//Date TodayDate=new Date();
    	LocalDate today = LocalDate.now();
    	int currentDateInt= today.getDayOfMonth();
    	System.out.println(currentDateInt);
        String ActualDateOfDepartureInWebsite=Driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-flights\"]/div/div[2]/div[1]/div/div[3]/div/div/div[1]/div[2]/span[2]")).getText();
        int ActualDateInt= Integer.parseInt(ActualDateOfDepartureInWebsite);
    	System.out.println(ActualDateInt);
    	Assert.assertEquals(ActualDateInt, currentDateInt+2);
    }
   ///////////////////////////////////
    @Test(description ="Test # 8 ", invocationCount = 1,priority=8)
    public void Check_Language(){
    	
    	//swap between languages randomly
      String [] WebSiteURLs = {"https://global.almosafer.com/en","https://global.almosafer.com/ar"};
      int index =rand.nextInt(0,2);
      Driver.get(WebSiteURLs[index]);  
      
      //check if the swapping assert to ar and en languages or not

      if(Driver.getCurrentUrl().contains("ar")) {
      String Lang = Driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/a[1]")).getText();

      Assert.assertEquals(Lang, ExpectedLanguageEnglish);
      }
      else {
     String Lang = Driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/a[1]")).getText();

      Assert.assertEquals(Lang, ExpectedLanguageArabic); 	
      }
    }
    
    @Test(description="Test # 9", priority=9)//invocationCount = 3
    //i try to reopen Chrome multi times because the pop up currency message not appears except at the first launching of browser
    public void Search_in_hotel_tab() throws InterruptedException {
    	//before launch any error for no such element wait for 3 seconds
    	Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
   // 	WebDriver Driver = new ChromeDriver();
    //	  Driver.get(Website);
     	 String [] WebSiteURLs = {"https://global.almosafer.com/en","https://global.almosafer.com/ar"};
	      int index =rand.nextInt(0,2);
	      Driver.get(WebSiteURLs[index]);  	      
         String [] ArabicCountry= {"جدة","دبي"};
         int ArabIndex=rand.nextInt(0,2);
         String [] EnglishCountry= {"Dubai","Jeddah","Riyadh"};
         int EnglishIndex=rand.nextInt(0,3);
    	      if(Driver.getCurrentUrl().contains("ar")) {
    	    	//pop-up message  
    	    	Driver.findElement(By.cssSelector("body > div.js-modal-root > div > div.sc-jWojfa.kwDONK > div >div > div > button.sc-jTzLTM.eJkYKb.cta__button.cta__saudi.btn.btn-primary")).click();  
    	       
    	    	  Driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tab-hotels\"]")).click();
    	    	System.out.println("arab");
    	    	Thread.sleep(3000);    	       
    	    	WebElement Search_bar = Driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[1]/div/div/div/div/input"));
    	        Search_bar.sendKeys(ArabicCountry[ArabIndex]+Keys.ARROW_DOWN);
    	        // to click enter over the first option of drop down menu but enter in this website not active so click on search button
    	          WebElement search_button = Driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[4]/button"));
    	          //Search_bar.sendKeys(Keys.chord(Keys.ARROW_DOWN)+Keys.ENTER);
    	          search_button.click();  
    	      }
    	      else {
    	    Driver.findElement(By.cssSelector("body > div.js-modal-root > div > div.sc-jWojfa.kwDONK > div > div > div > button.sc-jTzLTM.hQpNle.cta__button.cta__continue.btn.btn-primary")).click();
    

    	    	  Driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tab-hotels\"]")).click();
    	    	  System.out.println("Eng");
    	    	  Thread.sleep(3000); 
    	    	  WebElement Search_bar = Driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[1]/div/div/div/div/input"));
      	          Search_bar.sendKeys(EnglishCountry[EnglishIndex]+Keys.ARROW_DOWN);
      	          // to click enter over the first option of drop down menu but enter in this website not active so click on search button
      	        //   Search_bar.sendKeys(Keys.chord(Keys.ARROW_DOWN)+Keys.ENTER);
      	          WebElement search_button = Driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[4]/button"));
      	      
      	          search_button.click();      	        
    	      }
    	
    }
    
    @Test(description = "test case # 10", priority = 10)
    public void NumberOfvisitorsInHotel() {
    	WebElement NumOfVistors = Driver.findElement(By.xpath("//*[@id=\"__next\"]/section[2]/div/section/div/div/div/div/div[3]/div/select"));
    	Select MySelector = new Select(NumOfVistors);
    	//MySelector.selectByValue("B"); A B C value in selector in drop down list
    	//to select randomly from drop down list
    	int myindex=rand.nextInt(0,2);
    	MySelector.selectByIndex(myindex);
    	
    }
    
    @Test(description = "Test # 11",priority = 11)
    public void searchOnHotle() {
    	WebElement seartchButton = Driver.findElement(By.xpath("//*[@id=\"__next\"]/section[2]/div/section/div/div/div/div/div[4]/button"));
    	seartchButton.click();
    	}
    @Test(description = "Test # 12" , priority = 12)
    public void Full_loading(){
    //wait until fully searching complete then you can find the result as element
    	Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    	WebElement Result = Driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/section/span"));
    	String ResultText= Result.getText();
    	
    	System.out.println(ResultText);
    	boolean ActualResult= ResultText.contains("found")||ResultText.contains("وجدنا");
    	Assert.assertEquals(ActualResult, true);
    			}
  
    @Test(description = "Test # 13" , priority = 13)
    public void Sorting_from_lowest_to_highest_price() throws InterruptedException{
    WebElement Lowest_price = Driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/section[1]/div/button[2]"));
    Lowest_price.click() ; 
   //Always take care about time when you move between tabs there are need times
   Thread.sleep(3000);
    //    WebElement thefirstPrice = Driver.findElement(By.xpath("//*[@id=\"hotelCard-2183321\"]/div[3]/div/div/div/div[1]/div/span[2]"));
//	String FirstPriceText= thefirstPrice.getText();
//	int firstPriceInt = FirstPriceText.
//			 WebElement theLastPrice = Driver.findElement(By.xpath("//*[@id=\"hotelCard-1275434\"]/div[3]/div/div[2]/div/div[1]/div/span[2]"));
//				String LastPriceText= thefirstPrice.getText();
//				int lastPriceInt = FirstPriceText.
//						
//    boolean result = firstPriceInt< lastPriceInt;
//	Assert.assertEquals(result, true);
    //the erea that i want to search in instead of all page of driver which is the right side
    WebElement theDivWindow =Driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]"));
//    first way list to compare the previous price with the next one	
    List <WebElement> prices = theDivWindow.findElements(By.className("Price__Value"));
//    for(int i=0; i<prices.size();i++) {
   	
    String StringFirstPrice = prices.get(0).getText();
    String StringLastPrice = prices.get(prices.size()-1).getText();

    int IntFirstPrice = Integer.parseInt(StringFirstPrice);
    int IntLastPrice = Integer.parseInt(StringLastPrice);
    Assert.assertEquals(IntFirstPrice< IntLastPrice, true);
    	}
    		
	@AfterTest
	public void MyAfterTest() {
	}
}
