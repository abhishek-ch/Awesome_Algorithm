package cube;

import java.util.ArrayList;
import java.util.List;

public class CubeController {


	private List<Cube> cubeList = new ArrayList<>();

	public List<Cube> getList() {
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
						updateStore(matrix, leftcube.getStartIndex(), leftcube.getStartColumn());
						// System.out.println(cube.getStartIndex()+" =
						// "+(cube.getStartColumn()-5)+" LEFT");
						break;
					} else if (cube.right == null && fitRightBasicSequence(cube, matrix)) {
						Cube newcube = new Cube(matrix);
						cube.right = newcube;
						newcube.left = cube;
						newcube.setStartIndex(cube.getStartIndex());
						newcube.setStartColumn(cube.getStartColumn() + 5);
						updateStore(matrix, newcube.getStartIndex(), newcube.getStartColumn());
						cubeList.add(newcube);
						// System.out.println(cube.getStartIndex()+" =
						// "+(cube.getStartColumn()+5)+" RIGHT");
						break;
					} else if (cube.top == null && fitTopBasicSequence(cube, matrix)) {
						Cube newcube = new Cube(matrix);
						cube.top = newcube;
						newcube.bottom = cube;
						newcube.setStartIndex(cube.getStartIndex() - 5);
						newcube.setStartColumn(cube.getStartColumn());
						updateStore(matrix, newcube.getStartIndex(), newcube.getStartColumn());
						// System.out.println((cube.getStartIndex()-5)+" =
						// "+(cube.getStartColumn())+" TOP");
						cubeList.add(newcube);
						break;
					} else if (cube.bottom == null && fitBottomBasicSequence(cube, matrix)) {
						Cube newcube = new Cube(matrix);
						cube.bottom = newcube;
						newcube.top = cube;
						newcube.setStartIndex(cube.getStartIndex() + 5);
						newcube.setStartColumn(cube.getStartColumn());
						updateStore(matrix, newcube.getStartIndex(), newcube.getStartColumn());
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
			System.err.println("Error occured from : " + e.getLocalizedMessage());
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

	int[][] store = new int[40][40];

	/**
	 * if right sequence agreed to collaborate 1-0 XOR
	 * 
	 * @param cube1
	 * @param matrix
	 * @return
	 */
	private boolean fitRightBasicSequence(Cube cube, int[][] matrix) {
		return xorColumns(cube.getRight(), Util.getLeft(matrix))
				&& safeCheckBottomTopXor(cube.getStartIndex(), cube.getStartColumn() + 5, cube, matrix);
	}

	private boolean fitLeftBasicSequence(Cube cube, int[][] matrix) {
		return xorColumns(cube.getLeft(), Util.getRight(matrix))
				&& safeCheckBottomTopXor(cube.getStartIndex(), cube.getStartColumn() - 5, cube, matrix);
	}

	private boolean fitTopBasicSequence(Cube cube, int[][] matrix) {
		return xorColumns(Util.getBottom(matrix), cube.getTop())
				&& safeCheckBottomTopXor(cube.getStartIndex() - 5, cube.getStartColumn(), cube, matrix);
	}

	private boolean fitBottomBasicSequence(Cube cube, int[][] matrix) {
		return xorColumns(cube.getBottom(), Util.getTop(matrix))
				&& safeCheckBottomTopXor(cube.getStartIndex() + 5, cube.getStartColumn(), cube, matrix);
	}

	// TODO anything can be done
	private boolean validatePositionOfPieceInCube(Cube cube, int[][] matrix, int row, int column) {
		return xorColumns(cube.getBottom(), Util.getTop(matrix)) && safeCheckBottomTopXor(row, column, cube, matrix);
	}

	private boolean safeCheckBottomTopXor(int oRow, int oCol, Cube cube, int[][] matrix)
			throws ArrayIndexOutOfBoundsException {
		int[] test2 = { store[oRow + 5][oCol], store[oRow + 5][oCol + 1], store[oRow + 5][oCol + 2],
				store[oRow + 5][oCol + 3], store[oRow + 5][oCol + 4] };// bottom
		int[] test3 = { store[oRow - 1][oCol], store[oRow - 1][oCol + 1], store[oRow - 1][oCol + 2],
				store[oRow - 1][oCol + 3], store[oRow - 1][oCol + 4] };// top

		return xorColumns(Util.getBottom(matrix), test2) && xorColumns(Util.getTop(matrix), test3);
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
