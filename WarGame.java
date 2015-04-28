import java.util.ArrayList;
import java.util.Random;

/**
 * Alec Meeker
 * Cs 110, Final Project
 * War
 * the card game program, no main method
 * flips one for war
 */
public class WarGame {
    private cardQueue player1,player2;
    private Card p1up,p2up;
    private cardStack p1war,p2war;
    public ArrayList<Card> p1warcards,p2warcards;
    private int p1ds,p2ds,warstack; //deck size
    public boolean warState;


    /**
     Constructor
     this creates a game with two players and initializes all the values
     */
    public WarGame(){
        player1=new cardQueue();
        player2=new cardQueue();
        dealnew(0,0);
        warstack=0;
        warState=false;
        p1warcards=new ArrayList<Card>();
        p2warcards=new ArrayList<Card>();
        p1war=new cardStack();
        p2war=new cardStack();
    }

    /**
     this method generates the deck and deals the cards out to the
     @param y the size of player 1's deck
     @param z the size of player 2's deck
     */
    private void dealnew(int y, int z){
        if(y!=0){
            player1.dequeueAll();
        }
        if(z!=0) {
            player2.dequeueAll();
        }
        Deck deck=new Deck();
        deck.shuffle();
        int pd=0;
        for(int i=0;i<52;i++){
            if (pd==0){
                pd++;
                Card toAdd=new Card(deck.dealCard());
                player1.enqueue(toAdd);
                p1ds++;
            }else if (pd==1){
                pd--;
                player2.enqueue(deck.dealCard());
                p2ds++;
            }else {
                System.out.print("ERROR, something went wrong");
                System.exit(0);
            }
        }
    }

    /**
     each player puts the top card face up
     decrements the deck size
     increases the amount of cards won
     */
    public void flip(){
        try {
            p1up = player1.dequeue();
        }catch (Exception e){
            System.out.print("Player 2 wins");
            System.exit(0);
        }
        try {
            p2up = player2.dequeue();
        }catch (Exception e){
            System.out.print("Player 1 wins");
            System.exit(0);
        }
        p1ds--;
        p2ds--;
        warstack+=2;
        if (p1up.equals(p2up)){
            warState=true;
        }
    }

    /**
     printing method
     produces what each player got when they flipped
     */
    public void show(){
        System.out.println("player 1: " + p1up.toString());
        System.out.println("player 2: " + p2up.toString());
        if(p1up.equals(p2up)){
            System.out.println("War");
        }


    }

    /**
     if there is a war, this shows the individual cards in the pile to be won in the war
     @param i player's number
     */
    public void showWarPile(int i){
        switch (i){
            case 1:
                for(int j=0;j<p1warcards.size();j++){
                    System.out.println(p1warcards.get(j));
                }
            case 2:
                for(int j=0;j<p2warcards.size();j++){
                    System.out.println(p2warcards.get(j));
                }
            default:
                System.out.println("oops, something went wrong");
        }
    }

    /**
     if there are face up cards, this method determines where the cards go
     */
    public void resolve(){
        if (warstack!=0) {
            if (p1up.greaterThan(p2up)) {
                winRound(player1);
                addPoints(1);
            } else if (p2up.greaterThan(p1up)) {
                winRound(player2);
                addPoints(2);
            } else if (p1up.equals(p2up)) {
                War();
            } else {
                System.out.print("Something went wrong");
            }
        }
    }

    /**
     allows the user to find the deck size of the specified player
     @param i player's number
     @return size of a deck
     */
    public int getDeckSize(int i){
        switch (i){
            case 1:
                return p1ds;
            case 2:
                return p2ds;
            default:
                System.out.print("That is an incorrect input");
                return 0;
        }
    }

    /**
     puts the cards in the specified players deck
     @param player the actual player's card queue
     */
    private void winRound(cardQueue player){
        player.enqueue(p1up);
        player.enqueue(p2up);
        while (p1war.peek() != null) {
            player.enqueue(p1war.pop());
        }
        while (p2war.peek() != null) {
            player.enqueue(p2war.pop());
        }
        p1warcards.clear();
        p2warcards.clear();

    }

    /**
     generates the piles of cards but does not flip the card that will determine who wins the piles
     */
    private void War(){
        p1warcards.add(p1up);
        p1war.push(p1up);

        p2warcards.add(p2up);
        p2war.push(p2up);

        p1war.push(player1.dequeue());
        p1warcards.add(p1war.peek());
        p1ds--;

        p2war.push(player2.dequeue());
        p2warcards.add(p2war.peek());
        p2ds--;

        warstack+=2;

        if(p1ds==0){
            winRound(player2);
            addPoints(2);
        }else if(p2ds==0){
            winRound(player1);
            addPoints(1);
        }
    }

    /**
     increases the winner's deck size by the amount they won
     @param ds the player's number
     */
    private void addPoints(int ds){
        switch (ds){
            case 1: p1ds+=warstack; break;
            case 2: p2ds+=warstack; break;
            default:
                System.out.println("There is an error......");
        }
        warstack=0;
        warState=false;
    }

    /**
     names are based off of the names given from the zipped folder of card pictures
     @param pp selected player's number
     @return name of the picture
     */
    public String picName(int pp){
        String name="";
        switch (pp){
            case 1:
                name+=p1up.getRankAsString().toLowerCase();
                switch (p1up.getSuit()){
                    case 1: name+="s.jpg";break;
                    case 2: name+="c.jpg";break;
                    case 3: name+="h.jpg";break;
                    case 4: name+="d.jpg";break;
                }
                break;
            case 2:
                name+=p2up.getRankAsString().toLowerCase();
                switch (p2up.getSuit()){
                    case 1: name+="s.jpg";break;
                    case 2: name+="c.jpg";break;
                    case 3: name+="h.jpg";break;
                    case 4: name+="d.jpg";break;
                }
                break;
        }
        return name;
    }

    /**
     takes both players decks and shuffles them separately
     */
    public void shuffle(){
        if(p1up!=null) {
            resolve();
        }
        int randNum;
        Random r = new Random();
        Card temp;

        Card[] shuff1=new Card[p1ds];
        for (int p1st=0;p1st<p1ds;p1st++){
            shuff1[p1st]=player1.dequeue();
        }
        for (int i = 0; i < p1ds; i++){
            randNum = r.nextInt(p1ds);
            temp = shuff1[i];
            shuff1[i]=shuff1[randNum];
            shuff1[randNum]=temp;
        }
        for(int ss1=0;ss1<p1ds;ss1++){
            player1.enqueue(shuff1[ss1]);
        }


        Card[] shuff2=new Card[p2ds];
        for (int p2st=0;p2st<p2ds;p2st++){
            shuff2[p2st]=player2.dequeue();
        }

        for (int w = 0; w < p2ds; w++){
            randNum = r.nextInt(p2ds);
            temp = shuff2[w];
            shuff2[w]=shuff2[randNum];
            shuff2[randNum]=temp;
        }
        for(int ss2=0;ss2<p2ds;ss2++){
            player2.enqueue(shuff2[ss2]);
        }
    }

    /**
     @return the string explicitly stating who won
     */
    public String theWinRound(){
        String n="";
        if (p1up!=null){
            if (p1up.greaterThan(p2up)) {
                n="Player one Wins";
            } else if (p2up.greaterThan(p1up)) {
                n="Player two Wins";
            } else if (p1up.equals(p2up)) {
                n="WAR!";
            } else {
                System.out.print("WrongWrongWrong");
            }
        }
        return n;
    }

}
