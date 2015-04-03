import java.util.HashMap;
import java.util.Map;
/**
 * A HashTable maintains the value left to match the target by subtracting 1st item with the target
 * if any value matches the required current value of the loop it does mean the hashtable value holds
 * the other element to get the required sum.
 * @author achoudhary
 *
 */
public class SumOfTwoNumbersToGetThird {
    public int[] twoSum(int[] numbers, int target) {
        int[] output = new int[2];
       
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i< numbers.length;i++){
        	if(map.containsKey(numbers[i])){
        		int value = map.get(numbers[i]);
        		output[0] = value+1;
        		output[1] = i;
        		break;
        	}else{
        		map.put(target-numbers[i], i);
        	}
        }
        
        return output;
    }
    
    public static void main(String[] args)
	{
    	int[] numbers={3,2,4};
		int[] output = new SumOfTwoNumbersToGetThird().twoSum(numbers, 6);
		System.out.println(output[0]+" "+output[1]);
	}
}