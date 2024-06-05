
public class LinkedList
{
    private ListNode first ;
    private ListNode last ;
    private ListNode current ;
    private String name;

    public LinkedList()
    {
        first = last = current = null;
        name = "LinkedList";
        //this.size = 0;
    }

    public int size()
    {
        int count = 0;
        if(isEmpty())
        {
            return count;
        }
        current = first;
        while(current != null)
        {
            count++;
            current = current.next;
        }
        return count;
    }

    public void insertAtFront(Order insertItem)
    {
        if(isEmpty())
            first = last = new ListNode(insertItem) ;
        else
            first = new ListNode (insertItem,first);
    }

    public void insertAtBack(Order insertItem)
    {
        if(isEmpty())
            first = last = new ListNode(insertItem) ;
        else
            last = last.next =  new ListNode (insertItem);
    }

    public boolean isEmpty(){return first == null;}

    public Object removefromFront()
    {
        Object removeItem = null ;
        removeItem = first.data;

        if(first.equals(last))
            first = last = null ;
        else
            first = first.next;

        return removeItem;
    }

    public Object removeFromBack()throws EmptyListException
    {
        Object removeItem = null;

        if(isEmpty())
            throw new EmptyListException();
        removeItem = last.data;

        if(first.equals(last))
        {
            first = last = null ;
        }

        else
        {
            ListNode current = last.next;
            while(current.next != last)
                current = current.next;
            last = current;
            current.next = null ;
        }
        return removeItem;
    }

    public void removeAnywhere(Object remove) throws  EmptyListException
    {
        ListNode curr , previous =null;
        curr = first;
        while (curr != null)
        {
            if(curr.data == remove)
            {
                if(first == last)
                    first = last = null;
                else if(curr == first)
                {
                    first = first.next;
                    curr = curr.next;
                }
                else if(curr == last)
                {
                    last = previous ;
                    last.next=null;
                }
                else
                {
                    curr = curr.next;
                    previous.next = curr;
                }
            }
            else
            {
                previous = curr;
                curr = curr.next;
            }

        }
    }

    public Object getFirst ()
    {
        if(isEmpty())
            return null;
        else
        {
            current = first;
            return current.data;
        }
    }

    public Object getNext()
    {
        if(current != last)
        {
            current = current.next;
            return current.data;
        }
        else
            return null ;
    }

    public void bubbleSortByid() 
    {
        boolean swapped;
        do {
            swapped = false;
            ListNode current = first;
            ListNode next = first.next;

            while (next != null) {
                if (current.data.getUserID().compareTo(next.data.getUserID()) > 0) {
                    // Swap the data in the nodes
                    Order temp = current.data;
                    current.data = next.data;
                    next.data = temp;

                    swapped = true;
                }

                current = next;
                next = next.next;
            }
        } while (swapped);
    }

    public Order getLast() 
    {
        if (isEmpty()) 
        {
            return null;
        } else
        {
            return last.data;
        }
    }

    public void print()
    {
        if(isEmpty())
        {
            System.out.println("Empty" + name);
            return;
        }

        current = first;
        while(current != null)
        {
            System.out.print(current.data.toString() + " ");
            current = current.next;
            System.out.println();
        }
    }
}
