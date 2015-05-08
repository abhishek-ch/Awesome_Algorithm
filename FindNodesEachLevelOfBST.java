import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FindNodesEachLevelOfBST {

	public List<List<Node>> findNodesInParticularDepth(Node node) {

		Stack<Node> main = new Stack<Node>();
		Stack<Node> local = new Stack<Node>();

		List<List<Node>> nodeList = new ArrayList<>();

		if (node == null) {
			return null;
		}

		List<Node> list = new ArrayList<Node>();
		main.add(node);
		nodeList.add(main); // adds te first level
		while (!main.isEmpty()) {

			Node elem = main.pop();

			if (elem.left != null) {
				local.add(elem.left);
			}

			if (elem.right != null) {
				local.add(elem.right);
			}

			if (main.isEmpty() && local.size() > 0) {
				list = new ArrayList<>();

				list.addAll(local);
				nodeList.add(list);

				main.addAll(local);
				local.clear();

			}
		}

		return nodeList;
	}

}