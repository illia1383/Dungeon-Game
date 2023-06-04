
/**
 * @author Illia lotfalian
 * 2023-02-28
 */


/**
 * First constuctor
 * @param <T>
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
    //making the instance variables
    private DLinkedNode<T> front; // reference to the firs tnode of the double linked list
    private DLinkedNode<T> rear; // reference to the last node of the doubly linked list
    private int count; // the value of this is the number of data items in the priority queue

    /**
     * Making a empty quueue
     */
    public DLPriorityQueue(){
        front = null;
        rear= null;
        count=0;
        //creates a empty queue
    }

    /**
     *
     * @param dataItem item to be added onto the priority queue
     * @param priority
     */
    public void add(T dataItem, double priority){
        // there is 3 cases, when the item being added is at the front, middle , rear
        DLinkedNode<T> newNode = new DLinkedNode<>(dataItem,priority);
        DLinkedNode<T> curr = front;
        // first check if there is nothign in the list add to the front of the list
        if(front==null){
            rear = newNode;
            front = newNode;
            newNode.setPrev(null);
            newNode.setNext(null);

        }
        else{
            while(curr!=null && priority>curr.getPriority()){
                curr = curr.getNext();

            }if(curr==front){ // if the node is at front of the list
                newNode.setNext(front);
                front.setPrev(newNode);
                front=newNode;

            }else if(curr==null){//if its at the rear
                newNode.setPrev(rear);
                rear.setNext(newNode);
                rear = newNode;


            }else{
                newNode.setPrev(curr.getPrev()); // if its in the middle
                newNode.setNext(curr);
                curr.getPrev().setNext(newNode);
                curr.setPrev(newNode);

            }

        }count++;

    }

    /**
     * A helper functiuon for updateprio
     * @param curr
     */
    private void remove(DLinkedNode<T> curr) {
        // 3 cases to consider if the node being removed is at the front, rear, or middle

        if (curr == front && curr == rear) { // if it's the only node in the list
            front = null;
            rear = null;
        } else if (curr == front) { // if it's at the front
            front = curr.getNext();
            front.setPrev(null);
        } else if (curr == rear) { // if it's at the rear
            rear = curr.getPrev();
            rear.setNext(null);
        } else { // if it's in the middle
            curr.getPrev().setNext(curr.getNext());
            curr.getNext().setPrev(curr.getPrev());
        }
    }

    /**
     *
     * @param dataItem item whose priority is to be changed
     * @param newPriority  value of the new priority for this data item
     * @throws InvalidElementException
     */
    public void updatePriority (T dataItem, double newPriority) throws InvalidElementException{
        DLinkedNode<T> curr = front;
        DLinkedNode<T> newNode= new DLinkedNode<T>(dataItem,newPriority);

        //make while loop going thorugh the list

        while(curr!=null && curr.getDataItem().equals(dataItem)==false){
            curr = curr.getNext();
        }
        if(curr == null){// checks if the item is actually in the list
            throw new InvalidElementException("Item not in the queue");
        }else{
            remove(curr);
            add(dataItem, newPriority);
        }
    }

    /**
     *
     * @return
     * @throws EmptyPriorityQueueException
     */
    public T removeMin() throws EmptyPriorityQueueException {

        T data;
        if (front == null) {
            throw new EmptyPriorityQueueException("Empty queue");
        } else {
            data =  front.getDataItem();
        }
        //always removes the node at the front of the list
        front = front.getNext();
        if(front == null){
        rear = (null);
        }else{front.setPrev(null);}

        count--; //since we are removing something we subtract one from count

        return data; //return dataitem that the conditions asks for
    }

    /**
     * Checks if its empty
     * @return
     */
    public boolean isEmpty(){ //checks if the prio queue is empty if it not returns false else it returns true
        if(front!=null){
            return false;
        }return true;
    }

    /**
     * gives size of the queue
     * @return
     */
    public int size(){
        return count;
    }

    public String toString() {
        DLinkedNode<T> curr = front;
        String st1 = "";
        while (curr != null) {//while loop to go thorugh the list
            st1 += curr.getDataItem(); // concatenate the dataitems
            curr = curr.getNext();
        }
        return st1;//return it
    }
    public DLinkedNode<T> getRear(){
        return rear;
    }







}

