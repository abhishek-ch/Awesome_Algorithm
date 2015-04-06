import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class HeightOfTreeFromArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int HEIGHT = 0;
		int[] array = {3,3,3,-1,2,1,0,1,4};
		//maintains the node and its corresponding child
		Map<Integer,List<Integer>> map = new HashMap<Integer, List<Integer>>();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		Queue<Integer> pq = new LinkedList<Integer>();
		
		//iterate through each of the element and save the particular node and its key
		for(int i=0;i<array.length;i++){
			int value = array[i];
			if(map.get(value) != null){
				List<Integer> list = map.get(value);
				list.add(i);
			}else{
				List<Integer> list = new ArrayList<Integer>();
				list.add(i);
				map.put(array[i], list);
			}
			//appends the root node
			if(value == -1){
				queue.add(i);
			}
		}
		
		Set<Integer> keySet = map.keySet();
		for(Integer i : keySet){
			System.out.println("Value "+i+" Size "+map.get(i).size());
		}
		
		//Run Simply DFS on Map and we are done
		while(!queue.isEmpty()){
			Integer node = queue.poll();
			List<Integer> list = map.get(node);
			if(list != null && list.size() >0){
				for(Integer child:list){
					pq.add(child);
				}
			}
			
			if(queue.isEmpty()){
				if(!pq.isEmpty()){
					queue.addAll(pq);
					pq.clear();
					HEIGHT++;
				}
			}
			
		}
		
		System.out.println("Height of the Tree "+HEIGHT);
		
	}

}
