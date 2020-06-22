package game.States;

import dlv.*;
import game.WebConnector;

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
    protected String choicePlayer;
    protected  Card firstCardPlayer;
    protected Card secondCardPlayer;
    protected ArrayList<Card>communitycards;
    protected HashMap<Integer, String> choiseAvversari;
    int callCost;
    int numPlayer;
    int playerWithNoChoice;
    int prob;
    int numPlayerWithChoice;
    public State(WebConnector connector, WebProbability888 probability){
        driver=connector;
        budget=driver.getPlayerBudget();
        this.probability=probability;
        dlvHandler=new DlvHandler();
        dlv=new DlvChoice();
    }

    public void execute(){

        takeCards();
        //todo Profiling se vogliamo farlo
        budget=driver.getPlayerBudget();
        callCost=driver.getCallCost();
        choiseAvversari=driver.getChoices();
        numPlayer=driver.setNumPlayers();
        playerWithNoChoice=driver.playerWithNoChoice();
        prob=getProbabilityWin();
        choicePlayer=DlvChoice();

    }

    protected void takeCards() {
        firstCardPlayer=driver.getFirstCard();
        secondCardPlayer=driver.getSecondCard();
        communitycards=driver.getCards();
    }
    protected int getProbabilityWin(){
        numPlayerWithChoice=driver.numPlayerWithChoice();
        probability.deletefirst();
        int playerInGame=numPlayerWithChoice+driver.playerWithNoChoice();
        for(int i=2;i<2+playerInGame;i++)
        {
            probability.setAvversario(i);
        }
        probability.setPlayerCards(firstCardPlayer,secondCardPlayer);
        for(int i=1;i<=communitycards.size();i++)
        {
            probability.setcards(communitycards.get(i-1),i);
        }
        int p=probability.GetProbabilityVictory();
        probability.resetTable();
        return p;
    }
    //todo in realta va bene anche il metodo voi , va bene string solo per il testing
    protected String DlvChoice()
    {
        //todo vanno aggiunte le classi no le variabili
        /*
        dlv.setBudget(b);
        dlv.setChanceWin(probabilitaVittoria);
        dlv.setNumeroAvversari(numeroAvversari);
        dlv.puntataMinima(puntataMinima);
        // dlv.setSceltaAvversario(sceltaAvversario);
        dlv.setSceltaAvversario(sceltaAvversario1);
        String result=dlv.runProgram();
        return result;*/
        return "scelta";
    }

}
