package cube;

import java.util.ArrayList;
import java.util.List;

public class CubeController {

	public List<Cube> buildCube(int count) {
		List<Cube> cubeList = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Cube cube = new Cube();
			cubeList.add(cube);
		}

		return cubeList;

	}

	private List<Cube> cubeList = new ArrayList<>();

	public List<Cube> getList() {
		return cubeList;
	}

	/**
	 * basic looping for arranging cubes one after another
	 * 
	 * @param sequence
	 * @param matrix
	 * @return
	 */
	public List<Cube> buildCubeBlock(int sequence, int[][] matrix) {
		switch (sequence) {
		case 0:
			// since just a blank scenario , add the cube
			Cube cube = new Cube(matrix);
			cubeList.add(cube);
			cube.linkedEdge = Edge.NONE;
			OutputStructure.updateOutput(matrix, 10, 10);
			break;

		case 1:
			Cube cube1 = cubeList.get(0);
			if (fitRightBasicSequence(cube1, matrix)) {
				cube = new Cube(matrix);
				cubeList.add(cube);
				cube.linkedEdge = Edge.RIGHT;
				OutputStructure.updateOutput(matrix, 10, 15);
			}
			break;
		case 2:
			Cube cube2 = cubeList.get(1);
			if (fitRightBasicSequence(cube2, matrix)) {
				cube = new Cube(matrix);
				cubeList.add(cube);
				cube.linkedEdge = Edge.RIGHT;
				OutputStructure.updateOutput(matrix, 10, 20);
			}
			break;

		case 3:
			// TODO optmize this piece of code
			// here we gotta check middle element and we need to verify top as
			// well as bottom side
			// of it may be it fits somewhere
			Cube mainMiddle = cubeList.get(1); // middle cube
			if (fitBottomBasicSequence(mainMiddle, matrix)) {
				cube = new Cube(matrix);
				cubeList.add(cube);
				cube.linkedEdge = Edge.BOTTOM;
				OutputStructure.updateOutput(matrix, 15, 15);
			} else if (fitTopBasicSequence(mainMiddle, matrix)) {
				cube = new Cube(matrix);
				cubeList.add(cube);
				cube.linkedEdge = Edge.TOP;
				OutputStructure.updateOutput(matrix, 5, 15);
			}
		default:
			System.err.println("Not Expecting More blocks into 6 Piece Cibe");
			break;
		}
		return cubeList;
	}

	/**
	 * if right sequence agreed to collaborate 1-0 XOR
	 * 
	 * @param cube1
	 * @param matrix
	 * @return
	 */
	private boolean fitRightBasicSequence(Cube cube, int[][] matrix) {

		int[] right_cube1 = cube.getRight();
		int[] right_matrix = Util.getLeft(matrix);
		return xorColumns(right_cube1, right_matrix);

	}

	private boolean fitTopBasicSequence(Cube cube, int[][] matrix) {
		int[] topCube = cube.getTop();
		int[] bottomCube = Util.getBottom(matrix);
		return xorColumns(topCube, bottomCube);
	}

	private boolean fitBottomBasicSequence(Cube cube, int[][] matrix) {
		int[] topCube = Util.getTop(matrix);
		int[] bottomCube = cube.getBottom();
		return xorColumns(bottomCube, topCube);
	}

	private boolean xorColumns(int[] thisCube, int[] otherCube) {
		boolean result = true;
		for (int i = 0; i < 5; i++) {
			if (thisCube[i] == 0 && otherCube[i] == 0) {
				continue;
			} else {
				if ((thisCube[i] ^ otherCube[i]) == 0) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

}
