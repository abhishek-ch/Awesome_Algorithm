/**
 * Created by achoudhary on 04/01/2016.
 *
 * Simple Binary Search operation with critical finding of how target falls under category
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {

        return tweakedBinarySearch(nums,0,nums.length-1,target);
    }

    public int tweakedBinarySearch(int[] nums, int left, int right, int target){
        if(nums == null || nums.length == 0 || left > right){
            return -1;
        }

        int mid = left + (right -left)/2;
        if(target == nums[mid]){
            return mid;
        }


        //if right rotated
        //7 8 9 0 1 2 3 4 5 6 7
        if(nums[left] > nums[mid]){
            if((target > nums[mid] && target > nums[right]) || (target < nums[mid])){
                return tweakedBinarySearch(nums,left,mid-1,target);
            }else{
                return tweakedBinarySearch(nums,mid+1,right,target);
            }
        }else{
            //left rotated
            //3 4 5 6 7 8 9 0 1 2
            if((target > nums[mid] && target > nums[left]) || (target < nums[mid] && target < nums[left])){
                return tweakedBinarySearch(nums,mid+1,right,target);
            }else{
                return tweakedBinarySearch(nums,left,mid-1,target);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(new SearchInRotatedSortedArray().search(new int[]{7,8,9,0,1,2,3,4,5,6},7));
    }
}
