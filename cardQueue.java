/**
 * Alec Meeker
 * CS 110 Final Project
 * this is a queue that only accepts cards
 * this also only returns cards, this makes it possible to use a copy constructor
 */
public class cardQueue extends QueueReferenceBased{
    private CardNode lastNode;


    public cardQueue(){
        lastNode = null;
    }

    /**
     card specific enqueue
     @param c the card to be put into the queue
     */
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

    /**
     produces the first card in the queue that was put there
     @return the next card
     */
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

    /**
     returns whether the queue is empty or not
     @return true or false depending on the last node
     */
    public boolean isEmpty() {
        if(lastNode == null){
            return true;
        }else {
            return false;
        }
    }
}
