import java.util.HashMap;

/**
 * Created by achoudhary on 23/12/2015.
 *
 * Logic behind the solution -
 * Iterate through each character of the input and fill a map with each iterating unique character upto K and build
 * a tracker string till the point the map size crosses K.
 * As soon as it crosses K, it means other extra character has encountered so tracker currently holds a series of
 * string with k unique characters,
 * So now its new character, clear the map and do same as aobove but in reverse direction of "tracker" and
 * first element of the map will be the new encountered character the 1st half of the logic. Once you have map
 * filled with k characters, start the llop or resume i.
 */
public class LongestSubStringContainKUniqueCharacter {


    public String getLongestStringWithKUniqueCharacters(String input, int k) {
        String output = "";
        HashMap<Character, Integer> map = new HashMap<>();
        String tracker = "";

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (map.size() <= k) {
                map.put(ch, 0);
                tracker += ch;
            }

            //windowing logic, track back and refill the map
            //with K new items , if found
            if (map.size() == k + 1) {
                output = ((tracker.length() - 1) > output.length()) ? tracker.substring(0, tracker.length() - 1) : output;
                map.clear(); //clears the map to refill

                String proxy = "";
                int backIndex = tracker.length() - 1;
                char curr = '\0';
                while (map.size() != k + 1 && backIndex > -1) {
                    curr = tracker.charAt(backIndex--);
                    proxy = curr + proxy; //add the new element in the end always
                    map.put(curr, 0);
                }
                map.remove(curr);
                tracker = proxy.substring(1, proxy.length());
            }

        }


        if (map.size() != k) {
            output = "";
        } else {
            output = (tracker.length() > output.length()) ? tracker : output;
        }

        return output;
    }

    public static void main(String[] args) {
        System.out.println("Longest Substring With K Unique Characterd : " + new LongestSubStringContainKUniqueCharacter().getLongestStringWithKUniqueCharacters("abcbbbbcccbdddadacb", 3));
    }
}
