package game;
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
        //Thread.sleep(10000);
        while(!myTurn()){
            //ONLY FIRST TIME; SO THE GAME IS LOADED! TODO A BETTER SOLUTION
        }
    }
    public int getPot()
    {

        Integer pot=Integer.parseInt((driver.findElement(By.xpath("//div[@class='pot-container']/h4")).getText()));
        return pot;
    }
    public ArrayList<Pair<String,String>> getCards()
    {
        while(!myTurn()){
            //ONLY FIRST TIME; SO THE GAME IS LOADED! TODO A BETTER SOLUTION
        }
        ArrayList<Pair<String,String>> cards=new ArrayList<Pair<String,String>>();
        Boolean notexist=Boolean.FALSE;
        Integer i=1;
        do {

            if(driver.findElements( By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div[6]/div["+ i +"]/h6") ).size()!=0)
            {
                Pair<String,String>card = null;
                String card1=(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div[6]/div["+ i +"]/h6")).getText());
                String Number=card1.substring(0,card1.length()-2);
                if(card1.charAt(card1.length()-1)=='♣'){
                    card=new Pair<String, String>("clubs",Number);
                    cards.add(card);
                }
                if(card1.charAt(card1.length()-1)=='♠'){
                    card=new Pair<String, String>("diamonds",Number);
                    cards.add(card);
                }
                if(card1.charAt(card1.length()-1)=='♦'){
                    card=new Pair<String, String>("spades",Number);
                    cards.add(card);
                }
                if(card1.charAt(card1.length()-1)=='♥'){
                    card=new Pair<String, String>("hearts",Number);
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
    public Pair<String, String> getFirstCard()
    {
        Pair<String,String>card = null;
        String card1=null;

        do{

            if(myTurn())
            card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[1]/h6")).getText());
        }while (card1==null  );
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
        String card1=null;
        do{

            if(myTurn())
                card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[2]/h6")).getText());
        }while (card1==null || card1.equals(""));

        System.out.println("AAAAAAAAAAA"+card1);
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



    public void Fold()
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div[1]/button[2]")).click();
    }

    public void Call()
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div[1]/button[1]")).click();
    }

    public String getActualStateOfTheGame(){
        if(driver.findElements( By.xpath("//button[@class='showdown--nextRound--button']") ).size()==1)
            return "TURNENDED";

        ArrayList<Pair<String,String>> cards=getCards();
        if(cards.size()==0)
            return "PREFLOP";
        if(cards.size()==3)
            return "FLOP";
        if(cards.size()==4)
            return "TURN";
        if(cards.size()==5)
            return "RIVER";

        return "ERRORE NON SO CHE FASE SIA OWOWOWOWO";




    }

    public  boolean myTurn(){
        boolean turn=driver.findElements(By.xpath("//button[@class='action-button']")).size()!=0;
        return turn;
    }

    public void closeWeb()
    {
        driver.close();
    }

}
