package trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trie {

	private final Map<Character, Trie> nodeMap = new HashMap<Character, Trie>();
	private final Map<Integer, Trie> intMap = new HashMap<Integer, Trie>();
	private final List<String> descendentChildrens = new ArrayList<String>();
	private static final Map<String, Integer> charTOIntMap = new HashMap<String, Integer>();

	public Trie() {

		// charTOIntMap.put("\\", 10);
		// charTOIntMap.put("\"", 10);
	}

	public void add(final String word) {
		// checkArgument(StringUtils.isNotBlank(word), "word can not be blank");
		if (word != null && !word.isEmpty()) {
			Trie current = this;
			descendentChildrens.add(word);
			for (final Character c : word.toCharArray()) {
				if (!current.nodeMap.containsKey(c)) {
					current.nodeMap.put(c, new Trie());
				}
				current = current.nodeMap.get(c);
				current.descendentChildrens.add(word);
				// get the Character

				// current.descendentChildrens.charTOIntMap(findPattern(word));
			}
		}
	}

	public void addWithKey(final String word) {
		updateMap();
		// checkArgument(StringUtils.isNotBlank(word), "word can not be blank");
		if (word != null && !word.isEmpty()) {
			Trie current = this;
			descendentChildrens.add(word);
			for (final Character c : word.toCharArray()) {
				if (Character.isLetter(c)) {
					Integer index = charTOIntMap.get(Character.toString(c)
							.toUpperCase());
					if (!current.intMap.containsKey(index)) {
						// current.nodeMap.put(c, new Trie());
						current.intMap.put(index, new Trie());
					}
					current = current.intMap.get(index);
					current.descendentChildrens.add(word);
				}
			}
		}
	}

	public List<String> search(final String wordStart) {
		// checkNotNull(wordStart, "wordStart cannot be null");
		if (wordStart == null)
			return null;
		Trie current = this;
		for (final Character c : wordStart.toCharArray()) {
			current = current.nodeMap.get(c);
			if (current == null) {
				return Collections.emptyList();
			}
		}
		return Collections.unmodifiableList(current.descendentChildrens);
	}

	public List<String> searchWithKey(final String wordStart) {

		// checkNotNull(wordStart, "wordStart cannot be null");
		if (wordStart == null)
			return null;
		Trie current = this;
		for (final Character c : wordStart.toCharArray()) {
			current = current.intMap.get(Character.getNumericValue(c));
			if (current == null) {
				return Collections.emptyList();
			}
		}
		List<String> finalOuput = current.descendentChildrens;
		finalOuput = updateFurther(current.descendentChildrens, wordStart);
		return finalOuput;
	}

	/**
	 * 
	 * @param descendentChildrens2
	 * @return
	 */
	private List<String> updateFurther(List<String> foundChildrens, String word) {
		List<String> value = new LinkedList<>();
		for (String string : foundChildrens) {
			int count = string.length() - string.replace("\"", "").length();
			if ((string.length() == word.length())
					|| (string.length() == (word.length() + count))) {
				value.add(string);
			}
		}
		return value;
	}

	private List<List<String>> _findEncodings(String number, int startAt) {
		Trie current = this;
		LinkedList<List<String>> result = new LinkedList<>();
		if (startAt == number.length()) {
			result.add(new LinkedList<String>());
			return result;
		}
		for (int endAt = startAt + 1; endAt <= number.length(); endAt++) {
			current = current.intMap.get(number.substring(startAt, endAt));
			List<String> words = current.descendentChildrens;
			System.out.println("wordswords " + words);
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

	List<List<String>> findEncodings(String number) {
		return _findEncodings(number, 0);
	}

	private static void updateMap() {
		// TODO:achoudhary Auto-generated method stub
		charTOIntMap.put("E", 0);

		charTOIntMap.put("J", 1);
		charTOIntMap.put("N", 1);
		charTOIntMap.put("Q", 1);

		charTOIntMap.put("R", 2);
		charTOIntMap.put("W", 2);
		charTOIntMap.put("X", 2);

		charTOIntMap.put("D", 3);
		charTOIntMap.put("S", 3);
		charTOIntMap.put("Y", 3);

		charTOIntMap.put("F", 4);
		charTOIntMap.put("T", 4);

		charTOIntMap.put("A", 5);
		charTOIntMap.put("M", 5);

		charTOIntMap.put("C", 6);
		charTOIntMap.put("I", 6);
		charTOIntMap.put("V", 6);

		charTOIntMap.put("B", 7);
		charTOIntMap.put("K", 7);
		charTOIntMap.put("U", 7);

		charTOIntMap.put("L", 8);
		charTOIntMap.put("O", 8);
		charTOIntMap.put("P", 8);

		charTOIntMap.put("G", 9);
		charTOIntMap.put("H", 9);
		charTOIntMap.put("Z", 9);
	}

	private String findPattern(String word) {
		String output = "";
		for (char ch : word.toCharArray()) {
			if (Character.isLetter(ch)) {
				output += charTOIntMap
						.get(Character.toString(ch).toUpperCase());
			}
		}
		return output;
	}

	/**
	 * Search the list of matching pattern in Trie. Modified the exact search
	 * logic here .
	 * 
	 * Changes made for handling UpperCase
	 * 
	 * @param wordStart
	 * @return
	 */
	public Set<String> specificSearch(final String wordStart) {

		return null;
	}

	// public static void main(String[] args) {
	// Trie trie = new Trie();
	// try (BufferedReader br = new BufferedReader(
	// new FileReader(
	// "D:\\Downloads\\numberencoding\\numberencoding\\dictionary.txt"))) {
	//
	// String sCurrentLine;
	//
	// while ((sCurrentLine = br.readLine()) != null) {
	// // System.out.println(sCurrentLine);
	// trie.add(sCurrentLine.trim());
	// }
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// Set<String> search = trie.search("Abbaumater");
	// for (String string : search) {
	// System.err.println(string);
	// }
	//
	// }
}