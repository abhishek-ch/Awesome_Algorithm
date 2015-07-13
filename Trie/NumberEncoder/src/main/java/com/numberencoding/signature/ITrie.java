package com.numberencoding.signature;

/**
 * This defines the basic framework of Trie
 * @author abc
 *
 */
public interface ITrie {
	
	public boolean buildTrie(String fileName);
	
	public void search(String input, String match,String rawInput);
}
