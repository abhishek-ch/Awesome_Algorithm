package com.numberencoding;

import java.util.HashMap;
import java.util.Map;

public class CharacterMapper {

	public Map<Character, Integer> buildCharacterMapper(){
		Map<Character, Integer> charTOIntMap = new HashMap<Character, Integer>();
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
		return charTOIntMap;
	}
	
}
