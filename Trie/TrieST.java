

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TrieST<Value> {
	private static final int R = 256; // extended ASCII

	private Node root; // root of trie
	private int N; // number of keys in trie

	// R-way trie node
	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}

	/**
	 * Initializes an empty string symbol table.
	 */
	public TrieST() {
	}

	/**
	 * Returns the value associated with the given key.
	 * 
	 * @param key
	 *            the key
	 * @return the value associated with the given key if the key is in the
	 *         symbol table and <tt>null</tt> if the key is not in the symbol
	 *         table
	 * @throws NullPointerException
	 *             if <tt>key</tt> is <tt>null</tt>
	 */
	public Value get(String key) {
		Node x = get(root, key, 0);
		if (x == null)
			return null;
		return (Value) x.val;
	}

	/**
	 * Does this symbol table contain the given key?
	 * 
	 * @param key
	 *            the key
	 * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
	 *         <tt>false</tt> otherwise
	 * @throws NullPointerException
	 *             if <tt>key</tt> is <tt>null</tt>
	 */
	public boolean contains(String key) {
		return get(key) != null;
	}

	private Node get(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length())
			return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d + 1);
	}

	/**
	 * Inserts the key-value pair into the symbol table, overwriting the old
	 * value with the new value if the key is already in the symbol table. If
	 * the value is <tt>null</tt>, this effectively deletes the key from the
	 * symbol table.
	 * 
	 * @param key
	 *            the key
	 * @param val
	 *            the value
	 * @throws NullPointerException
	 *             if <tt>key</tt> is <tt>null</tt>
	 */
	public void put(String key, Value val) {
		if (val == null)
			delete(key);
		else
			root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int d) {
		if (x == null)
			x = new Node();
		if (d == key.length()) {
			if (x.val == null)
				N++;
			x.val = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * 
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return N;
	}

	/**
	 * Is this symbol table empty?
	 * 
	 * @return <tt>true</tt> if this symbol table is empty and <tt>false</tt>
	 *         otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns all keys in the symbol table as an <tt>Iterable</tt>. To iterate
	 * over all of the keys in the symbol table named <tt>st</tt>, use the
	 * foreach notation: <tt>for (Key key : st.keys())</tt>.
	 * 
	 * @return all keys in the sybol table as an <tt>Iterable</tt>
	 */
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}

	/**
	 * Returns all of the keys in the set that start with <tt>prefix</tt>.
	 * 
	 * @param prefix
	 *            the prefix
	 * @return all of the keys in the set that start with <tt>prefix</tt>, as an
	 *         iterable
	 */
	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> results = new LinkedList<String>();
		Node x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), results);
		return results;
	}

	private void collect(Node x, StringBuilder prefix, Queue<String> results) {
		if (x == null)
			return;
		if (x.val != null)
			results.add(prefix.toString());
		for (char c = 0; c < R; c++) {
			prefix.append(c);
			collect(x.next[c], prefix, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	/**
	 * Returns all of the keys in the symbol table that match <tt>pattern</tt>,
	 * where . symbol is treated as a wildcard character.
	 * 
	 * @param pattern
	 *            the pattern
	 * @return all of the keys in the symbol table that match <tt>pattern</tt>,
	 *         as an iterable, where . is treated as a wildcard character.
	 */
	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> results = new LinkedList<String>();
		collect(root, new StringBuilder(), pattern, results);
		return results;
	}

	private void collect(Node x, StringBuilder prefix, String pattern,
			Queue<String> results) {
		if (x == null)
			return;
		int d = prefix.length();
		if (d == pattern.length() && x.val != null)
			results.add(prefix.toString());
		if (d == pattern.length())
			return;
		char c = pattern.charAt(d);
		if (c == '.') {
			for (char ch = 0; ch < R; ch++) {
				prefix.append(ch);
				collect(x.next[ch], prefix, pattern, results);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		} else {
			prefix.append(c);
			collect(x.next[c], prefix, pattern, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	/**
	 * Returns the string in the symbol table that is the longest prefix of
	 * <tt>query</tt>, or <tt>null</tt>, if no such string.
	 * 
	 * @param query
	 *            the query string
	 * @throws NullPointerException
	 *             if <tt>query</tt> is <tt>null</tt>
	 * @return the string in the symbol table that is the longest prefix of
	 *         <tt>query</tt>, or <tt>null</tt> if no such string
	 */
	public String longestPrefixOf(String query) {
		int length = longestPrefixOf(root, query, 0, -1);
		if (length == -1)
			return null;
		else
			return query.substring(0, length);
	}

	// returns the length of the longest string key in the subtrie
	// rooted at x that is a prefix of the query string,
	// assuming the first d character match and we have already
	// found a prefix match of given length (-1 if no such match)
	private int longestPrefixOf(Node x, String query, int d, int length) {
		if (x == null)
			return length;
		if (x.val != null)
			length = d;
		if (d == query.length())
			return length;
		char c = query.charAt(d);
		return longestPrefixOf(x.next[c], query, d + 1, length);
	}

	/**
	 * Removes the key from the set if the key is present.
	 * 
	 * @param key
	 *            the key
	 * @throws NullPointerException
	 *             if <tt>key</tt> is <tt>null</tt>
	 */
	public void delete(String key) {
		root = delete(root, key, 0);
	}

	private Node delete(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length()) {
			if (x.val != null)
				N--;
			x.val = null;
		} else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d + 1);
		}

		// remove subtrie rooted at x if it is completely empty
		if (x.val != null)
			return x;
		for (int c = 0; c < R; c++)
			if (x.next[c] != null)
				return x;
		return null;
	}

	/**
	 * Unit tests the <tt>TrieST</tt> data type.
	 */
	public static void main(String[] args) {
		// build symbol table from standard input
		TrieST<Integer> st = new TrieST<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(
				"D:\\Downloads\\numberencoding\\dictionary.txt"))) {

			String sCurrentLine;
			int index = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				String key = sCurrentLine.trim();
				st.put(key, index);
				index++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// print results
		if (st.size() < 100) {
			System.out.println("keys(\"\"):");
			for (String key : st.keys()) {
				System.out.println(key + " " + st.get(key));
			}
			System.out.println();
		}

		System.out.println("longestPrefixOf(\"shellsort\"):");
		System.out.println(st.longestPrefixOf("shellsort"));
		System.out.println();

		System.out.println("longestPrefixOf(\"quicksort\"):");
		System.out.println(st.longestPrefixOf("quicksort"));
		System.out.println();

		System.out.println("keysWithPrefix(\"shor\"):");
		for (String s : st.keysWithPrefix("shor"))
			System.out.println(s);
		System.out.println();

		System.out.println("keysThatMatch(\".he.l.\"):");
		for (String s : st.keysThatMatch(".he.l."))
			System.out.println(s);
	}
}