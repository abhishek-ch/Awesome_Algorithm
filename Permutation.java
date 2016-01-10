import java.util.*;

/**
 * Created by abc on 10/01/2016.
 */
public class Permutation {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        //starts with 1st index
        return findPermutation(nums,0, result);
    }

    public  List<List<Integer>> findPermutation(int[] nums, int startIndex , List<List<Integer>> result){

        //if length of start increases the length of input , means we found
        //all values , add it to list
        if(startIndex >= nums.length){
            result.add(convertToList(nums));
        }

        for (int i = startIndex; i < nums.length; i++) {
            //swaps each index of item with all other index of items
            //recursively
            swap(nums,startIndex,i);
            //find the next permutation from start+1 index till last item
            findPermutation(nums,startIndex+1,result);
            swap(nums,startIndex,i);
        }

        return result;
    }


    private List<Integer> convertToList(int[] array){
        List<Integer> list = new ArrayList<>();
        for (int i:
             array) {
            list.add(i);
        }
        return list;
    }


    private int[] swap(int[] a , int i, int j){
        if(i != j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        return a;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new Permutation().permuteUnique(new int[]{1,2,3});
        for (List<Integer> value:
             result) {
            System.out.println("-> "+value);
        }
        //System.out.println("Permutations "+ new Permutation().permuteUnique(new int[]{1,2,3}));
    }
}
