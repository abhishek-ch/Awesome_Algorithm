/**
 * Simply get the middle element and all left node will be node.left and right will be right
 * @author achoudhary
 *
 */
public class BalancedBinaryTreeFromSortedArray {

	public void buildTree(int[] arr) {
		Node node = createBalancedBinaryTree(arr, 0, arr.length - 1);
	}

	public Node createBalancedBinaryTree(int[] arr, int start, int end) {

		if (start > end)
			return null;

		// int mid = (start + end)/2; //avoid for overflow
		int mid = start + (end - start) / 2;
		Node node = new Node(arr[mid]);
		node.left = createBalancedBinaryTree(arr, start, mid - 1);
		node.right = createBalancedBinaryTree(arr, mid + 1, end);

		return node;
	}

}