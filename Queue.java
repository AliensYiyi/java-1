
public class Queue
{
    protected LinkedList list ;
    
    public Queue ()
    {
        list = new LinkedList ();
    }
    
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    
    public int size()
    {
        return list.size();
    }
    
    public void enqueue (Order element)
    {
        list.insertAtBack(element);
    }
    
    public Object dequeue ()
    {
        return list.removefromFront();
    }
    
    public Object front ()
    {
        return list.getFirst();
    }
    
    public Object rear()
    {
        return list.getLast();
    }
    
    public void bubleSortId()
    {
        list.bubbleSortByid();
    }
    
}