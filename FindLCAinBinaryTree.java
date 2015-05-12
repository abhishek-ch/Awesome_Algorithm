import java.util.ArrayList;
import java.util.List;

public class FindLCAinBinaryTree {

	int findLCA(Node root, Node A, Node B) {

		inorder(root);
		postorder(root);

		int positionA = inorderList.indexOf(A.value);
		int positionB = inorderList.indexOf(B.value);

		List<Integer> sublist = inorderList.subList(positionA, positionB);
		int iNodeValue = 0;
		for (Integer val : sublist) {

			iNodeValue = Math.max(iNodeValue, postorderList.indexOf(val));

		}

		return iNodeValue;

	}

	List<Integer> inorderList = new ArrayList<Integer>();
	List<Integer> postorderList = new ArrayList<Integer>();

	public void inorder(Node n) {

		if (n == null)
			return;

		inorder(n.left);
		inorderList.add(n.value);
		inorder(n.right);

	}

	public void postorder(Node n) {

		if (n == null)
			return;

		postorder(n.left);
		postorderList.add(n.value);
		postorder(n.right);

	}

}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 12 May 2015
 */