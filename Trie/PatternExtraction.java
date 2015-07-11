

//http://stackoverflow.com/questions/29053153/encoding-numbers-to-words
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PatternExtraction {

	static Map<String, List<String>> bucketedWords = new HashMap<>();
	static Trie trie = new Trie();
	private final Map<String, Integer> charTOIntMap = new HashMap<String, Integer>();

	public PatternExtraction() {
		charTOIntMap.put("E", 0);

		charTOIntMap.put("J", 0);
		charTOIntMap.put("N", 0);
		charTOIntMap.put("Q", 0);

		charTOIntMap.put("R", 0);
		charTOIntMap.put("W", 0);
		charTOIntMap.put("X", 0);

		charTOIntMap.put("D", 0);
		charTOIntMap.put("S", 0);
		charTOIntMap.put("Y", 0);

		charTOIntMap.put("F", 0);
		charTOIntMap.put("T", 0);

		charTOIntMap.put("A", 0);
		charTOIntMap.put("M", 0);

		charTOIntMap.put("C", 0);
		charTOIntMap.put("I", 0);
		charTOIntMap.put("V", 0);

		charTOIntMap.put("B", 0);
		charTOIntMap.put("K", 0);
		charTOIntMap.put("U", 0);

		charTOIntMap.put("L", 0);
		charTOIntMap.put("O", 0);
		charTOIntMap.put("P", 0);

		charTOIntMap.put("G", 0);
		charTOIntMap.put("H", 0);
		charTOIntMap.put("Z", 0);

	}

	// private List<String> listOfStringsFromTel(String telephone){
	//
	// }

	private static List<List<String>> _findEncodings(String number, int startAt) {
		LinkedList<List<String>> result = new LinkedList<>();
		if (startAt == number.length()) {
			result.add(new LinkedList<String>());
			return result;
		}
		for (int endAt = startAt + 1; endAt <= number.length(); endAt++) {
			List<String> words = bucketedWords.get(number.substring(startAt,
					endAt));
			if (words != null) {
				List<List<String>> encodings = _findEncodings(number, endAt);
				for (String word : words) {
					for (List<String> encoding : encodings) {
						List<String> enc = new LinkedList<>(encoding);
						enc.add(0, word);
						result.add(enc);
					}
				}
			}
		}
		return result;
	}

	private static List<List<String>> findEncodings(String number) {
		return _findEncodings(number, 0);
	}

	private static List<List<String>> modernEncodings(String number, int startAt) {
		LinkedList<List<String>> result = new LinkedList<>();
		
		if (startAt == number.length()) {
			result.add(new LinkedList<String>());
			return result;
		}

		for (int endAt = startAt + 1; endAt <= number.length(); endAt++) {
			List<String> words = trie.search(number.substring(startAt, endAt));
			// List<String> words = bucketedWords.get(number.substring(startAt,
			// endAt));
			if (words != null) {
				List<List<String>> encodings = _findEncodings(number, endAt);
				for (String word : words) {
					for (List<String> encoding : encodings) {
						List<String> enc = new LinkedList<>(encoding);
						enc.add(0, word);
						result.add(enc);
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		CustomizeTrie trie = new CustomizeTrie();
		try (BufferedReader br = new BufferedReader(new FileReader(
				"dictionary.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				trie.add(sCurrentLine.trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Map<String, List<String>> map = trie.dictionary;
		// for (Map.Entry<String, List<String>> entry : map.entrySet()) {
		// System.out.println("Key = " + entry.getKey() + ", Value = "
		// + entry.getValue());
		// }

		// Astlo
		List<List<String>> search = trie.search("381482");
		System.out.println(search);
		// for (String string : search) {
		// System.err.println(string);
		// }

		// callothermain();

	}

	public static void callothermain() {
		bucketedWords.put("562", Arrays.asList("mir", "Mix", "Ail", "tpi"));
		bucketedWords.put("482", Arrays.asList("tor"));
		bucketedWords.put("10", Arrays.asList("je"));
		bucketedWords.put("78", Arrays.asList("Bo\""));
		bucketedWords.put("35", Arrays.asList("da"));

		System.out.println(findEncodings("562482"));
		System.out.println(findEncodings("107835"));
	}

}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 10 Jul 2015
 */