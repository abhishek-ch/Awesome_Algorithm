/**
 * Created by abc on 26/12/2015.
 *
 * 1) Calculate the medians m1 and m2 of the input arrays ar1[]
 and ar2[] respectively.
 2) If m1 and m2 both are equal then we are done.
 return m1 (or m2)
 3) If m1 is greater than m2, then median is present in one
 of the below two subarrays.
 a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
 b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
 4) If m2 is greater than m1, then median is present in one
 of the below two subarrays.
 a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
 b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
 5) Repeat the above process until size of both the subarrays
 becomes 2.
 6) If size of the two arrays is 2 then use below formula to get
 the median.
 Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
 */
import java.util.*;
public class MedianOfTwoSortedArray_WithLogComplexity {


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return findMedian(nums1,0 , nums1.length,nums2,0,nums2.length);
    }



    private double findMedian(int[] array1, int arr1Start, int arr1End , int[] array2, int arr2Start,int arr2End){



        //if size == 2 for both th array.
        if(array1.length == 2 && array2.length == 2){
            return (Math.max(array1[0], array2[0]) + Math.min(array1[1], array2[1]))/2;
        }

        int[] arr1 = Arrays.copyOfRange(array1, arr1Start, arr1End);
        int[] arr2 = Arrays.copyOfRange(array2, arr2Start, arr2End);
        int first = getMedian(arr1);
        int second = getMedian(arr2);



        //if arr1 > arr2 , then sub array
        // arr1 = 0 .... median of arr1
        // arr2 = median of arr2 .... end
        if(arr1[first] == arr2[second]){
            return arr1[first];
        }
        else if(arr1[first] > arr2[second]){
            return  findMedian(arr1,0 , first+1, arr2,second, arr2.length);
        }else{
            return  findMedian(arr1,first , arr1.length, arr2,0, second+1);
        }

    }


    //find median of an array based on odd and even
    //number of items
    public int getMedian(int[] arr){
        int n = arr.length;
        if(n % 2 == 0){
            return (n + (n/2 -1))/2;
        }else{
            return n/2;
        }
    }



    public static void main(String[] args){
        int[] arr1 =   {1, 12, 15, 26, 38};
        int[] arr2 = {2, 13, 17, 30, 45};
        System.out.println(" Median of Sorted Array "+new MedianOfTwoSortedArray_WithLogComplexity().findMedianSortedArrays(arr1, arr2));
    }
}
