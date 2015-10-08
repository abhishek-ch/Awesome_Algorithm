import java.util.Stack;

/**
 * *******************************************************************
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * @author achoudhary
 * @version 1.0.0
 ********************************************************************
 */

class TreeNode{
   
   int value ;
   TreeNode left;
   TreeNode right;
   
   TreeNode(int i){
      this.value = i;
   }
}

public class FlattenBinaryTree
{
   /**
    * recursive method
    * 
    * we need to make sure always keep all the values to the right node,
    * so make everything in left as null after assigning the value to right of root node
    * 
    * callig the flatten simply assigns the value to root, as its not going to create any trouble
    * dont assign to left or right
    * 
    * @param root
    * @return
    */
   public TreeNode flatten(TreeNode root){
      
      if(root == null)
         return root;
      
      TreeNode rTree = root.right;
      if(root.left != null){
         root.right = root.left;
         root.left = null;
         root = flatten(root.right);
      }
      
      if(rTree != null){
         root.left = rTree;
         root = flatten(rTree.right);
      }
      
      
      
      return root;
   }

   /**
    * This approach is with stack where we simply add always the right value of the node to stack and 
    * read later but add left value always directly to a new tree created t.
    * 
    * Later if stack is not empty means it has series of right node, pop one after one
    * 
    * @param root
    */
   public void flattenStack(TreeNode root){
      Stack<TreeNode> stack = new Stack<TreeNode>();
      
      TreeNode t = root;
      while(t != null || !stack.empty()){
         
         if(t.right != null){
            stack.push(t.right);
         }

         if(t.left != null){
            t.right = t.left;
            t.left = null;
         }else if(!stack.empty()){
            t.right = stack.pop();
         }
         
         t = t.right;
      }
   }
   
}

