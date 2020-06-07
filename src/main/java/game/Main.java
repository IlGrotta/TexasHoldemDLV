package game;

import dlv.SceltaAvversario;
import javafx.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.sql.Driver;
import java.util.regex.Pattern;
import  dlv.WebProbability888;
public class Main {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        /* WebConnector prova=new WebConnector();
        System.out.println(prova.getFirstCard().getKey());
        System.out.println(prova.getFirstCard().getValue());
        System.out.println(prova.getSecondCard().getKey());
        System.out.println(prova.getSecondCard().getValue());
        System.out.println(prova.getCards());
        prova.closeWeb();*/
        WebProbability888 prob=new WebProbability888();
       /* Pair<String,String> cardp1=new Pair<String, String>("hearts","9");
        Pair<String,String> cardp2=new Pair<String, String>("clubs","9");
        prob.setPlayerCards(cardp1,cardp2);
        Pair<String,String> card=new Pair<String, String>("diamonds","9");
        Pair<String,String> card1=new Pair<String, String>("clubs","3");
        Pair<String,String> card2=new Pair<String, String>("hearts","3");
        prob.setcards(card,1);
        prob.setcards(card1,2);
        prob.setcards(card2,3);
        System.out.println(prob.GetProbabilityVictory());
      //  prob.resetTable();
        Thread.sleep(1000);*/
       prob.setAvversario(3);
       prob.setAvversario(4);
        prob.closeWeb();
    }
     /*
            WebElement slider= (driver.findElement(By.xpath("//div[@class='slider-handles']/div")));
            Actions move=new Actions(driver);
            Action action=(Action) move.dragAndDropBy(slider,300    ,0).build();
            action.perform();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@class='action-button']")).click();
            Thread.sleep(200);*/
}
