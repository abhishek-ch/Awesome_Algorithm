package com.cube.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling all the rotations of any Node
 * As its Matrix, so its rotating matrix in every direction clock-wise
 * anticlock wise
 * 
 * It as well mainatins a method for mirror and flip
 * 
 * @author abc
 *
 */
public class GraphHelper
{

	/**
	 * returns the flipping of entire block/piece
	 * @param theArray the original block content as matrix
	 * @return
	 */
   public static int[][] flipInPlace(int[][] theArray)
   {
      for (int i = 0; i < (theArray.length / 2); i++)
      {
         int[] temp = theArray[i];
         theArray[i] = theArray[theArray.length - i - 1];
         theArray[theArray.length - i - 1] = temp;
      }
      return theArray;
   }

   /**
    * flip the block from left to right
    * @param theArray theArray the original block content as matrix
    * @return
    */
   public static int[][] flipLeftToRight(int[][] theArray)
   {

      for (int i = 0; i < theArray.length; i++)
      {
         for (int curr = 0; curr < (theArray[0].length + 1) / 2; curr++)
         {

            int saved = theArray[i][curr];
            theArray[i][curr] = theArray[i][theArray[0].length - 1 - curr];
            theArray[i][theArray[0].length - 1 - curr] = saved;
         }
      }
      return theArray;
   }

   /**
    * returns the mirror of cube
    * @param width columns
    * @param height number of rows
    * @param theArray 
    * @return 
    */
   public static int[][] mirror(int width, int height, int[][] theArray)
   {
      int[][] out = new int[height][width];
      for (int i = 0; i < height; i++)
      {
         for (int j = 0; j < width; j++)
         {
            out[i][width - j - 1] = theArray[i][j];
         }
      }
      return out;
   }

   /**
    * rotates the matrix to clock wise
    * @param pixels
    * @return
    */
   public static int[][] rotateClockWise(int[][] theArray)
   {

      int[][] rotate = new int[theArray[0].length][theArray.length];

      for (int i = 0; i < theArray[0].length; i++)
      {
         for (int j = 0; j < theArray.length; j++)
         {
            rotate[i][theArray.length - 1 - j] = theArray[j][i];
         }
      }
      return rotate;
   }

   /**
    * rotates the component anti clock-wise
    * @param pixels
    * @return
    */
   public static int[][] rotateAntiClockwise(int[][] pixels)
   {

      int[][] newarray = new int[pixels[0].length][pixels.length];

      for (int i = 0; i < pixels.length; i++)
      {
         for (int j = 0; j < pixels[0].length; j++)
         {
            newarray[pixels[0].length - 1 - j][i] = pixels[i][j];
         }
      }

      return newarray;
   }

   /**
    * returns all 360 rotations in clock-wise in given matrix
    * @param matrix
    * @return
    */
   public static List<int[][]> findAllPossibleRightRotations(int[][] matrix)
   {
      List<int[][]> allPossibleRotations = new ArrayList<>();
      for (int i = 0; i < 3; i++)
      {
         matrix = rotateClockWise(matrix);
         allPossibleRotations.add(matrix);
      }
      return allPossibleRotations;
   }

   /**
    * returns all 360 rotations made anti clock-wise in given matrix
    * @param matrix
    * @return
    */
   public static List<int[][]> findAllPossibleLefttRotations(int[][] matrix)
   {
      List<int[][]> allPossibleRotations = new ArrayList<>();
      for (int i = 0; i < 3; i++)
      {
         matrix = rotateAntiClockwise(matrix);
         allPossibleRotations.add(matrix);
      }
      return allPossibleRotations;
   }

   /**
    * find all orientations of any matrix or piece provided
    * @param matrix
    * @return
    */
   public static List<int[][]> findAllOrientations(int[][] matrix)
   {
      List<int[][]> allPossibleRotations = new ArrayList<>();
      allPossibleRotations.add(matrix);
      allPossibleRotations.add(flipInPlace(matrix));
      allPossibleRotations
            .add(mirror(matrix[0].length, matrix.length, matrix));

      allPossibleRotations
            .addAll(findAllPossibleRightRotations(allPossibleRotations
                  .get(0)));

      allPossibleRotations
            .addAll(findAllPossibleRightRotations(allPossibleRotations
                  .get(2)));

      return allPossibleRotations;
   }
}
