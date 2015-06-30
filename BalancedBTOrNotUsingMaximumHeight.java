class TreeNode
{


   int val;

   TreeNode left;

   TreeNode right;


   TreeNode(int x)
   {
      val = x;
   }
}

// a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than
// 1
public class BalancedBTOrNotUsingMaximumHeight
{


   public boolean isBalanced(TreeNode root)
   {
      if (getDepth(root) == -1)
         return false;

      return true;
   }


   private int getDepth(TreeNode root)
   {
      if (root == null)
         return 0;

      int left = getDepth(root.left);
      int right = getDepth(root.right);

      if (left == -1 || right == -1 || Math.abs(left - right) > 1)
         return -1;

      return Math.max(left, right) + 1;

   }
}
