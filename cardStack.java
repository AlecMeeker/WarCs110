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
