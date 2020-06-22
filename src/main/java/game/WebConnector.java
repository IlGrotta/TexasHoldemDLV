package game;
import dlv.Card;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebConnector {
    WebDriver driver;
    int numPlayers;
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

        return Integer.parseInt((driver.findElement(By.xpath("//div[@class='pot-container']/h4")).getText()));
    }
    public ArrayList<Card> getCards()
    {
        ArrayList<Card> cards=new ArrayList<Card>();
        while(!myTurn()){
            //ONLY FIRST TIME; SO THE GAME IS LOADED! TODO A BETTER SOLUTION
        }
        boolean notexist=Boolean.FALSE;
        int i=1;
        do {

            if(driver.findElements( By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div[6]/div["+ i +"]/h6") ).size()!=0)
            {
                Card card = null;
                String card1=(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div[6]/div["+ i +"]/h6")).getText());
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


    public Card getSecondCard()
    {
        Card card = null;
        String card1=null;
        do{

            if(myTurn())
                card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[2]/h6")).getText());
        }while (card1==null || card1.equals(""));

        System.out.println("AAAAAAAAAAA"+card1);
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

    public  int setNumPlayers(){
        WebElement el=driver.findElement(By.xpath("//div[@class='poker-table--container']/div"));
        String s=el.getAttribute("class");
        s=s.substring(s.length()-1);
        numPlayers= Integer.parseInt(s)+1;
        return numPlayers;
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

        ArrayList<Card> cards=getCards();
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

    public int getPlayerBudget(){
        for(int i=0;i<numPlayers;i++){
            String pathPlayer="player-entity--wrapper p"+i;
            if(driver.findElement(By.xpath("//div[@class='"+pathPlayer+"']/div[3]/div/h5" )).getText().equals("Player 1")  ){
                return Integer.parseInt(driver.findElement(By.xpath("//div[@class='"+pathPlayer+"']/div[3]/div/div/h5" )).getText());
            }
        }
        return -1;
    }

    public void closeWeb()
    {
        driver.close();
    }

    public HashMap<Integer,String>getChoices(){
        HashMap<Integer,String> choices=new HashMap<Integer, String>();
        for(int i=0;i<numPlayers;i++){
            String pathPlayer="player-entity--wrapper p"+i;
            if(!driver.findElement(By.xpath("//div[@class='"+pathPlayer+"']/div[3]/div/h5" )).getText().equals("Player 1")  ){
                String choice=driver.findElement(By.xpath("//div[@class='"+pathPlayer+"']/div")).getText();
                if (choice==null)
                     choices.put(i,"null");
                else
                  {
                    String[] split=choice.split(" ");
                     choices.put(i,split[0]);

                  }

            }
        }
        return choices;
    }

    public int playerWithNoChoice(){
        HashMap<Integer,String> c=getChoices();
        int cont=0;
        for(Map.Entry<Integer, String> entry : c.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            if(value.equals("null"))
                cont++;
            // do what you have to do here
            // In your case, another loop.
        }
        return cont;
    }
    public int getCallCost(){
        return Integer.parseInt(driver.findElement(By.xpath("//div[@class='slider-handles']/div/div")).getText());
    }


}
