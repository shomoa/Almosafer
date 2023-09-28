import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ParameterClass {
	//I type static for all elements in webdriver so no need to type static for each parameters
  static WebDriver Driver = new ChromeDriver();
  String Website="https://global.almosafer.com/en";
  String ExpectedLanguageArabic="العربية";
  String ExpectedLanguageEnglish="English";
 String ExpectedCurrency = "SAR";

 String ExpectedContactNumber = "+966554400000";
 boolean ActiualValueOfQitafIsThere;
 String ExpectedAttValue;
 Random rand =new Random();
}
