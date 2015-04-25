import java.util.ArrayList;
import java.util.List;


public class RemoveNNodeFromLast {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RemoveNNodeFromLast();
	}

	
	public RemoveNNodeFromLast() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		ListNode removeNthFromEnd = removeNthFromEnd(node1, 2);
		while(removeNthFromEnd.next != null){
			System.out.print(removeNthFromEnd.val+"->");
			removeNthFromEnd = removeNthFromEnd.next;
		}
		System.out.print(removeNthFromEnd.val);
	}
	
	 public ListNode removeNthFromEnd(ListNode head, int n) {
	        ListNode main = head;
	        ListNode ref = head;
	        int i =0;
	        
	        while(i < n){
	        	System.out.println("kya "+ref.val);
	        	ref = ref.next;
	        	i++;
	        }
	        if(ref == null)
	        	return main.next;
	        while(ref.next != null){
	        	main = main.next;
	        	ref = ref.next;
	        }
	        main.next = main.next.next;
	        
	        return  head;
	    }
	
	 
	 
	 /**
	  * Definition for singly-linked list.*/
	   public class ListNode {
	       int val;
	       ListNode next;
	       ListNode(int x) { val = x; }
	   }
	  
}
