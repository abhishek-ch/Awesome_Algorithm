/**
 * Created by achoudhary on 08/03/2016.
 */

public class PartitionList {

    /**
     * create 2 list
     * add all values less than x to list1 and greater than to list2
     * then merge the list in the end
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {

        if(head == null)
            return null;

        ListNode result = new ListNode(0);
        ListNode clone = new ListNode(0);
        ListNode p1 = result;
        ListNode p2 = clone;

        while(head != null){
            if(head.val < x){
                p1.next= head;
                p1 = p1.next;
            }else{
                p2.next = head;
                p2 = p2.next;
            }
            head = head.next;
        }

        //reset the 2nd list final value to null
        p2.next = null;
        //merge both the list to build a big list
        p1.next = clone.next;
        return result.next;
    }

    public static void main(String[] args) {


    }
}



 // Definition for singly-linked list.
