import java.util.LinkedList;
import java.util.List;



//https://www.4clojure.com/problem/53#prob-title
public class LongestIncreasingSubSequence
{


   public static void main(String[] args)
   {
      // TODO:achoudhary Auto-generated method stub
      int[] val = {5,6,7,8,3,4, 1 ,6,7,6,7,8,1,2,3,4,5,1};
      int pointer = val[0];
      List<Integer> output = new LinkedList<>();
      List<Integer> proxy = new LinkedList<>();
      proxy.add(pointer);
      
      for(int i=1;i<val.length;i++){
         
         if(val[i] - pointer != 1){
            if(proxy.size() > 1){
               if(output.size() < proxy.size()){
                  output.clear();
                  output.addAll(proxy);
               }
            }
            proxy.clear();
         
     
         }
         pointer = val[i];
         proxy.add(pointer);
      }

      System.err.println(output);
      
   }

}

