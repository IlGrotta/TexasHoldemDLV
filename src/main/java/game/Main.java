package game;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        FirefoxDriver driver=new FirefoxDriver();

        driver.get("http://react-poker.surge.sh/");




        //driver.findElement(By.className("fold-button")).click();


        boolean myTurn;

        for(int x=0;x<4;x++){
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

        }

       // driver.findElement(By.xpath("//div[@class='action-buttons']/button[1]")).click();
        Thread.sleep(3000);
        //String pot= driver.findElement(By.xpath("//div[@class='pot-container']/h4")).getText();
      //  System.out.println(pot);





        driver.close();


        System.exit(0);





    }
}
