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

import java.sql.Driver;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--disable-gpu\", \"--window-size=1920,1200\",\"--ignore-certificate-errors");
        WebDriver driver=new FirefoxDriver(options);
        driver.navigate().to("http://react-poker.surge.sh/");


        //driver.findElement(By.className("fold-button")).click();

        boolean playerexis;
        boolean myTurn;
        boolean win=Boolean.FALSE;
        do{
            playerexis=Boolean.FALSE;
            do{

                 myTurn=(driver.findElements( By.xpath("//button[@class='action-button']") ).size()!=0);
            }while (!myTurn);

            WebElement slider= (driver.findElement(By.xpath("//div[@class='slider-handles']/div")));
            Actions move=new Actions(driver);
            Action action=(Action) move.dragAndDropBy(slider,300    ,0).build();
            action.perform();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@class='action-button']")).click();
            Thread.sleep(200);
            //*[@id="root"]/div/div/div/div[1]/div[4]/div[3]/div/img
            //*[@id="root"]/div/div/div/div[1]/div[2]/div[3]/div/img
            //*[@id="root"]/div/div/div/div[1]/div[3]/div[3]/div/img

            win=(driver.findElements( By.xpath("//*[@id=\"root\"]/div/div/div") ).size()!=0);
            System.out.println(playerexis);
            System.out.println(win);
        }while(playerexis || !win);
        System.out.println("s");
       // driver.findElement(By.xpath("//div[@class='action-buttons']/button[1]")).click();
        Thread.sleep(3000);
        //String pot= driver.findElement(By.xpath("//div[@class='pot-container']/h4")).getText();
      //  System.out.println(pot);




        driver.close();


        System.exit(0);





    }
   /* protected String checkSceltaAvversario()
    {
        Integer dump = getPrezzoCall();
        if(dump == null)
            return "";
        //*[@id="root"]/div/div/div/div[1]/div[1]/div[1]
        //*[@id="root"]/div/div/div/div[1]/div[3]/div[1]
        //*[@id="root"]/div/div/div/div[1]/div[5]/div[3]/div/h5
    }
    protected Integer getPrezzoCall()
    {
        Integer value = null;
        while(value == null)
        {
            try
            {
                value = Integer.valueOf(driver.findElement(By.xpath("/html[1]/body[1]/div[7]/font[1]/font[1]")).getText());
            }catch(Exception e){
                if(driver.findElement(By.id("call-button")).getText().equals("Go on"))
                    return null;
            }
        }

        return value;
    }*/

}
