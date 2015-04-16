import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
 
public class FrequentlyOccuringCharacterWithCondition {
	public static void main(String[] args) {
		String line = "dcbadcbadcbadde*";
    HashMap<Character,Integer> counts = new HashMap<>();
    for(char c : line.toCharArray()) {
        Integer count = counts.get(c);
        if (count == null) {
            count = 0;
        }
        counts.put(c, ++count);
    }
    List<Entry<Character,Integer>> list = new ArrayList<>(counts.entrySet());
    Collections.sort(list, new Comparator<Entry<Character,Integer>>() {
        @Override
        public int compare(Entry<Character, Integer> o1,
                Entry<Character, Integer> o2) {
        	//System.out.println("o2.getValue() - o1.getValue() "+(o2.getValue() - o1.getValue())+" k1 "+o1.getKey()+" o2 "+o2.getKey());
        	int matching = o2.getValue() - o1.getValue();
        	if(matching == 0){
        		matching = o1.getKey() - o2.getKey();
        	}
            return matching;
        }
    });
    for(Entry<Character,Integer> entry : list) {
        System.out.println("The character "+entry.getKey() +" has occured for "+ entry.getValue()+" times");
    }}
 
 
}
 
