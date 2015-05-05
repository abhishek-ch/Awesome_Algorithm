public class RotateImageInPlace
{

	public void rotate(int[][] matrix)
	{
		
		
		printArray(matrix, "MAIN ARRAY");
		
		//rotate diagonally A = A'
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = i; j < matrix.length; j++)
			{
				if(i != j){
					int temp = matrix[i][j];
					matrix[i][j] = matrix[j][i];
					matrix[j][i] = temp;
				}
			}
		}
		
		printArray(matrix, "A = A'");
		
		
		//final rotoate the rowwith last to first sequenctially
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix.length/2; j++)
			{
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][matrix.length-(j+1)];
				matrix[i][matrix.length-(j+1)] = temp;
			}
		}
		
		printArray(matrix, "AFTER ROTATION");
	}
	
	private void printArray(int[][] matrix,String Title){
		System.out.println(Title);
		System.out.println("---------------");
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix.length; j++)
			{
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println("\n\n");
		
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int[][] matrix = new int[][]{
									{1,2,3,4},
									{5,6,7,8},
									{9,10,11,12},
									{13,14,15,16}
		};
		
		new RotateImageInPlace().rotate(matrix);
	}

}
