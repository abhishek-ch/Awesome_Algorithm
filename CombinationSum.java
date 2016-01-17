import java.util.*;

/**
 * Created by abc on 17/01/2016.
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);

        List<Integer> items = new ArrayList<>();
        depthFirstSearch(candidates,result,items,0,target);

        return result;
    }

    private void depthFirstSearch(int[] candidates,List<List<Integer>> result,List<Integer> items , int start, int target){

        if(target < 0){
            return;
        }

        if(target == 0){
            result.add(new ArrayList<Integer>(items));
            return;
        }

        for (int i = start; i <candidates.length ; i++) {
            items.add(candidates[i]);
            depthFirstSearch(candidates,result,items,i,target-candidates[i]);
            items.remove(items.size()-1);
        }

    }

    public static void main(String[] args) {

    }
}
