/**
 * Created by achoudhary on 06/01/2016.
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        if(height.length == 0)
            return 0;
        int output = 0;
        int[] leftMax = new int[height.length]; //hold the max left value
        int[] rightMax = new int[height.length]; //holds the right max value for any ith element

        int max = height[0];
        /**
         * iterate through ascending order to track maxLeft element
         * for each ith Element
         */
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = max;
            if(height[i] > max){
                max = height[i];
            }

        }


        /**
         * iterate through descending order to track maxRight element
         * for each ith Element
         */
        max = height[height.length-1];
        for (int i = height.length-2; i >= 0; i--) {
            rightMax[i] = max;
            if(height[i] > max){
                max = height[i];
            }
        }


        for (int i = 1; i < height.length - 1; i++) {
            int trap = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (trap > 0)
                output += trap;
        }
        return output;

    }
}
