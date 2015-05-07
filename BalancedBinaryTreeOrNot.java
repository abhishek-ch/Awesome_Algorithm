public class BalancedBinaryTreeOrNot
{

	class Node
	{
		public int value;
		public Node left;
		public Node right;
	}

	private int findMinimumNodeValue(Node node)
	{

		if (node == null)
			return 0;

		return Math.min(findMinimumNodeValue(node.left),
				findMinimumNodeValue(node.right)) + 1;

	}

	private int findMamimumNodeValue(Node node)
	{

		if (node == null)
			return 0;

		return Math.max(findMinimumNodeValue(node.left),
				findMinimumNodeValue(node.right)) + 1;

	}

	public boolean findBalancedTree(Node root)
	{

		return !(findMamimumNodeValue(root) - findMamimumNodeValue(root) > 1);

	}

}