package com.cube.controller;

import com.cube.helper.Util;
import com.cube.model.Cube;

/**
 * *******************************************************************
 * This class holds the responsibility for validation the cube .
 * Ideally a cube is nothing but a Graph Node in datastructure.
 * 
 * Since its a Graph, it has multiple child and we fixed it to
 * 4. More details {@link Cube} Since its being maintained by Graph, so traversing became simply
 * like DFS over any Graph or Tree
 * 
 * @author achoudhary
 * @version 1.0.0
 ******************************************************************** 
 */
public class CubeValidation
{


   /**
    * if right sequence agreed to collaborate 1-0 XOR
    * 
    * @param cube1
    * @param matrix
    * @return
    */
   public static boolean fitRightBasicSequence(Cube cube, int[][] matrix, int[][] store)
   {
      return xorColumns(cube.getRight(), Util.getLeft(matrix))
            && safeCheckBottomTopXor(cube.getStartIndex(),
                  cube.getStartColumn() + 5, cube, matrix, store);
   }


   public static boolean fitLeftBasicSequence(Cube cube, int[][] matrix, int[][] store)
   {
      return xorColumns(cube.getLeft(), Util.getRight(matrix))
            && safeCheckBottomTopXor(cube.getStartIndex(),
                  cube.getStartColumn() - 5, cube, matrix, store);
   }


   public static boolean fitTopBasicSequence(Cube cube, int[][] matrix, int[][] store)
   {
      return xorColumns(Util.getBottom(matrix), cube.getTop())
            && safeCheckBottomTopXor(cube.getStartIndex() - 5,
                  cube.getStartColumn(), cube, matrix, store);
   }


   public static boolean fitBottomBasicSequence(Cube cube, int[][] matrix, int[][] store)
   {
      return xorColumns(cube.getBottom(), Util.getTop(matrix))
            && safeCheckBottomTopXor(cube.getStartIndex() + 5,
                  cube.getStartColumn(), cube, matrix, store);
   }


   /**
    * On placing component to any edge refrence of {@link Cube} ie left, right ...
    * its must to see the sorrounding as well. Because validation is fine with the reference node, but
    * there is a chance that the desired position has other neighbour as well.
    * 
    * So conceptulize the thing , while adding any piece to Graph, this validates its verifies XORing with
    * each side of its neighbour
    * 
    * @param oRow
    * @param oCol
    * @param cube
    * @param matrix
    * @param store
    * @return
    * @throws ArrayIndexOutOfBoundsException
    */
   private static boolean safeCheckBottomTopXor(int oRow, int oCol, Cube cube,
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
            && xorColumns(Util.getTop(matrix), bottomLayerFromArray);
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
