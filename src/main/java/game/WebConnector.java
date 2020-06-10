package game;
import dlv.Card;
import javafx.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;

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
    public ArrayList<Card> getCards()
    {
        ArrayList<Card> cards=new ArrayList<Card>();
        Boolean notexist=Boolean.FALSE;
        Integer i=1;
        do {

            if(driver.findElements( By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div[6]/div["+String.valueOf(i)+"]/h6") ).size()!=0)
            {
                Card card = null;
                String card1=(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div[6]/div["+String.valueOf(i)+"]/h6")).getText());
                String Number=card1.substring(0,card1.length()-2);
                Integer num=Integer.parseInt(Number);
                if(!(num>=2 && num<=10)){
                    if(Number.equals("J")){
                        num=11;
                    }
                    if(Number.equals("Q")){
                        num=12;

                    }
                    if(Number.equals("K")){
                        num=13;

                    }
                    if(Number.equals("A")){
                        num=14;

                    }
                }
                if(card1.charAt(card1.length()-1)=='♣'){
                    card=new Card("clubs",num);
                    cards.add(card);
                }
                if(card1.charAt(card1.length()-1)=='♠'){
                    card=new Card("diamonds",num);
                    cards.add(card);
                }
                if(card1.charAt(card1.length()-1)=='♦'){
                    card=new Card("spades",num);
                    cards.add(card);
                }
                if(card1.charAt(card1.length()-1)=='♥'){
                    card=new Card("hearts",num);
                    cards.add(card);
                }
            }
            else
            {
                notexist=Boolean.TRUE;
            }
            i+=1;
        }while (!notexist);
        return cards;
    }
    public Card getFirstCard()
    {
        Card card = null;
        String card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[1]/h6")).getText());
        String Number=card1.substring(0,card1.length()-2);
        Integer num=Integer.parseInt(Number);
        if(!(num>=2 && num<=10)){
            if(Number.equals("J")){
                num=11;
            }
            if(Number.equals("Q")){
                num=12;

            }
            if(Number.equals("K")){
                num=13;

            }
            if(Number.equals("A")){
                num=14;

            }
        }
        if(card1.charAt(card1.length()-1)=='♣'){
            card=new Card("clubs",num);
        }
        if(card1.charAt(card1.length()-1)=='♠'){
            card=new Card("diamonds",num);
        }
        if(card1.charAt(card1.length()-1)=='♦'){
            card=new Card("spades",num);
        }
        if(card1.charAt(card1.length()-1)=='♥'){
            card=new Card("hearts",num);
        }
        return card;
    }
    public void Fold()
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div[1]/button[2]")).click();
    }

    public void Call()
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div[1]/button[1]")).click();
    }
    public Card getSecondCard()
    {
        Card card = null;
        String card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[2]/h6")).getText());
        String Number=card1.substring(0,card1.length()-2);
        Integer num=Integer.parseInt(Number);
        if(!(num>=2 && num<=10)){
            if(Number.equals("J")){
                num=11;
            }
            if(Number.equals("Q")){
                num=12;

            }
            if(Number.equals("K")){
                num=13;

            }
            if(Number.equals("A")){
                num=14;

            }
        }
        if(card1.charAt(card1.length()-1)=='♣'){
            card=new Card("clubs",num);
        }
        if(card1.charAt(card1.length()-1)=='♠'){
            card=new Card("diamonds",num);
        }
        if(card1.charAt(card1.length()-1)=='♦'){
            card=new Card("spades",num);
        }
        if(card1.charAt(card1.length()-1)=='♥'){
            card=new Card("hearts",num);
        }
        return card;
    }
    public void closeWeb()
    {
        driver.close();
    }

}
