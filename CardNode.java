/**
 * Alec Meeker
 * CS 110 final project
 * specific node structure for cards only
 */
public class CardNode extends Node {
    private Card item;
    private CardNode next;

    /**
     Constructor
     @param c a card
     */
    public CardNode(Card c){
        super(c);
        item=c;
        next=null;
    }

    /**
     Constructor
     @param c the card
     @param d the next card
     */
    public CardNode(Card c,CardNode d){
        super(c,d);
        item=c;
        next=d;
    }

    /**
     left the same name for simplicity of programming
     @return the card
     */
    public Card getItem(){
        return item;
    }

    /**
     simply returns the next one
     @return the next card node
     */
    public CardNode getNext(){
        return next;
    }

    /**
     puts a node as next
     @param nextNode to be placed in the node list
     */
    public void setNext(CardNode nextNode) {
        next = nextNode;
    }
}
