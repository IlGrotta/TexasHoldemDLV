package game;

import dlv.SceltaAvversario;
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
import java.sql.Array;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Main {



    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {


        WebConnector prova=new WebConnector();

        System.out.println(prova.getFirstCard().getKey());
        System.out.println(prova.getFirstCard().getValue());
        System.out.println(prova.getSecondCard().getKey());
        System.out.println(prova.getSecondCard().getValue());
        System.out.println(prova.getCards());

        System.out.println(prova.dealerName());
       // prova.closeWeb();
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
