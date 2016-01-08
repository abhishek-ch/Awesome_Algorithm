/**
 * Created by achoudhary on 08/01/2016.
 */
public class JumpGame1 {

    public boolean canJump(int[] nums) {
        if(nums.length == 0)
            return false;

        int maxLength = 0;
        int steps = 1;
        for (int i = 0; i < nums.length; i++) {
            steps--;

            if(nums[i] + i > maxLength){
                maxLength = nums[i] + i;
                steps = nums[i];
            }

            if(steps == 0 && i < nums.length-1){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame1().canJump(new int[]{2,3,1,0,4}));
    }
}
