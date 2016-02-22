/**
 * Created by achoudhary on 22/02/2016.
 */
public class SearchInRotatedArrayII {

    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while(left <= right){
            int mid = (left + right)/2;
            if(nums[mid] == target)
                return true;

            if(nums[left] < nums[mid]){
                if(nums[left] <= target && target < nums[mid]){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }else if(nums[left] > nums[mid]){
                if(nums[mid] < target && target <= nums[right]){
                    left = mid+1;
                }else{
                    right = mid -1;
                }
            }else{
                left++;
            }
        }


        return false;
    }


    public static void main(String[] args) {

    }
}
