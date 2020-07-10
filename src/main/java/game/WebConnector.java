package game;
import dlv.Card;
import game.States.State;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static game.States.State.StateType.*;

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
        JavascriptExecutor js=(JavascriptExecutor)driver;
        //js.executeScript("document.getElementById('actionBox').style.display = '1';");
        //js.executeScript("  document.getElementByCss(actionBox).style.display = 'block'; ");
       // js.executeScript("document.getElementsByClassName('actionBox').style.display='block';");
        WebElement element=driver.findElement(By.className("actionBox"));
        ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('actionBox')[0].style.display='block'");
        ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('actionBox')[1].style.display='block'");
        ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('actionBox')[2].style.display='block'");
        ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('actionBox')[3].style.display='block'");
        ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('actionBox')[4].style.display='block'");
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

                card=funcToTakeCArd(card,card1);
                cards.add(card);


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
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[1]/h6")).getText().length() != 0;
            }
        });
        String card1=(driver.findElement(By.xpath("//div[@class='player-entity--wrapper p0']/div[2]/div[1]/h6")).getText());
        return funcToTakeCArd(card, card1);
    }

    private Card funcToTakeCArd(Card card, String card1) {
        String Number=card1.substring(0,card1.length()-2);
        Integer num=null;
        if(Number.equals("J")||Number.equals("Q")||Number.equals("K")||Number.equals("A")){
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
        else{
            num=Integer.parseInt(Number);
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
        return funcToTakeCArd(card, card1);
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

    public void clickNextTurn(){

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='showdown--nextRound--button']"))  ));
         element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='showdown--nextRound--button']"))  ));

        element.click();

    }

    public void scrollBar(int pos){
        WebElement slider= (driver.findElement(By.xpath("//div[@class='slider-handles']/div")));
        Actions move=new Actions(driver);
        Action action=(Action) move.dragAndDropBy(slider,pos    ,0).build();
        action.perform();
        try {
            //todo migliorare
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public State.StateType getActualStateOfTheGame(){
        while(!myTurn()){

        }
        if(driver.findElements( By.xpath("//button[@class='showdown--nextRound--button']") ).size()==1)
            return State.StateType.ENDMATCH;

        ArrayList<Card> cards=getCards();
        if(cards.size()==0)
            return State.StateType.PREFLOP;
        if(cards.size()==3)
            return State.StateType.FLOP;
        if(cards.size()==4)
            return State.StateType.TURN;
        if(cards.size()==5)
            return RIVER;

        return State.StateType.ERROR;




    }

    public  boolean myTurn(){
        boolean turn=driver.findElements(By.xpath("//button[@class='action-button']")).size()!=0;
        boolean end=(driver.findElements( By.xpath("//button[@class='showdown--nextRound--button']") ).size())==1;
        return turn || end;
    }


    public  String getPlayerName(int pos){
        String pathPlayer="player-entity--wrapper p"+pos;
        return driver.findElement(By.xpath("//div[@class='"+pathPlayer+"']/div[3]/div/h5")).getText();

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
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap<Integer,String> choices=new HashMap<Integer, String>();
        for(int i=0;i<numPlayers;i++){
            String pathPlayer="player-entity--wrapper p"+i;
                String choice=driver.findElement(By.xpath("//div[@class='"+pathPlayer+"']/div")).getText();
                System.out.println("La scelta di "+i+" è: "+choice);
                if (choice==null)
                     choices.put(i,"null");
                else
                  {
                    String[] split=choice.split(" ");
                     choices.put(i,split[0]);

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
            //System.out.println(key+"    adassadsadsadsasad "+value);
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

    public int numPlayerWithChoice()
    {
        HashMap<Integer,String> c=getChoices();
        int cont=0;
        for(Map.Entry<Integer, String> entry : c.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            if(value.equals("Raise") || value.equals("Call") ||value.equals("Check")||value.equals("Bet"))
                cont++;
            // do what you have to do here
            // In your case, another loop.
        }
        return cont;
    }




}
