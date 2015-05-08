import java.util.LinkedList;
import java.util.Queue;



class Node{

 public boolean visited =false;
 Node left;
 Node right;
 int value;
}

public class FindPathExistsBetweenTwoNodes{

 Node A;
 Node B;
  public  FindPathExistsBetweenTwoNodes(Node A){
  
  } 

  public boolean findPathBetweenNodes(Node n){
  
      Queue<Node> queue = new LinkedList<Node>();

      queue.add(n);
      
      while(!queue.isEmpty()){
       Node head = queue.poll();
       
       if(head.value == B.value){
        return true;
       }
       
       Node left = head.left;
       Node right = head.right;
       if(left  != null && !left .visited){
    	   queue.add(left);
        left.visited = true;
       }
       
       
       if(right != null && !right .visited){
    	   queue.add(head.left);
        right.visited = true;
       }
       
      }
      
      return false;
  
  }

}
