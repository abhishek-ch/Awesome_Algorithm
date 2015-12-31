import java.util.*;
import java.util.PriorityQueue;

/**
 * Created by abc on 31/12/2015.
 * Using priorityQueue or concept of heap to solve the problem.
 * Just need to implement the comparator
 */
public class MergeKSortedList {




    public ListNode mergeKLists(ListNode[] lists) {
        if(null == lists || lists.length ==0){
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length,new Comparator<ListNode>(){
            public int compare(ListNode node1 , ListNode node2){
                if(node1.val > node2.val){
                    return 1;
                }
                if(node1.val == node2.val){
                    return 0;
                }else
                    return -1;
            }
        });

        //just add the header of each ListNode

        for (ListNode node:
                lists) {
            if(node != null){
                heap.add(node);
            }
        }

        ListNode result = new ListNode(0);
        ListNode dummy = result;
        while(heap.size() > 0){
            ListNode np = heap.poll();
            dummy.next = np;

            //since we have header of each list node previously
            //now we will simply get next element of each pop element and add to heap
            //automatically it will match with previous
            if(np.next != null){
                heap.add(np.next);
            }
            dummy = dummy.next;

        }

        return result.next;
    }

    public static void main(String[] args) {
        new MergeKSortedList().mergeKLists(null);
    }
}

//  Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

