package com.numberencoding;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.junit.Before;
import org.junit.Test;

public class CustomizeTrieTest {

	CustomizeTrie customizeTrie;
	@Before
	public void setUp() throws Exception {
		customizeTrie = new CustomizeTrie();
	}

	@Test
	public void testBuildTrie() {
		boolean buildTrie = customizeTrie.buildTrie("dictionary.txt");
		assertTrue(buildTrie);
		SortedMap<String, List<String>> dataStructure = customizeTrie.getDataStructure();
		for (Map.Entry<String, List<String>> entry : dataStructure.entrySet()) {
		    System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		assertArrayEquals(dataStructure.get("10").toArray(new String[]{}), new String[]{"je"});
		assertArrayEquals(dataStructure.get("253302").toArray(new String[]{}), new String[]{"Wasser"});
		assertArrayEquals(dataStructure.get("4824").toArray(new String[]{}), new String[]{"fort","Torf"});
		assertArrayEquals(dataStructure.get("562").toArray(new String[]{}), new String[]{"mir","Mix"});
		assertArrayEquals(dataStructure.get("783").toArray(new String[]{}), new String[]{"bo\"s"});
	}



	@Test
	public void testSearch() {
		fail("Can Not Implement as Console will be printed for this"); // TODO
		
	}
	
	

}
