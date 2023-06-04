
/**
 * @author Illia lotfalian
 * 2023-02-28
 */

public class DLinkedNode<T> {
    //Making instance variables
    private T dataItem;
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;

    public DLinkedNode(T data, double prio) {
        dataItem = data;
        priority = prio;
        next=null;
        prev=null;
    }

    public DLinkedNode() {
        dataItem = null;
        priority = 0.0;
        next=null;
        prev=null;

    }
    //Getters and setters


    public double getPriority() {
        return priority;
    }

    public T getDataItem() {
        return dataItem;
    }

    public DLinkedNode<T> getNext() {
        return next;
    }

    public DLinkedNode<T> getPrev() {
        return prev;
    }

    public void setDataItem(T dataItem) {
        this.dataItem = dataItem;
    }

    public void setNext(DLinkedNode next) {
        this.next = next;
    }

    public void setPrev(DLinkedNode prev) {
        this.prev = prev;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }
}