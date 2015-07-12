

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class CustomizeTrie {

	private static final Map<Character, Integer> charTOIntMap = new HashMap<Character, Integer>();
	public static final SortedMap<String, List<String>> dictionary = new TreeMap<String, List<String>>();

	public CustomizeTrie() {
		charTOIntMap.put('E', 0);

		charTOIntMap.put('J', 1);
		charTOIntMap.put('N', 1);
		charTOIntMap.put('Q', 1);

		charTOIntMap.put('R', 2);
		charTOIntMap.put('W', 2);
		charTOIntMap.put('X', 2);

		charTOIntMap.put('D', 3);
		charTOIntMap.put('S', 3);
		charTOIntMap.put('Y', 3);

		charTOIntMap.put('F', 4);
		charTOIntMap.put('T', 4);

		charTOIntMap.put('A', 5);
		charTOIntMap.put('M', 5);

		charTOIntMap.put('C', 6);
		charTOIntMap.put('I', 6);
		charTOIntMap.put('V', 6);

		charTOIntMap.put('B', 7);
		charTOIntMap.put('K', 7);
		charTOIntMap.put('U', 7);

		charTOIntMap.put('L', 8);
		charTOIntMap.put('O', 8);
		charTOIntMap.put('P', 8);

		charTOIntMap.put('G', 9);
		charTOIntMap.put('H', 9);
		charTOIntMap.put('Z', 9);
	}

	public void add(String word) {
		String intPattern = findPattern(word);
		// System.out.println(intPattern);
		List<String> wordList = dictionary.get(intPattern);
		// if null , create the list
		if (wordList == null) {
			wordList = new ArrayList<>();
		}
		// add the value in the list
		wordList.add(word);
		// update the Map
		dictionary.put(intPattern, wordList);
	}
	
	/**
	 * Build the DataStructure Trie Dictionary
	 * @param fileName
	 */
	public void buildTrie(String fileName){
		try (BufferedReader br = new BufferedReader(new FileReader(
				fileName))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				this.add(sCurrentLine.trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void encodeNumber(String fileName){
	      File Numbers = new File(fileName);
	        try {
	            Scanner nc = new Scanner(Numbers);
	             while(nc.hasNext()){
	                String num = nc.next();
	                String match = "";
	                lost = "";
	                search(num,match,num);
	                state = false; 
	            }
	            nc.close();
	               
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	private String findPattern(String word) {
		String output = "";
		for (char ch : word.toCharArray()) {
			if (Character.isLetter(ch)) {
				output += charTOIntMap.get(Character.toUpperCase(ch));
			}
		}
		return output;
	}

	
	
	public <V> SortedMap<String, V> filterPrefix(SortedMap<String,V> baseMap, String prefix) {
       	
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
     String lost = "";
    private  boolean state = false;
    public  void search(String num, String match,String original){
    	String local = "";
    	int index = 0;
        String prefix = num.replaceAll("[/-]+", "");
        if(prefix.length() == 1){
        	return;
        }
        
           
        SortedMap<String, List<String>> mo = filterPrefix(dictionary, prefix);
        for(Map.Entry<String,List<String>> entry :mo.entrySet()) {
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
        			search(lo,match + entry.getValue().get(i) + " ",original);
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
        		search(prefix,match,original);
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
        		search(prefix,match,original);
        }
        
    }
	

}
