/**
 * Alec Meeker
 * CS 110 Final Project
 * this is a Stack that only accepts cards
 * this also only returns cards, this makes it possible to use a copy constructor
 */
public class cardStack extends StackReferenceBased {
    private CardNode top;

    /**
     initializing the stack
     */
    public cardStack(){top=null;}

    /**
     places a card on top of the stack
     @param newItem a card
     */
    public void push(Card newItem) throws StackException{
        top = new CardNode(newItem, top);
    }

    /**
     removes the top card of the stack
     @return the top card
     */
    public Card pop() throws StackException
    {
        if (!isEmpty()) {
            CardNode temp = top;
            top = top.getNext();
            return temp.getItem();
        }
        else {
            throw new StackException("StackException on " +
                    "pop: stack empty");
        }
    }

    /**
     shows the top card without removing it from the stack
     @return the top card
     */
    public Card peek() throws StackException
    {
        if (!isEmpty()) {
            return top.getItem();
        }
        else {
            return null;
            //throw new StackException("StackException on " +"peek: stack empty");
        }
    }

    /** Determines whether the stack is empty.
     @return true if the stack is empty, otherwise returns false.
     */
    public boolean isEmpty()
    {
        if (top ==  null){
            return true;
        }else {
            return false;
        }
    }
}
