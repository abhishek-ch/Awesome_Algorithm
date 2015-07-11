

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Trie1 {
	private TrieNode root;

	public Trie1() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		HashMap<Character, TrieNode> children = root.children;

		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);

			TrieNode t;
			if (children.containsKey(c)) {
				t = children.get(c);
			} else {
				t = new TrieNode(c);
				children.put(c, t);
			}

			children = t.children;

			// set leaf node
			if (i == word.length() - 1)
				t.isLeaf = true;
		}
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode t = searchNode(word);

		if (t != null && t.isLeaf)
			return true;
		else
			return false;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		if (searchNode(prefix) == null)
			return false;
		else
			return true;
	}

	public TrieNode searchNode(String str) {
		Map<Character, TrieNode> children = root.children;
		TrieNode t = null;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (children.containsKey(c)) {
				t = children.get(c);
				children = t.children;
			} else {
				return null;
			}
		}

		return t;
	}

	public static void main(String[] args) {
		Trie1 trie = new Trie1();
		try (BufferedReader br = new BufferedReader(
				new FileReader(
						"D:\\Downloads\\numberencoding\\numberencoding\\dictionary.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				trie.insert(sCurrentLine.trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		TrieNode searchNode = trie.searchNode("Abba");
		HashMap<Character, TrieNode> children = searchNode.children;
		System.out.println("childrenchildren " + children.size());
		for (Entry<Character, TrieNode> entry : children.entrySet()) {
			TrieNode value = entry.getValue();
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}
}