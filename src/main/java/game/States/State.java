package game.States;

import dlv.*;
import game.WebConnector;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class State {
    protected DlvChoice dlv;
    protected DlvHandler dlvHandler;
    protected WebConnector driver;
    protected int budget;
    protected WebProbability888 probability;
    protected String pathIA;
    protected  boolean init=false;
    protected String sceltaAvversario;
    protected static ArrayList<HashMap<String, Integer>> behaviour;
    protected static int numRound = 0;
    protected static int numPhases = 0;
    Card firstCardPlayer;
    Card secondCardPlayer;
    ArrayList<Card>communitycards;

    public State(WebConnector connector, WebProbability888 probability){
        driver=connector;
        budget=driver.getPlayerBudget();
        this.probability=probability;
        dlvHandler=new DlvHandler();
        dlv=new DlvChoice();
    }

    public  State execute(){

        takeCards();
        //todo Profiling se vogliamo farlo

        budget=driver.getPlayerBudget();
        driver.getCallCost();
        driver.setNumPlayers();
        //todo dire quanti sono in gioco, e quanti devono ancora giocare
        //todo calcolare probabilita con nostre carte
        //todo  ESEGUIRE DLV
        //todo eseguire azione detta da dlv
        return null;
    }

    protected void takeCards() {
        firstCardPlayer=driver.getFirstCard();
        secondCardPlayer=driver.getSecondCard();
        communitycards=driver.getCards();
    }


}
