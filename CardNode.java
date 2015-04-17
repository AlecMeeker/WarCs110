/**
 * Created by Alec Meeker on 4/16/2015.
 */
public class CardNode extends Node {
    private Card item;
    private CardNode next;

    public CardNode(Card c){
        super(c);
        item=c;
        next=null;
    }

    public CardNode(Card c,CardNode d){
        super(c,d);
        item=c;
        next=d;
    }

    public Card getItem(){
        return item;
    }

    public CardNode getNext(){
        return next;
    }

    public void setNext(CardNode nextNode) {
        next = nextNode;
    }
}
