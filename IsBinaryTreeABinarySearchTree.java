public class IsBinaryTreeABinarySearchTree {
	boolean isBTaBST(Node n, int min, int max) {

		if (n == null || n.left == null || n.right == null) {
			return true;
		}

		return n.value > min && n.value < max && isBTaBST(n.left, n.value, max)
				&& isBTaBST(n.right, min, n.value);

	}
}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 14 May 2015
 */