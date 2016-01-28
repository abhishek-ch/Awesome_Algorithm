import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abc on 28/01/2016.
 */
public class LetterCombinationPhoneNumber {


    public List<String> letterCombinations(String digits) {

        List<Character> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0)
            return result;
        Map<String,String> map = new HashMap<>();
        map.put("1","");
        map.put("2","abc");
        map.put("3","def");
        map.put("4","ghi");
        map.put("5","jkl");
        map.put("6","mno");
        map.put("7","pqrs");
        map.put("8","tuv");
        map.put("9","wxyz");

        depthFirstSearch(digits,map,temp,result);
        return result;
    }

    private void depthFirstSearch(String digits, Map<String,String> map,List<Character> temp,List<String> result){

        if(digits.length() == 0){
            String out = "";
            for (char ch:
                 temp) {
                out += ch;
            }
            result.add(out);
            return;
        }

        String curr = digits.substring(0,1);
        String letters = map.get(curr);
        for (int i = 0; i < letters.length(); i++) {
            temp.add(letters.charAt(i));
            depthFirstSearch(digits.substring(1),map,temp,result);
            temp.remove(temp.size()-1);
        }

    }


    public static void main(String[] args) {
        System.out.println(new LetterCombinationPhoneNumber().letterCombinations("9"));
    }
}
