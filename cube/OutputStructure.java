package cube;

import java.util.List;

public class OutputStructure {

	private static int[][] outputArr = new int[90][90];
	private static int STARTINDEX = 0;

	public static void updateOutput(int[][] input, int row, int column) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// System.out.printf("%2d", a[i][j]);
				outputArr[row + i][column + j] = input[i][j];
			}
			System.out.println();
		}
	}

	public static void startIndex(int startIndex) {
		STARTINDEX = startIndex;
	}

	public static int[][] getOutputArr() {
		return outputArr;
	}

	public static void print() {
		System.out.println("Printing...");
		// TODO write algorithm to print
		for (int i = 0; i < 90; i++) {
			for (int j = 0; j < 90; j++) {
				System.out.print(outputArr[i][j] == 0 ? " " : "o");
			}
			System.out.println();
		}
	}

	public static void betterPrint(List<Cube> list) {
		for (Cube cube : list) {
			int[][] element = cube.getElement();
			int column = cube.getStartColumn();
			int row = cube.getStartIndex();
			int irow=0;
			int icol = 0;
			for(int i=row;i<row+5;i++){
				for(int j=column;j<column+5;j++){
					outputArr[i][j] = element[irow][icol++];
				}
				icol = 0;
				irow++;
			}
		}
		print();
	}

}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 15 Jul 2015
 */