import java.util.*;

/**
 * Created by achoudhary on 18/01/2016.
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        Set<List<Integer>> set = new HashSet<>();

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int k = j+1;
                int l = nums.length-1;
                while(k < l){
                    int curr = nums[i]+nums[j]+nums[k]+nums[l];
                    if(curr < target){
                        k++;
                    }else if(curr > target){
                        l--;
                    }else if(curr == target){
                        List<Integer> tuple = new ArrayList<>();
                        tuple.add(nums[i]);
                        tuple.add(nums[j]);
                        tuple.add(nums[k]);
                        tuple.add(nums[l]);
                        if(set.add(tuple)){
                            result.add(tuple);
                        }
                        k++;
                        l--;
                    }
                }
            }
        }

        return result;




    }


    private void depthFirstSearch(int[] nums, List<List<Integer>> result,List<Integer> tuple, int start , int target,int ultimateTarget){
        if(tuple.size() > 4){
            return;
        }

        System.err.println("target "+target);
        if(target == ultimateTarget && tuple.size() == 4){
            System.out.printf("AAKKAKA "+tuple);
            result.add(tuple);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if(tuple.size() == 0 || tuple.get(tuple.size()-1) < nums[i]) {
                tuple.add(nums[i]);

            }else{
                return;
            }
            depthFirstSearch(nums,result,tuple,i,target+nums[i],ultimateTarget);
            tuple.remove(tuple.size()-1);


        }
    }


    public static void main(String[] args) {
        int[] input = new int[]{1,0,-1,0,-2,2};
        System.out.println(new FourSum().fourSum(input , 0));
    }
}
