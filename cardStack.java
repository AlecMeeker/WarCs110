/**
 * Created by Alec Meeker on 4/16/2015.
 */
public class cardStack extends StackReferenceBased {
    private CardNode top;

    public cardStack(){top=null;}

    public void push(Card newItem) throws StackException{
        top = new CardNode(newItem, top);
    }

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

    public Card peek() throws StackException
    {
        if (!isEmpty()) {
            return top.getItem();
        }
        else {
            throw new StackException("StackException on " +
                    "peek: stack empty");
        }
    }
}
