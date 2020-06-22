package game;
import dlv.*;
import javafx.util.Pair;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        /*
        System.tor();out.println(prova.getFirstCard().getKey());
        System.out.println(prova.getFirstCard().getValue());
        System.out.println(prova.getSecondCard().getKey());
        System.out.println(prova.getSecondCard().getValue());
        System.out.println(prova.getCards());
        prova.closeWeb();*/
       /*
        prob.deletefirst();
        prob.setAvversario(2);
        prob.setAvversario(3);
        Card cardp1=new Card("hearts",9);
        Card cardp2=new Card("clubs",9);
        prob.setPlayerCards(cardp1,cardp2);
        Card card=new Card("diamonds",9);
        Card card1=new Card("clubs",3);
        Card card2=new Card("hearts",3);
        prob.setcards(card,1);
        prob.setcards(card1,2);
        prob.setcards(card2,3);
        System.out.println(prob.GetProbabilityVictory());*/
        WebProbability888 prob=new WebProbability888();
        Budget b=new Budget(5000);
        numeroAvversari numeroAvversari=new numeroAvversari(2);
        ProbabilitaVittoria probabilitaVittoria=new ProbabilitaVittoria(prob.GetProbabilityVictory());
        puntataMinima puntataMinima=new puntataMinima(300);
        sceltaAvversario sceltaAvversario=new sceltaAvversario("ciccio","call");
        sceltaAvversario sceltaAvversario1=new sceltaAvversario("pasticcio","call");
        DlvChoice dlv=new DlvChoice();
        dlv.setBudget(b);
        dlv.setChanceWin(probabilitaVittoria);
        dlv.setNumeroAvversari(numeroAvversari);
        dlv.puntataMinima(puntataMinima);
       // dlv.setSceltaAvversario(sceltaAvversario);
        dlv.setSceltaAvversario(sceltaAvversario1);
        String result=dlv.runProgram();
        System.out.println(result);

    }
     /*
            WebElement slider= (driver.findElement(By.xpath("//div[@class='slider-handles']/div")));
            Actions move=new Actions(driver);
            Action action=(Action) move.dragAndDropBy(slider,300    ,0).build();
            action.perform();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@class='action-button']")).click();
            Thread.sleep(200);*/
}
