package com.numberencoding;

import java.util.Scanner;

public class MainFunction {



	public static void main(String[] args) {
		
		Scanner scanIn = new Scanner(System.in);
		CustomizeTrie trie = new CustomizeTrie();
		boolean buildTrie = false;
		while(!buildTrie){
			System.out.println("Enter the path of dictionary file : ");
			String dictionaryPath = scanIn.nextLine();
			buildTrie = trie.buildTrie(dictionaryPath);
		}
		
		String input = "";
		while(!input.equals("0")){
			System.out.println("Enter the path of Input file to process or 0 to exit : ");
			input = scanIn.nextLine();
			trie.encodeNumber(input);
		}
		scanIn.close();
		System.exit(0);

	}


}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 10 Jul 2015
 */