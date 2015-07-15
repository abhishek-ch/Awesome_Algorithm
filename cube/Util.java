package cube;

public class Util {

	public static int[] getRight(int[][] element) {
		int[] right = new int[element.length];
		for (int i = 0; i < element.length; i++) {
			right[i] = element[i][4];
		}
		return right;
	}

	public static int[] getLeft(int[][] element) {
		int[] left = new int[element.length];
		for (int i = 0; i < element.length; i++) {
			left[i] = element[i][0];
		}
		return left;
	}

	public static int[] getTop(int[][] element) {
		return element[0];
	}

	public static int[] getBottom(int[][] element) {
		return element[element.length - 1];
	}
}
