import java.util.ArrayList;

/**
 * Created by Alec Meeker on 4/16/2015.
 */
public class WarGame {
    private cardQueue player1,player2;
    private Card p1up,p2up;
    private cardStack p1war,p2war;
    private ArrayList<Card> p1warcards,p2warcards;
    private int p1ds,p2ds,warstack; //deck size


    /***/
    public WarGame(){
        player1=new cardQueue();
        player2=new cardQueue();
        dealnew(0,0);
    }

    /***/
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

    /***/
    public void flip(){
        p1up= player1.dequeue();
        p2up= player2.dequeue();
        p1ds--;
        p2ds--;
    }

    /***/
    public void show(){
        System.out.println("player 1: " + p1up.toString());
        System.out.println("player 2: " + p2up.toString());
        if(p1up.equals(p2up)){
            System.out.println("War");
        }


    }

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

    /***/
    public void resolve(){
        if (p1up.greaterThan(p2up)){
            winRound(player1);
            addPoints(1);
        }else if (p2up.greaterThan(p1up)){
            winRound(player2);
            addPoints(2);
        }else if (p1up.equals(p2up)){
            War();
        }else{System.out.print("Something went wrong");}
    }

    /***/
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

    /***/
    private void winRound(cardQueue player){
        player.enqueue(p1up);
        player.enqueue(p2up);
        try {
            while (p1war.peek() != null) {
                player.enqueue(p1war.pop());
            }
            while (p2war.peek() != null) {
                player.enqueue(p2war.pop());
            }
            p1warcards.clear();
            p2warcards.clear();
        }catch (NullPointerException e){}

    }

    /***/
    private void War(){
        p1warcards.add(p1up);
        p1war.push(p1up);
        p2warcards.add(p2up);
        p2war.push(p2up);
        for(int i=0;i<3;i++) {
            p1war.push(player1.dequeue());
            p1warcards.add(p1war.peek());
            p1ds--;
            p2war.push(player2.dequeue());
            p2warcards.add(p2war.peek());
            p2ds--;
            warstack+=2;
        }
        warstack+=2;
        flip();
    }

    /***/
    private void addPoints(int ds){
        int add=2+warstack;
        switch (ds){
            case 1: p1ds+=add; break;
            case 2: p2ds+=add; break;
            default:
                System.out.println("There is an error......");
        }
    }
}
