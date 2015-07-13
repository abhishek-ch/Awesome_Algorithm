package com.numberencoding;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import com.numberencoding.signature.ITrie;

public class CustomizeTrie implements ITrie{

	private Map<Character, Integer> charTOIntMap = null;
	private final SortedMap<String, List<String>> dictionary = new TreeMap<String, List<String>>();
    private String lost = "";
    private  boolean state = false;

	public CustomizeTrie() {
		charTOIntMap = new CharacterMapper().buildCharacterMapper();
	}
	
	/**
	 * add each to the dictionary and based on each character of the word,
	 * build a key sequence for each word.
	 * 
	 * Modified the common Trie perception as here we maintained a key hashcode as a dictionary
	 * to hold a list of matching words
	 * @param word
	 */
	private void add(String word) {
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
	 * returns the build datastructure
	 * @return
	 */
	public SortedMap<String, List<String>> getDataStructure(){
		return dictionary;
	}
	
	/**
	 * Build the DataStructure Trie Dictionary
	 * @param fileName
	 */
	public boolean buildTrie(String fileName){
		try (BufferedReader br = new BufferedReader(new FileReader(
				fileName))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				this.add(sCurrentLine.trim());
			}

		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * public api to encode list of Numbers to be encoded.
	 * This api is expecting a file path which will be parsed line by line and following output will be published
	 * @param fileName
	 */
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
	            //e.printStackTrace();
	        }
	}
	
	/**
	 * Pattern will be selected from the Dictionary developed in {@link CharacterMapper}
	 * Each character of the word is associated with a Number and that number in String format will be developed
	 * 
	 * currently the return type is made to String which can be made as Integer if required, but current to access the
	 * bucket using hashcode technique will be constant time .
	 * @param word
	 * @return
	 */
	private String findPattern(String word) {
		String output = "";
		for (char ch : word.toCharArray()) {
			if (Character.isLetter(ch)) {
				output += charTOIntMap.get(Character.toUpperCase(ch));
			}
		}
			
		return output;
	}

	
	/**
	 * This is way to find prefix on a regular Trie structure.
	 * As we are trying to search the prefix, used the already existing subMap 
	 * which Returns a view of the portion of this map whose keys range from fromKey, inclusive, to toKey, exclusive.
	 * 
	 * So it will behave like trie but this is not an entire Trie
	 * @param baseMap
	 * @param prefix
	 * @return
	 */
	private <V> SortedMap<String, V> searchPrefix(SortedMap<String,V> baseMap, String prefix) {
       	
       	if(prefix.length() > 0) {
               char nextLetter = (char) (prefix.charAt(prefix.length() -1)+ 1);
               String end = prefix.substring(0, prefix.length()-1) + nextLetter;
               String range = prefix.substring(0, 2);
               SortedMap<String,V> mo = baseMap.subMap(range,end);
               
               return mo; 
           }
           return baseMap;
       }
	   


	
	
	/**
	 * prints the output in console
	 * @param rawInput
	 * @param output
	 */
	private void print(String rawInput,String output){
		System.out.println(rawInput+" : "+output.trim());
	}
	


    /**
     * Search will search for the input from the matching list returned from the dictionary,
     * 
     * It will follow conditions to handle the entire scenario
     * a) it will iterate through each value from the list to matching number and if exact match happens after 
     * cleaning the text, it will simply popup the result.
     * 
     * b) if a is not proved, it will check the difference between prefix and and list entry value and if thats
     * equal to 1, based on requirement , since thats the last item of the input ,w e can simply pull the digit into
     * the queue and print the result.
     * 
     * c) If the input is still larger than the list of values, we got a situation of concatenation of String.
     * So we will resend the search call will next in the pattern keeping the already solved index in the backtracking
     * 
     * d) If prefix equals cleanedvalue, simply print the result but set the flag to true which signals that we still have some
     * values in the list which must be addressed. 
     * 
     * 
     * @param input
     * @param match
     * @param rawInput
     */
    public void search(String input, String match,String rawInput){
    	String local = "";
    	int index = 0;
    	//replace all noise from the data
        String prefix = input.replaceAll("[/-]+", "");
        //if empty value or 1
        if(prefix.isEmpty() || prefix.length() == 1){
        	return;
        }
        
        SortedMap<String, List<String>> dataset = searchPrefix(dictionary, prefix);
        for(Map.Entry<String,List<String>> entry :dataset.entrySet()) {
        	for(int i = 0; i < entry.getValue().size(); i++){
        		//clears the input value with any " as that can be part of dictionary but whie matching we get to ignore
        		String cleanedValue = entry.getValue().get(i).replaceAll("\"", "");
        		String key = entry.getKey();
        		//this ensures that input number matches with the found key or input starts with digits of key, so we can
        		//concatenate other values to the string
        		boolean condition = key.equals(prefix) || prefix.startsWith(key);
        		if(match.replace(" ", "").length() == rawInput.length()){
        			print(rawInput, lost+" " + match);
        		}else if((prefix.length() - cleanedValue.length()) == 1&& condition){
        			print(rawInput, lost+" " + match + entry.getValue().get(i) + " " +prefix.substring(prefix.length()-1, prefix.length()));
        		}else if(cleanedValue.length() < prefix.length() && condition){
        			index = cleanedValue.length();
        			local = entry.getValue().get(i);
        			String lo = prefix.substring(cleanedValue.length(), prefix.length());
        			search(lo,match + entry.getValue().get(i) + " ",rawInput);
        		}else if (prefix.length() == cleanedValue.length()&& condition){
        			print(rawInput, lost+ " " + match + cleanedValue);
        			state = true;
        			
        		}
        	}
        	
        }
        
        if(!state){
        	preprocessSearch(input, match, rawInput, local, index, prefix);
        }
        //reset the lost
        lost = "";
    }
    
    /**
     * It cares mainly 2 situation
     * 
     * a) If and only if at a particular point no word at all from
	 * the dictionary can be inserted, a single digit from the phone number can
     * be copied to the encoding instead.
     * In some cases , if any value left with number and no sequence if loop will check for either match is less
     * than input, then it tracked a variable lost which is tracks the entire previous matching dataset , it will
     * skip the next digit character and triggers the search again.
     * 
     * b) Else it will simply check the previous character is digit or not , if not it will backtrack the search
     * 
     * 
     * @param input
     * @param match
     * @param rawInput
     * @param local
     * @param index
     * @param prefix
     */
	private void preprocessSearch(String input, String match, String rawInput, String local, int index, String prefix) {
		if( match.length()< input.length() ){
        	String m = match.replace(" ", "");
        	if(m.length() == 0){
        		if(index > 0)
        			lost = local;
        		match += prefix.substring(index, index+1) + " ";
        		prefix = prefix.substring(index+1, prefix.length());
        		search(prefix,match,rawInput);
        	}else if (m.substring(m.length()-1).matches("[0-9]+")){
        		return;
        	}
        }else {
        		String testMatch = match.trim();
        		char charAt = testMatch.charAt(testMatch.length()-1);
        		if(Character.isDigit(charAt))
        			return;
        		match += prefix.substring(0,1) + " ";
        		prefix = prefix.substring(1);
        		search(prefix,match,rawInput);
        }
	}
	

}
