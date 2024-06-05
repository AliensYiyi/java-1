
public class ListNode
{
    Order data ;
    ListNode next ;
    
    //public ListNode(Object o){ this (o,null);}
    
    ListNode(Order o) 
    {
        this (o, null);
    }
    
    ListNode(Order o , ListNode nextNode)
    {
        data = o;
        next = nextNode;
    }
    
    Object getObject() {return data;}
    ListNode getLink() {return next;}
}
