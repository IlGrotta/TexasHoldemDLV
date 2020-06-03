package game;

import dlv.Budget;
import org.openqa.selenium.WebDriver;

//è finito il turno
public class EndTurn  extends State{
    public EndTurn(WebDriver driver, Budget budget, String pathIA) {
        super(driver, budget, pathIA);
    }
}
