package cube;

public class Cube {
	
	
	
	int[][] element = new int[5][5];
	
	public Cube(int[][] element) {
		this.element = element;
	}
	
	
	public Cube() {
		// TODO Auto-generated constructor stub
	}
	
	public int[] getLeft(){
		int[] left = new int[element.length];
		for (int i = 0; i < element.length; i++) {
			left[i] = element[i][0];
		}
		return left;
	}
	
	
	public int[] getRight(){
		int[] right = new int[element.length];
		for (int i = 0; i < element.length; i++) {
			right[i] = element[i][4];
		}
		return right;
	}
	
	public int[] getBottom(){
		return element[4];
	}
	
	public int[] getTop(){
		return element[0];
	}
}
