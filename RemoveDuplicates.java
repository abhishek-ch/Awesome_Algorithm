import java.util.LinkedList;
import java.util.List;




public class RemoveDuplicates
{


   public static void main(String[] args)
   {
      int[] nums = {1,1,1,2};
      
      int prev = -1;
      int index = 0;
      int count =0;
      List<Integer> values = new LinkedList<>();
      while(index < nums.length){
         int curr =  nums[index];
         
         if(curr != prev){
            values.add(curr);
            count = 1;
         }else if(curr == prev && count < 2){
            values.add(curr);
            count++;
         }else {
            System.err.println(count);
            count++;
         }
         prev = curr;
         index++;
      }
      
      
      System.err.println(values);
   }

   

   
}

 /*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 *
 * Created on 9 Oct 2015
 */