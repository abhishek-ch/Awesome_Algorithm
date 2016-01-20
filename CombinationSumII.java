import java.util.*;

/**
 * Created by achoudhary on 20/01/2016.
 * https://leetcode.com/problems/combination-sum-ii/
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>>  result = new ArrayList<>();
        Arrays.sort(candidates);
        depthFirstSearchMaintainingUnique(candidates,target,result,new ArrayList<Integer>(), new HashSet<List<Integer>>(),0);

        return result;

    }

    private void depthFirstSearchMaintainingUnique(int[] candidates, int target,List<List<Integer>>  result, List<Integer> proxy, Set<List<Integer>> uniqueSet, int start){
        if(target < 0)
            return;

        if(target == 0){
            if(uniqueSet.add(proxy)) {
                result.add(new ArrayList<>(proxy));
            }
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            proxy.add(candidates[i]);
            depthFirstSearchMaintainingUnique(candidates,target-candidates[i],result,proxy,uniqueSet,i+1);
            proxy.remove(proxy.size()-1);
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new CombinationSumII().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        for (List<Integer> val:
             lists) {
            System.out.println(" Output : "+val);
        }
    }
}
