/**
 * Created by Alec Meeker on 4/16/2015.
 */
public class cardQueue extends QueueReferenceBased{
    private CardNode lastNode;


    public cardQueue(){
        lastNode = null;
    }

    /***/
    public void enqueue(Card c){
        CardNode newNode = new CardNode(c);

        // insert the new node
        if (isEmpty()) {
            // insertion into empty queue
            newNode.setNext(newNode);
        }
        else {
            // insertion into nonempty queue
            newNode.setNext(lastNode.getNext());
            lastNode.setNext(newNode);
        }

        lastNode = newNode;  // new node is at back
    }

    public Card dequeue()throws QueueException {
        if (!isEmpty()) {
            // queue is not empty; remove front
            CardNode firstNode = lastNode.getNext();
            if (firstNode == lastNode) { // special case?
                lastNode = null;           // yes, one node in queue
            }
            else {
                lastNode.setNext(firstNode.getNext());
            }
            return firstNode.getItem();
        }
        else {
            throw new QueueException("QueueException on dequeue:"
                    + "queue empty");
        }
    }

    /***/
    public boolean isEmpty() {
        if(lastNode == null){
            return true;
        }else {
            return false;
        }
    }
}
