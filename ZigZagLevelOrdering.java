
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by achoudhary on 11/03/2016.
 */

// Definition for a binary tree node.


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ZigZagLevelOrdering {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;



        Stack<TreeNode> currStack = new Stack<>();
        Stack<TreeNode> nextStack = new Stack<>();
        Stack<TreeNode> tmp;
        currStack.push(root);
        boolean leftOrdering = true;

        while(!currStack.isEmpty()){
            List<Integer> proxyResult = new ArrayList<>();

            while(!currStack.empty()){
                TreeNode node = currStack.pop();
                proxyResult.add(node.val);
                if(leftOrdering){
                    if(node.left != null){
                        nextStack.push(node.left);
                    }
                    if(node.right != null){
                        nextStack.push(node.right);
                    }
                }else{
                    if(node.right != null){
                        nextStack.push(node.right);
                    }
                    if(node.left != null){
                        nextStack.push(node.left);
                    }

                }
            }


            result.add(proxyResult);
            tmp = currStack;
            currStack = nextStack;
            nextStack = tmp;
            leftOrdering = !leftOrdering;
        }

        return result;
    }

    public static void main(String[] args) {

    }
}



