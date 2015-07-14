package cube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeriveMatrix {

	private int[][] convertToArray(String[] cube) {

		int[][] matrix = new int[5][5];
		int rowIndex = 0;

		for (String row : cube) {
			char[] charArray = row.toCharArray();
			int columnIndex = 0;
			for (char c : charArray) {
				if (c == ' ') {
					matrix[rowIndex][columnIndex] = 0;
				} else {
					matrix[rowIndex][columnIndex] = 1;
				}
				columnIndex++;
			}
			rowIndex++;
		}
		// printGrid(matrix);
		return matrix;
	}

	public void printGrid(int[][] a) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.printf("%2d", a[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------");
		// System.out.println(Arrays.deepToString(a));
	}

	public void printSmart(List<int[][]> listMatrix) {
		for (int[][] is : listMatrix) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.printf("%2d", is[i][j]);
				}
				System.out.println();
			}
			System.out.println("\n\n");
		}
	}

	// *** THIS IS THE METHOD YOU CARE ABOUT ***
	public int[][] flipInPlace(int[][] theArray) {
		for (int i = 0; i < (theArray.length / 2); i++) {
			int[] temp = theArray[i];
			theArray[i] = theArray[theArray.length - i - 1];
			theArray[theArray.length - i - 1] = temp;
		}
		// printGrid(theArray);
		return theArray;
	}

	public int[][] flipLeftToRight(int[][] pixels) {

		for (int i = 0; i < pixels.length; i++) {
			for (int curr = 0; curr < (pixels[0].length + 1) / 2; curr++) {

				int saved = pixels[i][curr];
				pixels[i][curr] = pixels[i][pixels[0].length - 1 - curr];
				pixels[i][pixels[0].length - 1 - curr] = saved;
			}
		}
		// printGrid(pixels);
		return pixels;
	}

	public int[][] mirror(int width, int height, int[][] in) {
		int[][] out = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				out[i][width - j - 1] = in[i][j];
			}
		}
		// printGrid(out);
		return out;
	}

	public int[][] rotateClockWise(int[][] pixels) {

		int[][] rotate = new int[pixels[0].length][pixels.length];

		for (int i = 0; i < pixels[0].length; i++) {
			for (int j = 0; j < pixels.length; j++) {
				rotate[i][pixels.length - 1 - j] = pixels[j][i];
			}
		}
		// printGrid(rotate);
		return rotate;
	}

	public int[][] rotateAntiClockwise(int[][] pixels) {

		int[][] newarray = new int[pixels[0].length][pixels.length];

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				newarray[pixels[0].length - 1 - j][i] = pixels[i][j];
			}
		}
		// printGrid(newarray);

		return newarray;
	}

	public List<int[][]> findAllPossibleRightRotations(int[][] matrix) {
		List<int[][]> allPossibleRotations = new ArrayList<>();
		// allPossibleRotations.add(matrix);
		for (int i = 0; i < 3; i++) {
			matrix = rotateClockWise(matrix);
			allPossibleRotations.add(matrix);
		}
		// printSmart(allPossibleRotations);
		return allPossibleRotations;
	}

	public List<int[][]> findAllPossibleLefttRotations(int[][] matrix) {
		List<int[][]> allPossibleRotations = new ArrayList<>();
		// allPossibleRotations.add(matrix);
		for (int i = 0; i < 3; i++) {
			matrix = rotateAntiClockwise(matrix);
			allPossibleRotations.add(matrix);
		}
		// printSmart(allPossibleRotations);
		return allPossibleRotations;
	}

	public List<int[][]> findAllOrientations(int[][] matrix) {
		List<int[][]> allPossibleRotations = new ArrayList<>();
		allPossibleRotations.add(matrix);
		allPossibleRotations.add(flipInPlace(matrix));
		allPossibleRotations
				.add(mirror(matrix[0].length, matrix.length, matrix));

		allPossibleRotations
				.addAll(findAllPossibleRightRotations(allPossibleRotations
						.get(0)));

		allPossibleRotations
				.addAll(findAllPossibleRightRotations(allPossibleRotations
						.get(2)));

		// allPossibleRotations
		// .addAll(findAllPossibleRightRotations(allPossibleRotations
		// .get(1)));
		// allPossibleRotations.addAll(findAllPossibleRightRotations(matrix));
		// System.out.println(allPossibleRotations.size()); // 4

		// allPossibleRotations
		// .add(mirror(matrix[0].length, matrix.length, matrix));
		// System.out.println(allPossibleRotations.size()); // 5

		// int[][] flipInPlace = flipInPlace(matrix);
		// allPossibleRotations.add(flipInPlace);
		// System.out.println(allPossibleRotations.size()); // 6

		// allPossibleRotations.addAll(findAllPossibleRightRotations(flipInPlace));
		// System.out.println(allPossibleRotations.size());
		printSmart(allPossibleRotations);
		return allPossibleRotations;
	}

	Map<int[][], List<int[][]>> tracker = new HashMap<>();

	public Map<int[][], List<int[][]>> trigger(List<int[][]> pieces_left) {
		for (int[][] piece : pieces_left) {
			List<int[][]> findAllOrientations = findAllOrientations(piece);
			tracker.put(piece, findAllOrientations);
		}

		return tracker;

	}
	public List<Cube> buildBlankCubeStructure(int count){
		//build 3- based Pattern Cube
		List<Cube> cubeList = new ArrayList<>();
		for(int i =0;i<count;i++){
			Cube cube = new Cube();
			cubeList.add(cube);
		}
		
		return cubeList;
		
	}

	public void backtrack(int slotid, List<int[][]> pieces_left) {
		if (slotid == 3) {
			System.out.println("DONE DONE");
		}

		for (int[][] piece : pieces_left) {
			List<int[][]> list = tracker.get(piece);
			for (int[][] orientationPiece : list) {

			}
		}

	}

	public void buildCubeSymettry() {

	}

	
	public void start(){
		List<Cube> buildCube = controller.buildCube(3);
	}
	
	CubeController controller;
	public DeriveMatrix() {
		// TODO Auto-generated constructor stub
		controller = new CubeController();
	}
	
	public static void main(String[] args) {
		DeriveMatrix deriveMatrix = new DeriveMatrix();
		String[] tPiece0 = { "  o  ", " ooo ", "ooooo", " ooo ", "  o  " };
		String[] tPiece1 = { "  o o", "ooooo", " ooo ", "ooooo", " o oo" };
		String[] tPiece2 = { " o o ", " ooo ", "ooooo", " ooo ", "  o  " };
		String[] tPiece3 = { "o o  ", "ooooo", " ooo ", "ooooo", " o o " };
		String[] tPiece4 = { "o o o", "ooooo", " ooo ", "ooooo", "o o o" };
		String[] tPiece5 = { " o o ", "oooo ", " oooo", "oooo ", "oo o " };

		int[][] convertToArray = deriveMatrix.convertToArray(tPiece1);
		deriveMatrix.findAllOrientations(convertToArray);
		
		// int[][] flipInPlace = deriveMatrix.flipInPlace(convertToArray);
		// deriveMatrix.findAllPossibleRightRotations(flipInPlace);
		// convertToArray = deriveMatrix.mirror(convertToArray[0].length,
		// convertToArray.length, convertToArray);
		// int[][] convertToArray2 = deriveMatrix.convertToArray(tPiece1);

		// System.err.println(Arrays.deepEquals(convertToArray,
		// convertToArray2));
		// deriveMatrix.findAllPossibleRightRotations(convertToArray);
		// deriveMatrix.findAllPossibleRightRotations(convertToArray);
		// convertToArray = deriveMatrix.convertToArray(tPiece2);
		// convertToArray = deriveMatrix.convertToArray(tPiece3);
		// deriveMatrix.rotateClockWise(convertToArray);
		// deriveMatrix.rotateAntiClockwise(convertToArray);
		// int[][] flipLeftToRight =
		// deriveMatrix.flipLeftToRight(convertToArray);
		// deriveMatrix.rotateClockWise(flipLeftToRight);
		// deriveMatrix.rotateAntiClockwise(flipLeftToRight);

		// deriveMatrix.convertToArray(tPiece1);
		// deriveMatrix.convertToArray(tPiece2);
		// deriveMatrix.convertToArray(tPiece3);
		// deriveMatrix.convertToArray(tPiece4);
		// deriveMatrix.convertToArray(tPiece5);

	}

}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 14 Jul 2015
 */