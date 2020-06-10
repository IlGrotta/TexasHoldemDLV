package dlv;

public class Card {
    protected int number;
    protected String seed;

    public Card( String seed,int number ) {
        this.number = number;
        this.seed = seed;
    }

    public int getNumber() {
        return number;
    }

    public String getSeed() {
        return seed;
    }
}
