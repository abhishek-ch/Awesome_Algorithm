package com.cube.controller;

import com.cube.helper.Util;
import com.cube.model.Cube;

/**
 * This class holds the responsibility for validation the cube .
 * Ideally a cube is nothing but a Graph Node in datastructure.
 * 
 * Since its a Graph, it has multiple child and we fixed it to
 * 4. More details {@link Cube} Since its being maintained by Graph, so traversing became simply
 * like DFS over any Graph or Tree
 * 
 * @author abc
 *
 */
public class CubeValidation
{


   /**
    * if right sequence agreed to collaborate 1-0 XOR
    * 
    * @param cube the main cube
    * @param matrix to be compared with this 
    * @return if matrix can be attached to right of the cube
    */
   public static boolean fitRightBasicSequence(Cube cube, int[][] matrix, int[][] store)
   {
      return xorColumns(cube.getRight(), Util.getLeft(matrix))
            && revalidateXor(cube.getStartIndex(),
                  cube.getStartColumn() + 5, cube, matrix, store);
   }

   /**
    * if Left sequence agreed to collaborate 1-0 XOR
    * 
    * @param cube
    * @param matrix
    * @return if matrix can be attached to left of the cube
    */
   public static boolean fitLeftBasicSequence(Cube cube, int[][] matrix, int[][] store)
   {
      return xorColumns(cube.getLeft(), Util.getRight(matrix))
            && revalidateXor(cube.getStartIndex(),
                  cube.getStartColumn() - 5, cube, matrix, store);
   }

   /**
    * if top sequence agreed to collaborate 1-0 XOR
    * 
    * @param cube
    * @param matrix
    * @return if matrix can be attached to top of the cube
    */
   public static boolean fitTopBasicSequence(Cube cube, int[][] matrix, int[][] store)
   {
      return xorColumns(Util.getBottom(matrix), cube.getTop())
            && revalidateXor(cube.getStartIndex() - 5,
                  cube.getStartColumn(), cube, matrix, store);
   }

   /**
    * if bottom sequence agreed to collaborate 1-0 XOR
    * 
    * @param cube
    * @param matrix
    * @return if matrix can be attached to bottom of the cube
    */
   public static boolean fitBottomBasicSequence(Cube cube, int[][] matrix, int[][] store)
   {
      return xorColumns(cube.getBottom(), Util.getTop(matrix))
            && revalidateXor(cube.getStartIndex() + 5,
                  cube.getStartColumn(), cube, matrix, store);
   }


   /**
    * On placing component to any edge reference of {@link Cube} ie left, right ...
    * its must to see the surrounding as well. Because validation is fine with the reference node, but
    * there is a chance that the desired position has other neighbor as well.
    * 
    * So conceptualize the thing , while adding any piece to Graph, this validates its verifies XORing with
    * each side of its neighbor
    * 
    * @param oRow
    * @param oCol
    * @param cube
    * @param matrix
    * @param store
    * @return
    * @throws ArrayIndexOutOfBoundsException
    */
   private static boolean revalidateXor(int oRow, int oCol, Cube cube,
         int[][] matrix, int[][] store) throws ArrayIndexOutOfBoundsException
   {
      // System.out.println(" Orow " + oRow + " Col " + oCol);
      int[] topLayerFromArray = { store[oRow + 5][oCol], store[oRow + 5][oCol + 1],
            store[oRow + 5][oCol + 2], store[oRow + 5][oCol + 3],
            store[oRow + 5][oCol + 4] };// bottom
      int[] bottomLayerFromArray = { store[oRow - 1][oCol], store[oRow - 1][oCol + 1],
            store[oRow - 1][oCol + 2], store[oRow - 1][oCol + 3],
            store[oRow - 1][oCol + 4] };// top

      return xorColumns(Util.getBottom(matrix), topLayerFromArray)
            && xorColumns(Util.getTop(matrix), bottomLayerFromArray)
            && xorEverything(matrix,store);
   }

   private static boolean xorEverything(int[][] thisCube, int[][] otherCube){
	   boolean result = true;
	   for(int i=0;i<5;i++){
		   for(int j=0;j<5;j++){
			   if (thisCube[i][j] == 0 && otherCube[i][j] == 0)
		         {
		            continue;
		         }
		         else
		         {
		            if ((thisCube[i][j] ^ otherCube[i][j]) == 0)
		            {
		               result = false;
		               break;
		            }
		         }
		   }
	   }
	   return result;
   }
   

   /**
    * Since everything is based on matrix representation , simply use the concept of XOR.
    * So if any 2 pieces can be fit together if either both the sides are 0 , or 1 side is
    * 0 and other is 1.
    * 
    * So XORing simply did the job to validate cell position validation
    * 
    * @param thisCube
    * @param otherCube
    * @return
    */
   private static boolean xorColumns(int[] thisCube, int[] otherCube)
   {
      boolean result = true;
      for (int i = 0; i < 5; i++)
      {
         if (thisCube[i] == 0 && otherCube[i] == 0)
         {
            continue;
         }
         else
         {
            if ((thisCube[i] ^ otherCube[i]) == 0)
            {
               result = false;
               break;
            }
         }
      }
      return result;
   }

}
