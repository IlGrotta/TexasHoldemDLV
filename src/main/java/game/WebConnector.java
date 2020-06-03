package game;
import javafx.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebConnector {
    WebDriver driver;
    public WebConnector() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--disable-gpu\", \"--window-size=1920,1200\",\"--ignore-certificate-errors");
        driver=new FirefoxDriver(options);
        driver.navigate().to("http://react-poker.surge.sh/");
        Thread.sleep(10000);
    }
    public int getPot()
    {

        Integer pot=Integer.parseInt((driver.findElement(By.xpath("//div[@class='pot-container']/h4")).getText()));
        return pot;
    }
    public Pair<String, String> getFirstCard()
    {
        Pair<String,String>card = null;
        String card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[1]/h6")).getText());
        String Number=card1.substring(0,card1.length()-2);
        if(card1.charAt(card1.length()-1)=='♣'){
            card=new Pair<String, String>("clubs",Number);
        }
        if(card1.charAt(card1.length()-1)=='♠'){
            card=new Pair<String, String>("diamonds",Number);
        }
        if(card1.charAt(card1.length()-1)=='♦'){
            card=new Pair<String, String>("spades",Number);
        }
        if(card1.charAt(card1.length()-1)=='♥'){
            card=new Pair<String, String>("hearts",Number);
        }
        return card;
    }

    public Pair<String, String> getSecondCard()
    {
        Pair<String,String>card = null;
        String card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[2]/h6")).getText());
        String Number=card1.substring(0,card1.length()-2);
        if(card1.charAt(card1.length()-1)=='♣'){
            card=new Pair<String, String>("clubs",Number);
        }
        if(card1.charAt(card1.length()-1)=='♠'){
            card=new Pair<String, String>("diamonds",Number);
        }
        if(card1.charAt(card1.length()-1)=='♦'){
            card=new Pair<String, String>("spades",Number);
        }
        if(card1.charAt(card1.length()-1)=='♥'){
            card=new Pair<String, String>("hearts",Number);
        }
        return card;
    }
    public void closeWeb()
    {
        driver.close();
    }
}
