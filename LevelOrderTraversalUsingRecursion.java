
public class LevelOrderTraversalUsingRecursion {

	class Node{
		
		Node left;
		Node right;
		int value;
	}
	
	
	public void levelOrdeTraversalRecursion(Node n, int level){
		
		
		if(n != null){
			
			if(level == 0){
				System.out.println(n.value);
			}
		}
		levelOrdeTraversalRecursion(n.left, level-1);
		levelOrdeTraversalRecursion(n.right, level-1);
		
	}
	
	/**
	 * traverse each level of the tree by calling recursive method.
	 * For example if we want to print each level till 4 (height)
	 * ---
	 * Then recursion will traverse each level and negate as shown above , 4-1 , 4-2, 4-3 ,4-4
	 * and ultimately it will reach the level 4 the value will become 0 , and it will print
	 * 
	 * @param root
	 * @param depth
	 */
	public void traversal(Node root,int depth){
		for(int i=0;i<depth;i++){
			levelOrdeTraversalRecursion(root, i);
		}
	}
}
