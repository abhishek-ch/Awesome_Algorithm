
public class SearchInsertPosition
{

	public static void main(String[] args)
	{
		int[] input ={1,3,5,6};
		int output = new SearchInsertPosition().searchInsert(input, 10);
		System.out.println(output);
	}

	 public int searchInsert(int[] nums, int target) {
	        return binarySearch(nums, target);
	    }
	
	
	private int binarySearch(int[] nums , int target){
		int lo = 0;
		int hi = nums.length-1;
		int nearestkey = 0;
		while(lo <= hi){
			int mid = lo + (hi -lo)/2;
			if(target < nums[mid]){
				hi = mid-1;
			}else if(target > nums[mid]){
				lo = mid+1;
			}else{
				return mid;
			}
			nearestkey = Math.max(hi, lo);
		}
		return nearestkey;
	}
	
}
