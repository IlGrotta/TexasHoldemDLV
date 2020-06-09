package dlv;
import javafx.util.Pair;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.smartcardio.Card;

public class WebProbability888 {
    private WebDriver driver;
    public WebProbability888() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--headless","focus" ,"--disable-infobars", "--disable-extensions");
        driver=new FirefoxDriver(options);
        driver.navigate().to("https://www.888poker.com/poker/poker-odds-calculator");
        driver.manage().window().fullscreen();
    }
    public void setAvversario(int N)//l'avversario rientra nel turno attuale
    {
        int player=N;
        driver.findElement(By.xpath("/html/body/div[1]/section/div/div[4]/div/div/div/article/div/div/div/div/div/section[1]/div[2]/div["+String.valueOf(player)+"]/div[3]/p")).click();
    }
    public int GetProbabilityVictory()
    {
        String value=driver.findElement(By.xpath("/html/body/div[1]/section/div/div[4]/div/div/div/article/div/div/div/div/div/section[1]/div[2]/div[1]/div[3]/div[2]/p[1]/span[2]")).getText();
        return (int) Float.parseFloat(value.substring(0,value.length()-1));
    }
    public void setcards(Pair<String,String> card,int poscard)
    {
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/section/div/div[4]/div/div/div/article/div/div/div/div/div/section[1]/div[1]/div/div/ul/li["+String.valueOf(poscard)+"]/div/div/div[2]")));
        clickAndCheckPopup(By.xpath("/html/body/div[1]/section/div/div[4]/div/div/div/article/div/div/div/div/div/section[1]/div[1]/div/div/ul/li["+String.valueOf(poscard)+"]/div/div/div[2]"));
        putCard(card);
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.id("card-selection-complete")));
        clickAndCheckPopup(By.id("card-selection-complete"));
    }
    private void putCard(Pair<String,String>card)
    {
        int number=Integer.parseInt(card.getValue())-1;
        int seed=0;
        if(card.getKey().equals("clubs"))
        {
            seed=1;
        }
        if(card.getKey().equals("diamonds"))
        {
            seed=2;
        }
        if(card.getKey().equals("spades"))
        {
            seed=3;
        }
        if(card.getKey().equals("hearts"))
        {
            seed=4;
        }
        //da aggiustare il fatto che per come e ora non possiamo inserire numeri a meno di if
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/section/div/div[4]/div/div/div/article/div/div/div/div/div/section[2]/div[2]/div[1]/div["+String.valueOf(seed)+"]/a["+String.valueOf(number)+"]/span")));
        clickAndCheckPopup(By.xpath("/html/body/div[1]/section/div/div[4]/div/div/div/article/div/div/div/div/div/section[2]/div[2]/div[1]/div["+String.valueOf(seed)+"]/a["+String.valueOf(number)+"]/span"));
    }
    public void setPlayerCards(Pair<String,String>card1, Pair<String,String>card2) {
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"player-listing\"]/div[1]/div[3]/div[1]/div[1]/div/div/div[2]")));
        clickAndCheckPopup(By.xpath("//*[@id=\"player-listing\"]/div[1]/div[3]/div[1]/div[1]/div/div/div[2]"));
        putCard(card1);
        putCard(card2);
        clickAndCheckPopup(By.id("card-selection-complete"));
    }
    private void clickAndCheckPopup(By by)
    {
        boolean exit = false;
        while (!exit) {
            try {
                driver.findElement(by).click();
                exit = true;
            } catch (Exception e) {
                closePopup();
                exit = false;
            }
        }
    }

    private void closePopup()
    {
        try {
            driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[6]/div[1]/div[1]/div[1]/button[1]/div[1]")).click();
        }catch (Exception e){}
    }
    public void closeWeb()
    {
        driver.close();
    }
    public void resetTable() throws ElementClickInterceptedException {
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.id("reset-poker-table-desktop")));
        clickAndCheckPopup(By.id("reset-poker-table-desktop"));
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.id("reset-confirm-btn")));
        clickAndCheckPopup(By.id("reset-confirm-btn"));
    }
    //elimina sempre il primo avversario che si ritrova
    public void deletefirst()
    {
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.player--single:nth-child(2)  ")));
        WebElement el = driver.findElement(By.cssSelector("div.player--single:nth-child(2) "));
        Actions builder = new Actions(driver);
        builder.moveToElement(el).click(el);
        builder.perform();
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#player-listing > div:nth-child(2) > div.player--edit.player--edit-right   ")));
        WebElement el1 = driver.findElement(By.cssSelector("#player-listing > div:nth-child(2) > div.player--edit.player--edit-right   "));
        el1.click();
    }
}