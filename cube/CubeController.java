package cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CubeController {
	
	
	public List<Cube> buildCube(int count){
		List<Cube> cubeList = new ArrayList<>();
		for(int i =0;i<count;i++){
			Cube cube = new Cube();
			cubeList.add(cube);
		}
		
		return cubeList;
		
	}
	
	
	private List<Cube> cubeList = new ArrayList<>();
	public List<Cube> buildCubeBlock(int sequence,int[][] matrix){
		switch(sequence){
		case 0:
			//since just a blank scenario , add the cube
			Cube cube = new Cube(matrix);
			cubeList.add(cube);
			break;
			
		case 1:
			Cube cube1 = cubeList.get(0);
			if(fitRightBasicSequence(cube1,matrix)){
				
			}
		}
		return cubeList;
	}
	/**
	 * if right sequence agreed to collaborate 1-0 XOR
	 * @param cube1
	 * @param matrix
	 * @return
	 */
	private boolean fitRightBasicSequence(Cube cube, int[][] matrix) {
		boolean result = true;
		int[] right_cube1 = cube.getRight();
		int[] right_matrix = Util.getLeft(matrix);
		for(int i =0;i<5;i++){
			if(right_cube1[i] == 0 && right_matrix[i] == 0){
				continue;
			}else{
				if((right_cube1[i] ^ right_matrix[i]) == 0){
					result = false;
					break;
				}
			}
		}
		
		return result;
	}

}
