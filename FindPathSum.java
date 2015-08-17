public class FindPathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		if (sum == root.val && (root.left == null && root.right == null))
			return true;

		return hasPathSum(root.left, sum - root.val)
				|| hasPathSum(root.right, sum - root.val);
	}

}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 17 Aug 2015
 */