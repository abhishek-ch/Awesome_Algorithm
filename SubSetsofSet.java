import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SubSetsofSet
{

	




	private List<List<Integer>> subsetsAgain(List<Integer> list, int index){
		List<List<Integer>> allSubsets = null;
		
		if(list.size() == index){
			allSubsets = new ArrayList<>();
			allSubsets.add(new ArrayList<Integer>());
			return allSubsets;
		}
			
		int value = list.get(index);
		allSubsets = subsetsAgain(list, index+1);
		
		List<List<Integer>> output = new ArrayList<>();
		for (List<Integer> list2 : allSubsets)
		{
			List<Integer> newsubset = new ArrayList<>();
			newsubset.addAll(list2);
			newsubset.add(value);
			output.add(newsubset);
		}
		allSubsets.addAll(output);
		
		return allSubsets;
		
	}
	
	
	public static void main(String[] args)
	{
		List<List<Integer>> finaloutput = new SubSetsofSet().subsetsAgain(Arrays.asList(1, 2, 3,4), 0);
		for (List<Integer> subsets : finaloutput) {
            System.out.println(subsets);
        }
	}

}
