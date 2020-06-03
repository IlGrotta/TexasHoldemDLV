package game;
import dlv.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public abstract class State {
    protected DlvChoice dlv;
    protected WebDriver driver;
    protected Budget budget;
    protected String pathIA;
    protected String sceltaAvversario;
    protected static ArrayList<HashMap<String, Integer>>behaviour;
    protected static int numRound = 0;
    protected static int numPhases = 0;
    static
    {
        behaviour=new ArrayList<HashMap<String, Integer>>();
        for(int i=0;i<4;i++)
        {
            behaviour.add(new HashMap<String, Integer>());
        }
        for(int i=0;i<4;i++)
        {
            behaviour.get(i).put("raise",0);
            behaviour.get(i).put("fold",0);
            behaviour.get(i).put("call",0);
        }

    }
    public State(WebDriver driver, Budget budget, String pathIA)
    {
        this.pathIA = pathIA;
        this.budget = budget;
        this.driver = driver;
        dlv = new DlvChoice();
        sceltaAvversario = "";
    }
    protected boolean Algorithm(String dealer) throws InterruptedException {
        boolean myTurn;
        Boolean existPlayer;
        do{
            do{

                myTurn=(driver.findElements( By.xpath("//button[@class='action-button']") ).size()!=0);
            }while (!myTurn);

            WebElement slider= (driver.findElement(By.xpath("//div[@class='slider-handles']/div")));
            Actions move=new Actions(driver);
            Action action=(Action) move.dragAndDropBy(slider,300    ,0).build();
            action.perform();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[@class='action-button']")).click();
            Thread.sleep(100);
            existPlayer=(driver.findElements( By.xpath("//div[@class='player-avatar--container']") ).size()!=0);
        } while(!existPlayer);
        return false;
    }


}
