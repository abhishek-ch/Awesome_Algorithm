package com.numberencoding.main;

import java.util.Scanner;

import com.numberencoding.CustomizeTrie;
import com.numberencoding.signature.ITrie;

/**
 * Main function which takes input as dictionary file and input file
 * 
 * @author abc
 *
 */
public class MainFunction {

	public static void main(String[] args) {

		Scanner scanIn = new Scanner(System.in);
		ITrie trie = new CustomizeTrie();
		boolean buildTrie = false;
		String input = "";
		while (!input.equals("0")) {
			while (!buildTrie) {
				System.out.println("Enter the path of dictionary file or 0 to exit: ");
				input = scanIn.nextLine();
				if(input.equals("0"))
					System.exit(0);
				buildTrie = trie.buildTrie(input);
			}

			System.out.println("Enter the path of Input file to process or 0 to exit : ");
			input = scanIn.nextLine();
			((CustomizeTrie) trie).encodeNumber(input);
		}
		scanIn.close();
		System.exit(0);

	}

}
