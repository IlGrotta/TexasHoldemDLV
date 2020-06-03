package dlv;
public class Card {
    public static String clubs = "clubs";
    public static String diamonds = "diamonds";
    public static String spades = "spades";
    public static String hearts = "hearts";

    protected String number; //ace -> 0, two -> 1 etc etc
    protected String seed;

    public Card(String number, String seed) {
        this.number = number;
        this.seed = seed;
    }

    public String getNumber() {
        return number;
    }

    public String getSeed() {
        return seed;
    }
}
