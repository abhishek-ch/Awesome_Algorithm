package cube;

import java.util.ArrayList;
import java.util.List;

public class CubeController {

	private List<Cube> cubeList = new ArrayList<>();

	public List<Cube> getList() {
		return cubeList;
	}

	public List<Cube> buildUnfoldedCube(int sequence, int[][] matrix) {
		if (sequence == 0) {
			// since just a blank scenario , add the cube
			Cube cube = new Cube(matrix);
			cube.setStartIndex(10);
			cube.setStartColumn(10);
			cubeList.add(cube);
			new OutputStructure().updateOutput(matrix, 10, 10);
		}
		if (cubeList.size() > 0) {
			switch (sequence) {

			case 1:
				// Right
				Cube cube1 = cubeList.get(0);
				if (fitRightBasicSequence(cube1, matrix)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(10);
					cube.setStartColumn(15);
					cube1.right = cube;
					cube.left = cube1;
					cubeList.add(cube);
					new OutputStructure().updateOutput(matrix, 10, 15);
				}
				break;
			case 2:
				// Left of Middle Node
				cube1 = cubeList.get(0);
				if (fitLeftBasicSequence(cube1, matrix)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(10);
					cube.setStartColumn(5);
					cube1.left = cube;
					cube.right = cube1;
					cubeList.add(cube);
					new OutputStructure().updateOutput(matrix, 10, 5);
				}
				break;

			case 3:
				// Top of Middle Node
				cube1 = cubeList.get(0);
				if (fitTopBasicSequence(cube1, matrix)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(5);
					cube.setStartColumn(10);
					cube1.top = cube;
					cube.bottom = cube1;
					cubeList.add(cube);
					new OutputStructure().updateOutput(matrix, 5, 10);
				}
				break;
			case 4:
				// Bottom of Middle Node
				cube1 = cubeList.get(0);
				if (fitBottomBasicSequence(cube1, matrix)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(15);
					cube.setStartColumn(10);
					cube1.bottom = cube;
					cube.top = cube1;
					cubeList.add(cube);
					new OutputStructure().updateOutput(matrix, 15, 10);
				}
				break;
			case 5:
				Cube cube4 = cubeList.get(4);
				if (fitBottomBasicSequence(cube4, matrix)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(15);
					cube.setStartColumn(10);
					cube4.bottom = cube;
					cube.top = cube4;
					cubeList.add(cube);
					new OutputStructure().updateOutput(matrix, 20, 10);
				}
				break;
			// Last or tail of the Tree
			}
		}
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
			new OutputStructure().updateOutput(matrix, 10, 10);
			break;

		case 1:
			Cube middle = cubeList.get(0);
			if (middle.right == null && fitRightBasicSequence(middle, matrix)) {
				cube = new Cube(matrix);
				cubeList.add(cube);
				new OutputStructure().updateOutput(matrix, 10, 15);
			}
			break;
		case 2:
			Cube cube2 = cubeList.get(1);
			if (cube2.right == null && fitRightBasicSequence(cube2, matrix)) {
				cube = new Cube(matrix);
				cubeList.add(cube);
				new OutputStructure().updateOutput(matrix, 10, 5);
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
				new OutputStructure().updateOutput(matrix, 15, 15);
			} else if (fitTopBasicSequence(mainMiddle, matrix)) {
				cube = new Cube(matrix);
				cubeList.add(cube);
				new OutputStructure().updateOutput(matrix, 5, 15);
			}
		default:
			System.err.println("Not Expecting More blocks into 6 Piece Cibe");
			break;
		}
		return cubeList;
	}

	public List<Cube> buildCubeInfrastructure(int[][] matrix) {
		try {
			if (cubeList.size() > 0) {
				for (int i = 0; i < cubeList.size(); i++) {
					Cube cube = cubeList.get(i);
					if (cube.left == null && fitLeftBasicSequence(cube, matrix)) {
						Cube leftcube = new Cube(matrix);
						// reset the left
						leftcube.setStartIndex(cube.getStartIndex());
						leftcube.setStartColumn(cube.getStartColumn() - 5);
						cube.left = leftcube;
						leftcube.right = cube;
						cubeList.add(leftcube);
						updateStore(matrix, leftcube.getStartIndex(),
								leftcube.getStartColumn());
						// System.out.println(cube.getStartIndex()+" =
						// "+(cube.getStartColumn()-5)+" LEFT");
						break;
					} else if (cube.right == null
							&& fitRightBasicSequence(cube, matrix)) {
						Cube newcube = new Cube(matrix);
						cube.right = newcube;
						newcube.left = cube;
						newcube.setStartIndex(cube.getStartIndex());
						newcube.setStartColumn(cube.getStartColumn() + 5);
						updateStore(matrix, newcube.getStartIndex(),
								newcube.getStartColumn());
						cubeList.add(newcube);
						// System.out.println(cube.getStartIndex()+" =
						// "+(cube.getStartColumn()+5)+" RIGHT");
						break;
					} else if (cube.top == null
							&& fitTopBasicSequence(cube, matrix)) {
						Cube newcube = new Cube(matrix);
						cube.top = newcube;
						newcube.bottom = cube;
						newcube.setStartIndex(cube.getStartIndex() - 5);
						newcube.setStartColumn(cube.getStartColumn());
						updateStore(matrix, newcube.getStartIndex(),
								newcube.getStartColumn());
						// System.out.println((cube.getStartIndex()-5)+" =
						// "+(cube.getStartColumn())+" TOP");
						cubeList.add(newcube);
						break;
					} else if (cube.bottom == null
							&& fitBottomBasicSequence(cube, matrix)) {
						Cube newcube = new Cube(matrix);
						cube.bottom = newcube;
						newcube.top = cube;
						newcube.setStartIndex(cube.getStartIndex() + 5);
						newcube.setStartColumn(cube.getStartColumn());
						updateStore(matrix, newcube.getStartIndex(),
								newcube.getStartColumn());
						cubeList.add(newcube);
						// System.out.println((cube.getStartIndex()+5)+" =
						// "+(cube.getStartColumn())+" BOTTOM");
						break;
					}
				}
			} else {
				Cube cube = new Cube(matrix);
				cubeList.add(cube);
				cube.setStartIndex(10);
				cube.setStartColumn(10);
				updateStore(matrix, 10, 10);
			}
		} catch (Exception e) {
			// System.err.println("Error occured from : " +
			// e.getLocalizedMessage());
		}

		return cubeList;
	}

	private void updateStore(int[][] matrix, int row, int column) {
		int irow = 0;
		int icol = 0;
		for (int i = row; i < row + 5; i++) {
			for (int j = column; j < column + 5; j++) {
				store[i][j] = matrix[irow][icol++];
			}
			icol = 0;
			irow++;
		}
	}

	int[][] store = new int[Constant.ROW][Constant.COLUMN];

	/**
	 * if right sequence agreed to collaborate 1-0 XOR
	 * 
	 * @param cube1
	 * @param matrix
	 * @return
	 */
	private boolean fitRightBasicSequence(Cube cube, int[][] matrix) {
		return xorColumns(cube.getRight(), Util.getLeft(matrix))
				&& safeCheckBottomTopXor(cube.getStartIndex(),
						cube.getStartColumn() + 5, cube, matrix);
	}

	private boolean fitLeftBasicSequence(Cube cube, int[][] matrix) {
		return xorColumns(cube.getLeft(), Util.getRight(matrix))
				&& safeCheckBottomTopXor(cube.getStartIndex(),
						cube.getStartColumn() - 5, cube, matrix);
	}

	private boolean fitTopBasicSequence(Cube cube, int[][] matrix) {
		return xorColumns(Util.getBottom(matrix), cube.getTop())
				&& safeCheckBottomTopXor(cube.getStartIndex() - 5,
						cube.getStartColumn(), cube, matrix);
	}

	private boolean fitBottomBasicSequence(Cube cube, int[][] matrix) {
		return xorColumns(cube.getBottom(), Util.getTop(matrix))
				&& safeCheckBottomTopXor(cube.getStartIndex() + 5,
						cube.getStartColumn(), cube, matrix);
	}

	// TODO anything can be done
	private boolean validatePositionOfPieceInCube(Cube cube, int[][] matrix,
			int row, int column) {
		return xorColumns(cube.getBottom(), Util.getTop(matrix))
				&& safeCheckBottomTopXor(row, column, cube, matrix);
	}

	private boolean safeCheckBottomTopXor(int oRow, int oCol, Cube cube,
			int[][] matrix) throws ArrayIndexOutOfBoundsException {
		// System.out.println(" Orow " + oRow + " Col " + oCol);
		int[] test2 = { store[oRow + 5][oCol], store[oRow + 5][oCol + 1],
				store[oRow + 5][oCol + 2], store[oRow + 5][oCol + 3],
				store[oRow + 5][oCol + 4] };// bottom
		int[] test3 = { store[oRow - 1][oCol], store[oRow - 1][oCol + 1],
				store[oRow - 1][oCol + 2], store[oRow - 1][oCol + 3],
				store[oRow - 1][oCol + 4] };// top

		return xorColumns(Util.getBottom(matrix), test2)
				&& xorColumns(Util.getTop(matrix), test3);
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
