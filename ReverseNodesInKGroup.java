/**
 * Created by achoudhary on 30/12/2015.
 *
 * Iterate through head and make a pointer to record each k move.
 * Before that keep a pointer to the starting point in each move/group ie prev.
 *
 * As soon as any group caught or i ==k , reverse the group with prev which holds
 * the 1- starting point of each group till the 1 + curent tracker, its like
 * reverse everything inbetween
 */


//  Definition for singly-linked list.
  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }


public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode curr = new ListNode(0);
        curr.next = head;

        ListNode prev = curr;
        int i=0;

        while(head != null){
            i++;
            //if i equals k, means the group has determined
            //now reverse the group
            if(i == k){
                i =0;
                prev = reverse(prev,head.next);
                //we got the reverse version , now move the head to next of returned node
                head = prev.next;
            }else{
                head = head.next;
            }
        }
        return curr.next;
    }

    //reverse the
    private ListNode reverse(ListNode start, ListNode end){
        ListNode last=start.next;
        ListNode cur=last.next;

        while (cur!=end){

            last.next=cur.next;
            cur.next=start.next;
            start.next=cur;

            cur=last.next;
        }
        return last;
    }


    public static void main(String[] args) {
        int[] nodes = {2,3,4,5,6,7,8,9,10,11};
        ListNode curr = null;
        ListNode next = new ListNode(1);
        curr = next;
        for(int i : nodes){
            ListNode node = new ListNode(i);
            next.next = node;
            next = next.next;
        }


        ListNode test = curr;
        //pprint( new ReverseNodesInKGroup().reverseKGroup(test1,2));
        pprint( new ReverseNodesInKGroup().reverseKGroup(test,3));
        //pprint( new ReverseNodesInKGroup().reverseKGroup(test3,5));


    }

    private static void pprint(ListNode node){
        while (node != null){
            System.out.print(node.val+" -> ");
            node = node.next;
        }
        System.out.println();
    }

}
