package game;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.HashMap;
import java.util.regex.Pattern;
public class WebConnector {
    WebDriver driver;
    public WebConnector()
    {

        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--disable-gpu\", \"--window-size=1920,1200\",\"--ignore-certificate-errors");
        driver=new FirefoxDriver(options);
        driver.navigate().to("http://react-poker.surge.sh/");
    }
    public int getPot()
    {
        Integer pot=Integer.parseInt((driver.findElement(By.xpath("//div[@class='pot-container']/h4")).getText()));
        return pot;
    }
    public HashMap<String,String> getFirstCard()
    {
        HashMap<String,String>card = new HashMap<String, String>();
        String card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[1]/h6")).getText());
        String Number=card1.substring(0,card1.length()-2);
        if(card1.charAt(card1.length())=='♣'){
            card.put("clubs",Number);
        }
        if(card1.charAt(card1.length())=='♠'){
            card.put("clubs",Number);
        }
        if(card1.charAt(card1.length())=='♣'){
            card.put("Diamonds",Number);
        }
        if(card1.charAt(card1.length())=='♦'){
            card.put("spades",Number);
        }
        if(card1.charAt(card1.length())=='♥'){
            card.put("spades",Number);
        }
        return card;
    }
    public HashMap<String,String> getSecondCard()
    {
        HashMap<String,String>card = new HashMap<String, String>();
        String card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[2]/h6")).getText());
        String Number=card1.substring(0,card1.length()-2);
        if(card1.charAt(card1.length())=='♣'){
            card.put("clubs",Number);
        }
        if(card1.charAt(card1.length())=='♠'){
            card.put("clubs",Number);
        }
        if(card1.charAt(card1.length())=='♣'){
            card.put("Diamonds",Number);
        }
        if(card1.charAt(card1.length())=='♦'){
            card.put("spades",Number);
        }
        if(card1.charAt(card1.length())=='♥'){
            card.put("spades",Number);
        }
        return card;
    }
}
