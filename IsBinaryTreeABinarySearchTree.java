public class IsBinaryTreeABinarySearchTree {
	
	public boolean isValid(Node root) {
	     return isBTaBST(root, Integer.MIN_VALUE,
	          Integer.MAX_VALUE);
	}
	
	boolean isBTaBST(Node n, int min, int max) {

		if (n == null || n.left == null || n.right == null) {
			return true;
		}

		return n.value > min && n.value < max && isBTaBST(n.left, n.value, max)
				&& isBTaBST(n.right, min, n.value);

	}
}

