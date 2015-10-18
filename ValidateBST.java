
//http://www.programcreek.com/2012/12/leetcode-validate-binary-search-tree-java/
public class ValidateBST {

	
	
	 public boolean isValidBST(TreeNode root) {

		 return isValidate(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	    }
	
	 private boolean isValidate(TreeNode node, int left, int right){
		 
		 if(node == null)
			 return true;
		 
		 if(node.val <= left || node.val >= right)
			 return false;
		 
		 return isValidate(node.left, Math.min(right, node.val), left) && isValidate(node.right, right, Math.max(left, node.val));
	 }
	 
}
