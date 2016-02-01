import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by achoudhary on 01/02/2016.
 */


 class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
     }


public class MergeIntervals {

    public List<Interval> merge(List<Interval> intervals) {

        if(intervals.size() <= 1){
            return intervals;
        }


        Collections.sort(intervals,(Interval o1, Interval o2) -> o1.start - o2.start);


        List<Interval> result = new ArrayList<>();

        Interval prev = intervals.get(0);
        for (int i = 1; i <intervals.size() ; i++) {
            Interval curr = intervals.get(i);


            if(prev.end >= curr.start){
                Interval merged = new Interval(prev.start,Math.max(prev.end,curr.end));
                prev = merged;
            }else{
                result.add(prev);
                prev = curr;
            }


        }

        result.add(prev);

        return result;
    }


    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();

        list.add(new Interval(1,4));
        list.add(new Interval(4,5));
        List<Interval> res = new MergeIntervals().merge(list);
        for (Interval i:
                res) {
            System.out.println(i.start+" "+i.end);
        }
    }

}
