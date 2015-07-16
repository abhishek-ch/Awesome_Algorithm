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
		return matrix;
	}

	public int[][] flipInPlace(int[][] theArray) {
		for (int i = 0; i < (theArray.length / 2); i++) {
			int[] temp = theArray[i];
			theArray[i] = theArray[theArray.length - i - 1];
			theArray[theArray.length - i - 1] = temp;
		}
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
		return pixels;
	}

	public int[][] mirror(int width, int height, int[][] in) {
		int[][] out = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				out[i][width - j - 1] = in[i][j];
			}
		}
		return out;
	}

	public int[][] rotateClockWise(int[][] pixels) {

		int[][] rotate = new int[pixels[0].length][pixels.length];

		for (int i = 0; i < pixels[0].length; i++) {
			for (int j = 0; j < pixels.length; j++) {
				rotate[i][pixels.length - 1 - j] = pixels[j][i];
			}
		}
		return rotate;
	}

	public int[][] rotateAntiClockwise(int[][] pixels) {

		int[][] newarray = new int[pixels[0].length][pixels.length];

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				newarray[pixels[0].length - 1 - j][i] = pixels[i][j];
			}
		}

		return newarray;
	}

	public List<int[][]> findAllPossibleRightRotations(int[][] matrix) {
		List<int[][]> allPossibleRotations = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			matrix = rotateClockWise(matrix);
			allPossibleRotations.add(matrix);
		}
		return allPossibleRotations;
	}

	public List<int[][]> findAllPossibleLefttRotations(int[][] matrix) {
		List<int[][]> allPossibleRotations = new ArrayList<>();
		// allPossibleRotations.add(matrix);
		for (int i = 0; i < 3; i++) {
			matrix = rotateAntiClockwise(matrix);
			allPossibleRotations.add(matrix);
		}
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

	public List<Cube> backtrack(int slotid, List<int[][]> pieces_left) {
		if (slotid == 6) {
			List<Cube> list = controller.getList();
			new OutputStructure().betterPrint(controller.getList());
			// System.exit(0);
			controller = new CubeController();
			return list;
		}

		for (int[][] piece : pieces_left) {
			List<int[][]> list = tracker.get(piece);
			for (int[][] orientationPiece : list) {
				// List<Cube> buildCubeBlock = controller
				// .buildCubeInfrastructure(orientationPiece);

				List<Cube> buildCubeBlock = controller.buildUnfoldedCube(
						slotid, orientationPiece);

				if (buildCubeBlock.size() == slotid + 1) {
					List<int[][]> subList = new ArrayList<int[][]>(pieces_left);
					subList.remove(piece);
					backtrack(slotid + 1, subList);
				}
			}
		}
		return null;

	}

	CubeController controller;

	public DeriveMatrix() {
		// TODO Auto-generated constructor stub
		controller = new CubeController();

		// BLUE
		String[] tPiece0 = { "  o  ", " ooo ", "ooooo", " ooo ", "  o  " };
		String[] tPiece1 = { "  o o", "ooooo", " ooo ", "ooooo", " o oo" };
		String[] tPiece2 = { " o o ", " ooo ", "ooooo", " ooo ", "  o  " };
		String[] tPiece3 = { "o o  ", "ooooo", " ooo ", "ooooo", " o o " };
		String[] tPiece4 = { "o o o", "ooooo", " ooo ", "ooooo", "o o o" };
		String[] tPiece5 = { " o o ", "oooo ", " oooo", "oooo ", "oo o " };

		// RED Couldn't find any permutation to unfold red node to 6
		// String[] tPiece0 = { " oo", " ooo ", "ooooo", " ooo ", " o oo" };
		// String[] tPiece1 = { " o o", "oooo ", " oooo", "oooo ", " o " };
		// String[] tPiece2 = { " oo o", "ooooo", " ooo ", "ooooo", "o oo" };
		// String[] tPiece3 = { " o ", "oooo ", " oooo", "oooo ", " o " };
		// String[] tPiece4 = { " oo ", "ooooo", " ooo ", "ooooo", "o o " };
		// String[] tPiece5 = { " oo ", " ooo ", "ooooo", " ooo ", "oo oo" };

		// Purple
		// String[] tPiece0 = { "oo o ", "oooo ", "oooo ", " oooo", "  o  " };
		// String[] tPiece1 = { "   oo", "oooo ", "ooooo", " ooo ", " o o " };
		// String[] tPiece2 = { " o   ", "oooo ", " oooo", "oooo ", "  o  " };
		// String[] tPiece3 = { "oo oo", " oooo", "oooo ", " ooo ", " o o " };
		// String[] tPiece4 = { "  o o", " oooo", "ooooo", "oooo ", "o oo " };
		// String[] tPiece5 = { " o oo", " ooo ", " oooo", "oooo ", "oo o " };

		int[][] convertToArray1 = convertToArray(tPiece0);
		int[][] convertToArray2 = convertToArray(tPiece1);
		int[][] convertToArray3 = convertToArray(tPiece2);
		int[][] convertToArray4 = convertToArray(tPiece3);
		int[][] convertToArray5 = convertToArray(tPiece5);
		int[][] convertToArray6 = convertToArray(tPiece4);

		List<int[][]> listOfMatrix = new ArrayList<>();

		listOfMatrix.add(convertToArray1);
		listOfMatrix.add(convertToArray2);
		listOfMatrix.add(convertToArray3);
		listOfMatrix.add(convertToArray4);
		listOfMatrix.add(convertToArray5);
		listOfMatrix.add(convertToArray6);
		trigger(listOfMatrix);
		List<Cube> backtrack = backtrack(0, listOfMatrix);
		// System.err.println(controller.getList().size());
		// printPattern(controller.getList());
	}

	public static void main(String[] args) {
		DeriveMatrix deriveMatrix = new DeriveMatrix();

	}

}
