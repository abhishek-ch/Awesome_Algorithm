
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

//http://www.mkyong.com/maven/how-to-create-a-java-project-with-maven/
public class OutputMain {
	/**
	 * TreeMap that holds encodings from Letters to Numbers
	 * **/
    public static Map<String,String>LetterEncodings = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
    /**
     * SortedMap that holds encodings from Letters to Numbers
	 * **/
    public static SortedMap<String,ArrayList<String>> map = new TreeMap<String,ArrayList<String>>();
    /**
     * boolean value that serves as the base case when a pairing is found
	 * **/
    private static boolean state = false;
   
    /**
     * Initialize is a method that maps the letters to numbers
     * and stores them in the mapp called LetterEncodings.
     * 
     * **/
    public static void Initalize(){
    	LetterEncodings.put("E", "0");
        
    	LetterEncodings.put("J", "1");
        LetterEncodings.put("N", "1");
        LetterEncodings.put("Q", "1");
        
        LetterEncodings.put("R", "2");
        LetterEncodings.put("W", "2");
        LetterEncodings.put("X", "2");
        
        LetterEncodings.put("D", "3");
        LetterEncodings.put("S", "3");
        LetterEncodings.put("Y", "3");
        
        LetterEncodings.put("F", "4");
        LetterEncodings.put("T", "4");
        
        LetterEncodings.put("A", "5");
        LetterEncodings.put("M", "5");
        
        LetterEncodings.put("C", "6");
        LetterEncodings.put("I", "6");
        LetterEncodings.put("V", "6");
        
        LetterEncodings.put("B", "7");
        LetterEncodings.put("K", "7");
        LetterEncodings.put("U", "7");
        
        LetterEncodings.put("L", "8");
        LetterEncodings.put("O", "8");
        LetterEncodings.put("P", "8");
        
        LetterEncodings.put("G", "9");
        LetterEncodings.put("H", "9");
        LetterEncodings.put("Z", "9");
    }
    
    /**
     * Translates a char array of numbers into letters based on
     * the encodings put inside of the mapping called LetterEncodings.
     * 
     * @param char[] dict
     *            Accepts the combined cube fragments
     *
     *　@return String output
     *			The returned string is a translation of the numbers into letters.
     */
    public static String translate(char[] dict){
        String output = "";
    
        for (int i = 0; i < dict.length; i++){
            String xp = LetterEncodings.get(dict[i]+"");
            if (xp != null){
                output += xp;
            }
        }
        return output;
    }
    /**
     * Creates a dictionary of words translated to numbers.
     * This is done by taking each entry in a text file and mapping
     * each letter to its LetterEncodings and putting them in a SortedMap
     * called map.
     * 
     * @param File dictionary
     *            Text file of dictionary terms.
     *
     */
    public static void createDict(File dictionary){
        try {
            Scanner scan = new Scanner(dictionary);
            int count = 0;
            while(scan.hasNext()){
                if(count == 75000){
                    return;
                }else{
                    count++;
                    String num = scan.next();
                    String numex = num.replaceAll("[/-]+", "");
                    if(num.length() <= 50){
                    //map.put(translate(numex.toCharArray()), num);
                    addValues(translate(numex.toCharArray()), num);
                    }
                }
                
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    /**
     * addValues essentially takes the translated text to numbers and adds it to 
     * a SortedMap called map where the translated text is the key for text before its
     * translated.
     * 
     * @param String key, String value
     *            key represents the translated text to numbers.
     *            value is the text before it was translated to numbers.
     *
     */
    private static void addValues(String key, String value) {
           ArrayList tempList = null;
           if (map.containsKey(key)) {
              tempList = map.get(key);
              if(tempList == null)
                 tempList = new ArrayList();
              tempList.add(value);  
           } else {
              tempList = new ArrayList();
              tempList.add(value);               
           }
           map.put(key,tempList);
        }
    /**
     * Confirm essentially determines if there is a matching between
     * phone numbers and translated text.
     * 
     * @param char[] dict, char[] numb
     *            dict represents the translated text to numbers.
     *            numb represents the phone number that is being matched.
     *            
     * @return true or false
     *			If a matching is found true is returned. Otherwise, false is returned.
     *
     */
        public static boolean confirm(char[]dict, char []numb){
        	//System.err.println(dict);
        	//System.err.println(numb);
        int count = 0;
        
        if (dict.length > numb.length){

            return false;

        }

        for(int i = 0; i < dict.length;i++){

            if( dict.length <= i && count == dict.length){

                return true;

            }else if(numb[i] == dict[i]){

            count++;

            }

        }

        if(count == dict.length)return true;
       

        return false;

        }
        
        
        
        
        /**
         * Lookup essentially takes each phone number and attempts to find a matching within the
         * SortedMap called map.
         * 
         * @param String num, String match, String original
         *             num represents the phone number that is being matched.
         *             match stores a copy of a dictionary word if a match is found
         *             original is the dictionary unmodified
         *            
         *
         */
        static String lost = "";
        public static void Lookup1(String num, String match,String original){
        	String local = "";
        	int index = 0;
            String prefix = num.replaceAll("[/-]+", "");
            if(prefix.length() == 1){
            	return;
            }
            
               
            SortedMap<String,ArrayList<String>> mo = filterPrefix(map, prefix);
            for(Map.Entry<String,ArrayList<String>> entry :mo.entrySet()) {
            	for(int i = 0; i < entry.getValue().size(); i++){
            		String gattai = entry.getValue().get(i).replaceAll("\"", "");
            		String key = entry.getKey();
            		boolean con = key.equals(prefix) || prefix.startsWith(key);
            		if(match.replace(" ", "").length() == original.length()){
            			System.out.println(original + " : "+lost + match);
            			lost = "";
            			
            		}else if((prefix.length() - gattai.length()) == 1&& con){
            			System.out.println(original + " : "+lost + match + entry.getValue().get(i) + " " +prefix.substring(prefix.length()-1, prefix.length()));
            			lost = "";
            		}else if(gattai.length() < prefix.length()&& con){
            			index = gattai.length();
            			local = entry.getValue().get(i);
            			String lo = prefix.substring(gattai.length(), prefix.length());
            			Lookup1(lo,match + entry.getValue().get(i) + " ",original);
            		}else if (prefix.length() == gattai.length()&& con){
            			System.out.println(original + " : "+lost+ " " + match + gattai );
            			lost = "";
            			state = true;
            			
            		}
            	}
            	
            }
            if( match.length()< num.length()&& !state ){
            	String m = match.replace(" ", "");
            	if(m.length() == 0){
            		if(index > 0)
            			lost = local;
            		match += prefix.substring(index, index+1) + " ";
            		prefix = prefix.substring(index+1, prefix.length());
            		Lookup1(prefix,match,original);
            	}else if (m.substring(m.length()-1).matches("[0-9]+")){
            		return;
            	}
            }else if(match.length() > num.length()&& !state ){
            		String testMatch = match.trim();
            		char charAt = testMatch.charAt(testMatch.length()-1);
            		if(Character.isDigit(charAt))
            			return;
            		match += prefix.substring(0,1) + " ";
            		prefix = prefix.substring(1);
            		Lookup1(prefix,match,original);
            }
            
        }
        
        /**
         * Lookup essentially takes each phone number and attempts to find a matching within the
         * SortedMap called map.
         * 
         * @param String num, String match, String original
         *             num represents the phone number that is being matched.
         *             match stores a copy of a dictionary word if a match is found
         *             original is the dictionary unmodified
         *            
         *
         */
        public static void Lookup(String num, String match,String original){
            
         
            String prefix = num.replaceAll("[/-]+", "");
            if(prefix.length() == 1){
            	return;
            }
            
               
            SortedMap<String,ArrayList<String>> mo = filterPrefix(map, prefix);
            for (Map.Entry<String,ArrayList<String>> entry : mo.entrySet()) {
                //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
            Set<Entry<String, ArrayList<String>>> ntry =  mo.entrySet();
            for(Map.Entry<String,ArrayList<String>> entry :ntry) {
            	for(int i = 0; i < entry.getValue().size(); i++){
            		String gattai = entry.getValue().get(i);
            		String key = entry.getKey();
            		boolean con = key.equals(prefix) || prefix.startsWith(key);
            		//boolean con = confirm(key.toCharArray(),prefix.toCharArray());
            		if(match.replace(" ", "").length() == original.length()){
            			System.out.println( original + " : " + match);
            			
            		}else if((prefix.length() - gattai.replaceAll("\"", "").length()) == 1&& con == true){
            			String op = gattai.replaceAll("\"", "");
            			System.out.println( original + " : " + match + entry.getValue().get(i) + " " +prefix.substring(prefix.length()-1, prefix.length()));
            			
            		}else if(gattai.replaceAll("\"", "").length() < prefix.length()&& con == true){
            			String vo = gattai.replaceAll("\"", "");
            			String lo = prefix.substring(gattai.replace("\"", "").length(), prefix.length());
            			Lookup(lo,match + gattai + " ",original);
            			
            		}else if (prefix.length() == gattai.length()&& con == true){
            			
            			System.out.println( original + " : " + match + gattai );
            			state = true;
            			
            		}
            	}
            	
            }
            if( match.length()< num.length()&& state == false){
            	String m = match.replace(" ", "");
            	if(m.length() == 0){
            		//match += num.substring(0, 1) + " ";
            		match += prefix.substring(0, 1) + " ";
            		//prefix = num.substring(1, num.length());
            		prefix = prefix.substring(1, prefix.length());
                	Lookup(prefix,match,original);
            	}else if (m.substring(m.length()-1).matches("[0-9]+")){
            		return;
            	}
            }
            
        }
        /**
         * filterPrefix essentially runs a partial search on SortedMap map to find any keys that 
         * contain references to the String prefix.
         * 
         * @param SortedMap<String, V> baseMap, String prefix
         *            baseMap is the SortedMap that will contain the submappings of the prefix's first character
         *            all the way to the end(entire String of prefix).
         *            
         *            prefix is the String that will be searched for inside of baseMap.
         *             
         * @return <V>SortedMap <String, V>
         *			   A list of keys from SortedMap map that contain references to prefix         
         *
         */
        public static <V> SortedMap<String, V> filterPrefix(SortedMap<String,V> baseMap, String prefix) {
        	
        	if(prefix.length() > 0) {
                char nextLetter = (char) (prefix.charAt(prefix.length() -1)+ 1);
                String end = prefix.substring(0, prefix.length()-1) + nextLetter;
                String range = prefix.substring(0, 2);
              //  String leaf = prefix.substring(0, prefix.length()-1);
                SortedMap<String,V> mo = baseMap.subMap(range,end);
                
                return mo; 
            }
            return baseMap;
        }
            
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
     
       
        Initalize();

        File dictionary = new File("dictionary.txt");
        File Numbers = new File("input.txt");
        try {
            Scanner nc = new Scanner(Numbers);
            createDict(dictionary);
              
             while(nc.hasNext()){
                String num = nc.next();
                String match = "";
                lost = "";
                Lookup1(num,match,num);
                state = false; 
            }
            nc.close();
               
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }

}

