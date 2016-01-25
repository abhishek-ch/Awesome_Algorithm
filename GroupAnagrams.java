import java.util.*;

/**
 * Created by achoudhary on 25/01/2016.
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Arrays.sort(strs);
        List<List<String>> result = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] val = strs[i].toCharArray();
            Arrays.sort(val);
            String actual = String.valueOf(val);
            if(map.containsKey(actual)){
                List<String> list = map.get(actual);
                list.add(strs[i]);
                map.put(actual,list);
            }else{
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(actual,list);
            }
        }

        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    public static void main(String[] args) {
        new GroupAnagrams().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    }
}
