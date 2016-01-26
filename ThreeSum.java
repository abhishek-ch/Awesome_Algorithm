import java.util.*;

/**
 * Created by achoudhary on 26/01/2016.
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {



        List<List<Integer>> result = new ArrayList<>();

        if(nums.length < 3)
            return result;

        Arrays.sort(nums);
        Set<List<Integer>> proxy = new HashSet<>();


        for (int i = 0; i < nums.length-2; i++) {

            if(i ==0 || nums[i] > nums[i-1]) {

                int target = -nums[i];
                int start = i + 1;
                int end = nums.length - 1;

                while (start < end) {

                    if (nums[start] + nums[end] == target) {
                        List<Integer> value = new ArrayList<>();
                        value.add(nums[i]);
                        value.add(nums[start]);
                        value.add(nums[end]);

                        proxy.add(value);

                        start++;
                        end--;

                    } else if (nums[start] + nums[end] > target) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }

        result.addAll(proxy);

        return result;
    }

    public static void main(String[] args) {

    }
}
