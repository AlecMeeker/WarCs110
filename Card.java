
/**
 * Alec Meeker
 * CS 110 -- hw 10
 * used from hw 5
 */
public class Card {
    private int rank;
    private int suit;
    public static final int SPADES=1;
    public static final int CLUBS=2;
    public static final int HEARTS=3;
    public static final int DIAMONDS=4;
    public static final int ACE=1;
    public static final int JACK=11;
    public static final int QUEEN=12;
    public static final int KING=13;

    /** Constructor
    @param s ,Suit
    @param r ,rank
     */
    public Card(int r,int s){
        r=checkRank(r);
        s=checkSuit(s);
        rank=r;
        suit=s;
    }

    public Card(Card c){
        this(c.getRank(), c.getSuit());
    }

    /**
     @param s , possible suit number
     @return suit number
     */
    private int checkSuit(int s) {
        if (s > 4 || s < 1){
            s = 1;
        }
        return s;
    }

    /***/
    private int checkRank(int r){
        if (r>=14 || r<1){
            r=1;
        }
        return r;
    }

    /***/
    public int getSuit(){
        return suit;
    }

    /***/
    public int getRank(){
        return rank;
    }

    /***/
    public String getRankAsString(){
        switch (rank){
            case 1: return "ACE";
            case 2: return "2";
            case 3: return "3";
            case 4: return "4";
            case 5: return "5";
            case 6: return "6";
            case 7: return "7";
            case 8: return "8";
            case 9: return "9";
            case 10: return "10";
            case 11: return "JACK";
            case 12: return "QUEEN";
            case 13: return "KING";
            default: return "not a card";
        }
    }

    /***/
    public String getSuitAsString(){
        switch (suit){
            case 1: return "SPADES";
            case 2: return "CLUBS";
            case 3: return "HEARTS";
            case 4: return "DIAMONDS";
            default: return "not a suit";
        }
    }

    /**
     a proper format for the string to be printed as
     @return printable string
     */
    public String toString(){
        return getRankAsString()+" of "+getSuitAsString();
    }

    /**
     only compares the rank, not the suit
     @return if the cards are equal rank wise
     */
    public boolean equals(Card r){
        boolean s=false;
        if (rank==r.getRank())
            s=true;
        return s;
    }

    /**
     compares this card and another
     @return if the current card is greater than the chosen card
     */
    public boolean greaterThan(Card c){
        if (rank==1 &&c.rank!=1){
            return true;
        }else if(c.rank==1 && rank!=1){
            return false;
        }else if(rank>c.rank){
            return true;
        }else {
            return false;
        }
    }
}
