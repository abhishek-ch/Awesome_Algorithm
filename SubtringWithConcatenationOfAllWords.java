import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abc on 01/01/2016.
 */
public class SubtringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {


        List<Integer> result = new ArrayList<>(); //holds the result
        //add each word in the map to maintain the counter
        Map<String , Integer> holder = new HashMap<>();
        //maintained the other map if any case same words are repeated twice
        //or thrice like abc abc bef
        Map<String , Integer> matcher = new HashMap<>();
        for (String key:
             words) {
            if(holder.containsKey(key)){
             int value = holder.get(key);
                holder.put(key,++value);
            }else {
                holder.put(key, 1);
            }
            matcher.put(key,0);
        }



        //holds the pointer to start the matching
        int total = s.length();
        int wordLength = words[0].length();
        int allWordsSize = (words.length * wordLength);

        System.err.println("allWordsSizeallWordsSize "+allWordsSize+" BHA "+total);
        for(int i=0; i< total;i++){
            //next matching will be unnecessary because the remaining
            //string cant hold all the matches
            if((total - i) < allWordsSize) {
                System.err.println("------------------------------ "+i);
                break;
            }


            int curr = i;
            for(int j=0;j<words.length;j++){
                String eachWord = s.substring(curr,(curr + wordLength));
               // System.out.println("Word "+eachWord +" curr "+curr+" wo "+wordLength);
                if(matcher.containsKey(eachWord)){
                    int value = matcher.get(eachWord);
                    matcher.put(eachWord,++value);
                }else{
                    //if the key is not present it means the sequence broke
                    //just stop
                    break;
                }
                curr += wordLength;
            }



           // System.out.println("EVER EQUAL "+(matcher.equals(holder))+" matchermatcher "+matcher.values()+" holder "+holder.values()+" i "+i);
            if(matcher.equals(holder)){
                result.add(i);
            }
            matcher.replaceAll((k,v) -> 0);

        }


        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SubtringWithConcatenationOfAllWords().findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake",new String[]{"fooo","barr","wing","ding","wing"}));
    }
}
