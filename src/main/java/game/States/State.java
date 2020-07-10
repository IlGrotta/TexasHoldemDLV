package game.States;

import dlv.*;
import game.WebConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        System.out.println("TEST 1");
        takeCards();
        //todo Profiling se vogliamo farlo
        budget=driver.getPlayerBudget();
        callCost=driver.getCallCost();
        choiseAvversari=driver.getChoices();
        numPlayer=driver.setNumPlayers();
        playerWithNoChoice=driver.playerWithNoChoice();
        prob=getProbabilityWin();
        System.out.println("TEST 2");
        choicePlayer=DlvChoice();
        System.out.println("Il player ha scelto : "+choicePlayer);


        System.out.println("TEST 3");
        if(choicePlayer.equals("fold"))
            driver.Fold();

        if(choicePlayer.equals("call"))
            driver.Call();

        if(choicePlayer.equals("raise")){
            int howmuch=50;
            driver.scrollBar(howmuch);
            driver.Call();
        }

    }

    protected void takeCards() {
        firstCardPlayer=driver.getFirstCard();
        secondCardPlayer=driver.getSecondCard();
        communitycards=driver.getCards();
    }
    protected int getProbabilityWin(){



        /*vecchia versione
        numPlayerWithChoice=driver.numPlayerWithChoice();

        //probability.deletefirst();
        int playerInGame=numPlayerWithChoice+driver.playerWithNoChoice();
        for(int i=2;i<2+playerInGame;i++)
        {
            probability.setAvversario(i);
        }
        probability.setPlayerCards(firstCardPlayer,secondCardPlayer);


        int p=probability.GetProbabilityVictory();
        probability.resetTable();
        */
        int playersNotFold=driver.playerWithNoChoice()+driver.numPlayerWithChoice();
        int player888=probability.numberPlayer888table();

        System.out.println("Player senza scelta: "+driver.playerWithNoChoice());
        System.out.println("Player con scelta: "+driver.numPlayerWithChoice());
        System.out.println("Giocatori al tavolo: "+player888);
        while(player888<playersNotFold){
            System.out.println("Ho aggiunto!");
            //finchè bisogna aggiugnere al tavolo
            probability.setAvversario(player888+1);
            player888=probability.numberPlayer888table();
        }
        while(player888>playersNotFold){
            System.out.println("Ho rimosso!");
            //finchè bisogna rimuovere dal tavolo
            probability.deletefirst();
            player888=probability.numberPlayer888table();
        }

        for(int i=1;i<=communitycards.size();i++)
        {
            probability.setcards(communitycards.get(i-1),i);
        }
        probability.setPlayerCards(firstCardPlayer,secondCardPlayer);
        int p=probability.GetProbabilityVictory();
        probability.resetTable();
        return p;
    }



    //todo in realtà va bene anche il metodo voi , va bene string solo per il testing
    protected String DlvChoice()
    {
        //todo vanno aggiunte le classi no le variabili

        dlv.setBudget(new Budget(budget));
        dlv.setChanceWin(new ProbabilitaVittoria(prob));
        dlv.setNumeroAvversari(new numeroAvversari(numPlayer));
        dlv.puntataMinima(new puntataMinima(callCost));
        // dlv.setSceltaAvversario(sceltaAvversario);


        //todo son sicuro le stringhe danno problemi a dlv, ora provo cosi sennò va implementato con posizioni e si gestiscono i nomi in Java!
        for(Map.Entry<Integer, String> entry : choiseAvversari.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            if(!value.equals("null")){
                String n=driver.getPlayerName(key);
                dlv.setSceltaAvversario(new sceltaAvversario(n,value));
            }

        }

        String result=dlv.runProgram();
        return result;
    }




    public enum StateType{PREFLOP, FLOP, TURN, RIVER, ENDMATCH, ERROR}

}
