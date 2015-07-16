package com.cube.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cube.helper.Util;

public class TestHelper {
	List<int[][]> listOfMatrix = new ArrayList<>();
	Map<String,List<int[][]>> cubeBlocksMap = new HashMap<>();
	public TestHelper() {
		createPurpleCube("Purple");
		createBlueCube("Blue");
		createRedCube("Red");
		createGreenCube("Green");
	}
	
	private void createPurpleCube(String color){
	       String[] tPiece0 = { "oo o ", "oooo ", "oooo ", " oooo", "  o  " };
	       String[] tPiece1 = { "   oo", "oooo ", "ooooo", " ooo ", " o o " };
	       String[] tPiece2 = { " o   ", "oooo ", " oooo", "oooo ", "  o  " };
	       String[] tPiece3 = { "oo oo", " oooo", "oooo ", " ooo ", " o o " };
	       String[] tPiece4 = { "  o o", " oooo", "ooooo", "oooo ", "o oo " };
	       String[] tPiece5 = { " o oo", " ooo ", " oooo", "oooo ", "oo o " };
	       
	       commonwork(color,tPiece0,tPiece1,tPiece2,tPiece3,tPiece4,tPiece5);

	}
	
	private void createRedCube(String color){
	       // RED Couldn't find any permutation to unfold red node to 6
	       String[] tPiece0 = { "   oo", " ooo ", "ooooo", " ooo ", " o oo" };
	       String[] tPiece1 = { " o o ", "oooo ", " oooo", "oooo ", " o   " };
	       String[] tPiece2 = { " oo o", "ooooo", " ooo ", "ooooo", "o  oo" };
	       String[] tPiece3 = { "  o  ", "oooo ", " oooo", "oooo ", "  o  " };
	       String[] tPiece4 = { "  oo ", "ooooo", " ooo ", "ooooo", "o o  " };
	       String[] tPiece5 = { " oo  ", " ooo ", "ooooo", " ooo ", "oo oo" };
	       commonwork(color,tPiece0,tPiece1,tPiece2,tPiece3,tPiece4,tPiece5);

	}
	private void createBlueCube(String color){
	       // BLUE
	      String[] tPiece0 = { "  o  ", " ooo ", "ooooo", " ooo ", "  o  " };
	      String[] tPiece1 = { "  o o", "ooooo", " ooo ", "ooooo", " o oo" };
	      String[] tPiece2 = { " o o ", " ooo ", "ooooo", " ooo ", "  o  " };
	      String[] tPiece3 = { "o o  ", "ooooo", " ooo ", "ooooo", " o o " };
	      String[] tPiece4 = { "o o o", "ooooo", " ooo ", "ooooo", "o o o" };
	      String[] tPiece5 = { " o o ", "oooo ", " oooo", "oooo ", "oo o " };
	       
	       commonwork(color,tPiece0,tPiece1,tPiece2,tPiece3,tPiece4,tPiece5);

	}
	
	private void createGreenCube(String color){
	       // Green
	      String[] tPiece0 = { "  o  ", "oooo ", " oooo", "oooo ", " o o " };
	      String[] tPiece1 = { "  o o", "ooooo", " ooo ", "oooo ", " o o " };
	      String[] tPiece2 = { "  o o", " oooo", "oooo ", "ooooo", "o o  " };
	      String[] tPiece3 = { "  o  ", " oooo", "oooo ", " oooo", "oo o " };
	      String[] tPiece4 = { "o o o", "ooooo", " ooo ", "ooooo", "o o  " };
	      String[] tPiece5 = { " o o ", " ooo ", "ooooo", " ooo ", " o oo" };
	       
	       commonwork(color,tPiece0,tPiece1,tPiece2,tPiece3,tPiece4,tPiece5);

	}
	
	private void commonwork(String cubeColor,String[] tPiece0,String[] tPiece1,String[] tPiece2,String[] tPiece3,String[] tPiece4,String[] tPiece5){
	       int[][] convertToArray1 = Util.convertToArray(tPiece0);
	       int[][] convertToArray2 = Util.convertToArray(tPiece1);
	       int[][] convertToArray3 = Util.convertToArray(tPiece2);
	       int[][] convertToArray4 = Util.convertToArray(tPiece3);
	       int[][] convertToArray5 = Util.convertToArray(tPiece5);
	       int[][] convertToArray6 = Util.convertToArray(tPiece4);
	       
	       List<int[][]> listOfMatrix = new ArrayList<>();

	       listOfMatrix.add(convertToArray1);
	       listOfMatrix.add(convertToArray2);
	       listOfMatrix.add(convertToArray3);
	       listOfMatrix.add(convertToArray4);
	       listOfMatrix.add(convertToArray5);
	       listOfMatrix.add(convertToArray6);
	       
	       cubeBlocksMap.put(cubeColor, listOfMatrix);
	}
	
	public Map<String,List<int[][]>> getCubeBlocksinMatrix(){
		return cubeBlocksMap;
	}
	
}

