package com.cube.controller;

import java.util.ArrayList;
import java.util.List;

import com.cube.OutputStructure;
import com.cube.helper.Constant;
import com.cube.model.Cube;

/**
 * Controller class responsible to delegate each action happens over the cube .
 * 
 * It builds the unfolded or all permutation cube
 * @author abc
 *
 */
public class CubeController {

	private List<Cube> cubeList = new ArrayList<>();
	private int[][] store = new int[Constant.ROW][Constant.COLUMN];
	private OutputStructure outputStructure;

	public CubeController(OutputStructure outputStructure) {
		this.outputStructure = outputStructure;
	}

	public List<Cube> getList() {
		return cubeList;
	}

	/**
	 * This method handles the representation of a Cube to its Unfolded form.
	 * This method is responsible for presentation the cube in Unfolded format.
	 * Its getting called recursively for each orientation of any piece of cube,
	 * all other occurrence is being verified and the one which fits takes the
	 * advantage.
	 * 
	 * It defines a matrix named store(graph).Store is nothing but a
	 * representation of Graph For each iteration over cube orientations, it
	 * sets the value to the matrix store in the correct state.Matrix acts like
	 * a big blank graph which multiple nodes define with no definition. a) For
	 * sequence 0, it just a blank Cube , so it doesn't need to face any
	 * validation while adding to the graph. So first node being added to the
	 * graph. (node1) b) Then next node try to set to left of Node1. So while
	 * validating the same, it needs to make sure the xor status as defined in
	 * {@link CubeValidation}. Once thats achieved it adds the Node to left of
	 * node1 and it changes the graph edge references of both nodes 1 and 2. c)
	 * Following on each of the nodes, and there validation almost does the
	 * similar task only difference is reference setting
	 * 
	 * @param sequence
	 * @param matrix
	 * @return
	 */
	public List<Cube> buildUnfoldedCube(int sequence, int[][] matrix) {
		if (sequence == 0) {
			// since just a blank scenario , add the cube
			Cube cube = new Cube(matrix);
			cube.setStartIndex(10);
			cube.setStartColumn(10);
			cubeList.add(cube);
			outputStructure.updateOutput(matrix, 10, 10);
		}
		if (cubeList.size() > 0) {
			switch (sequence) {

			case 1:
				// Right
				Cube cube1 = cubeList.get(0);
				if (CubeValidation.fitRightBasicSequence(cube1, matrix, store)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(10);
					cube.setStartColumn(15);
					cube1.right = cube;
					cube.left = cube1;
					cubeList.add(cube);
					outputStructure.updateOutput(matrix, 10, 15);
				}
				break;
			case 2:
				// Left of Middle Node
				cube1 = cubeList.get(0);
				if (CubeValidation.fitLeftBasicSequence(cube1, matrix, store)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(10);
					cube.setStartColumn(5);
					cube1.left = cube;
					cube.right = cube1;
					cubeList.add(cube);
					outputStructure.updateOutput(matrix, 10, 5);
				}
				break;

			case 3:
				// Top of Middle Node
				cube1 = cubeList.get(0);
				if (CubeValidation.fitTopBasicSequence(cube1, matrix, store)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(5);
					cube.setStartColumn(10);
					cube1.top = cube;
					cube.bottom = cube1;
					cubeList.add(cube);
					outputStructure.updateOutput(matrix, 5, 10);
				}
				break;
			case 4:
				// Bottom of Middle Node
				cube1 = cubeList.get(0);
				if (CubeValidation.fitBottomBasicSequence(cube1, matrix, store)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(15);
					cube.setStartColumn(10);
					cube1.bottom = cube;
					cube.top = cube1;
					cubeList.add(cube);
					outputStructure.updateOutput(matrix, 15, 10);
				}
				break;
			case 5:
				Cube cube4 = cubeList.get(4);
				if (CubeValidation.fitBottomBasicSequence(cube4, matrix, store)) {
					Cube cube = new Cube(matrix);
					cube.setStartIndex(20);
					cube.setStartColumn(10);
					cube4.bottom = cube;
					cube.top = cube4;
					cubeList.add(cube);
					outputStructure.updateOutput(matrix, 20, 10);
				}
				break;
			// Last or tail of the Tree
			}
		}
		return cubeList;
	}

	/**
	 * This method actively inherits the same concept as defined in method
	 * buildUnfoldedCube but this method doesn't work based on only unfolded
	 * representation. This method will check for all available locations over
	 * the graph to build a perfect match
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Cube> buildCubeInAnyForm(int[][] matrix) {
		try {
			if (cubeList.size() > 0) {
				for (int i = 0; i < cubeList.size(); i++) {
					Cube cube = cubeList.get(i);
					if (cube.left == null && CubeValidation.fitLeftBasicSequence(cube, matrix, store)) {
						Cube leftcube = new Cube(matrix);
						// reset the left
						leftcube.setStartIndex(cube.getStartIndex());
						leftcube.setStartColumn(cube.getStartColumn() - 5);
						cube.left = leftcube;
						leftcube.right = cube;
						cubeList.add(leftcube);
						updateStore(matrix, leftcube.getStartIndex(), leftcube.getStartColumn());
						break;
					} else if (cube.right == null && CubeValidation.fitRightBasicSequence(cube, matrix, store)) {
						Cube newcube = new Cube(matrix);
						cube.right = newcube;
						newcube.left = cube;
						newcube.setStartIndex(cube.getStartIndex());
						newcube.setStartColumn(cube.getStartColumn() + 5);
						updateStore(matrix, newcube.getStartIndex(), newcube.getStartColumn());
						cubeList.add(newcube);
						break;
					} else if (cube.top == null && CubeValidation.fitTopBasicSequence(cube, matrix, store)) {
						Cube newcube = new Cube(matrix);
						cube.top = newcube;
						newcube.bottom = cube;
						newcube.setStartIndex(cube.getStartIndex() - 5);
						newcube.setStartColumn(cube.getStartColumn());
						updateStore(matrix, newcube.getStartIndex(), newcube.getStartColumn());
						cubeList.add(newcube);
						break;
					} else if (cube.bottom == null && CubeValidation.fitBottomBasicSequence(cube, matrix, store)) {
						Cube newcube = new Cube(matrix);
						cube.bottom = newcube;
						newcube.top = cube;
						newcube.setStartIndex(cube.getStartIndex() + 5);
						newcube.setStartColumn(cube.getStartColumn());
						updateStore(matrix, newcube.getStartIndex(), newcube.getStartColumn());
						cubeList.add(newcube);
						break;
					}
				}
			} else {
				Cube cube = new Cube(matrix);
				cubeList.add(cube);
				cube.setStartIndex(10);
				cube.setStartColumn(30);
				updateStore(matrix, 10, 30);
			}
		} catch (Exception e) {
			// System.err.println("Error occured from : " +
			// e.getLocalizedMessage());
		}

		return cubeList;
	}

	/**
	 * Update the Graph will latest updated value to ensure the validation check
	 * 
	 * @param matrix
	 * @param row
	 * @param column
	 */
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

}
