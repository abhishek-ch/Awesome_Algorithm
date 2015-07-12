package com.numberencoding;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CharacterMapperTest {

	
	CharacterMapper mapper;
	@Before
	public void setUp() throws Exception {
		mapper = new CharacterMapper();
	}

	@Test
	public void test() {
		Map<Character, Integer> buildCharacterMapper = mapper.buildCharacterMapper();
		assertNotNull(buildCharacterMapper);
		assertTrue(buildCharacterMapper.get('F')==4);
		assertTrue(buildCharacterMapper.get('T')==4);
		
		assertTrue(buildCharacterMapper.get('A')==5);
		assertTrue(buildCharacterMapper.get('M')==5);
		assertTrue(buildCharacterMapper.get('B')!=4);
		
		assertTrue(buildCharacterMapper.get('G')==9);
		assertTrue(buildCharacterMapper.get('H')==9);
		assertTrue(buildCharacterMapper.get('Z')==9);
		
	}

}
