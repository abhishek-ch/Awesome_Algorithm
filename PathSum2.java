import java.util.ArrayList;
import java.util.List;

public class PathSum2 {
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> mainList = new ArrayList<>();
		 findPath(root,sum,mainList,new ArrayList<Integer>());
		 return mainList;
	}
	
	
	public void findPath(TreeNode root, int sum, List<List<Integer>> mainList, ArrayList<Integer> arrayList){
		if(root == null)
			return;
		
		arrayList.add(root.val);
		sum -= root.val;
		if(root.left == null && root.right == null && sum == 0){
			mainList.add(new ArrayList<Integer>(arrayList));
		}else{
			findPath(root.left,sum,mainList,arrayList);
			findPath(root.right,sum,mainList,arrayList);
		}
		
		arrayList.remove(arrayList.size() - 1);
	}
}
