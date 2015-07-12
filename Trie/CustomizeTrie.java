

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
	   



	
	private List<List<String>> search(String number, int startAt) {
		SortedMap<String, List<String>> filterPrefix = filterPrefix(dictionary,number);
		   for (Map.Entry<String,List<String>> entry : filterPrefix.entrySet()) {
               System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
           }
		LinkedList<List<String>> result = new LinkedList<>();
		if (startAt == number.length()) {
			result.add(new LinkedList<String>());
			return result;
		}
		for (int endAt = startAt + 1; endAt <= number.length(); endAt++) {
			List<String> words = dictionary.get(number
					.substring(startAt, endAt));
			//System.out.println(words);
			System.out.println("endAt "+endAt);
			if (words != null) {
				List<List<String>> encodings = search(number, endAt);
				//System.out.println("End At "+endAt+" ---> "+startAt);
				for (String word : words) {
					//System.out.println("inside "+word +" - "+encodings+" endAt "+endAt+" startAt "+startAt);
					for (List<String> encoding : encodings) {
						//System.err.print(" "+word);
						List<String> enc = new LinkedList<>(encoding);
						enc.add(0, word);
						result.add(enc);
					}
				}
			}
		}
		return result;
	}



	public List<List<String>> search(String word) {
		List<List<String>> output1 = search(word.substring(0,word.length()), 0);
		//System.out.println(output1.size());
		if(output1.size() > 1){
			List<List<String>> output2 = search(word, 0);
			output1.addAll(output2);
		}else{
			output1 = search(word.substring(1,word.length()-1), 0);
			if(output1 != null){
				List<List<String>> output2 = search(word.substring(1), 0);
				output1.addAll(output2);
			}
		}
		
		return output1;
	}

}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 10 Jul 2015
 */