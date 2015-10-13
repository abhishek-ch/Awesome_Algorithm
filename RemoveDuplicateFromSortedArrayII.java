


public class RemoveDuplicateFromSortedArrayII
{


   /**
    * Definition for singly-linked list.*/
     public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }
    
   
   public ListNode deleteDuplicates(ListNode head) {
      ListNode n = new ListNode(0);
      n.next = head;
      
      ListNode p = n;
      while(p.next != null && p.next.next != null){
          if(p.next.val == p.next.next.val){
              int duplicate = p.next.val;
              
              while(p.next != null && p.next.val == duplicate){
                  p.next = p.next.next;
              }
          }else{
              p = p.next;
          }
      }
      
      return n.next;
  }


}
