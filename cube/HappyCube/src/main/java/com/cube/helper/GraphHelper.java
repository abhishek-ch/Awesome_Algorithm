package com.cube.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * *******************************************************************
 * This class is responsible for handling all the rotations of any Node
 * As its Matrix, so its rotating matrix in every direction clock-wise
 * anticlock wise
 * 
 * It as well mainatins a method for mirror and flip
 * 
 * @author achoudhary
 * @version 1.0.0
 ******************************************************************** 
 */
public class GraphHelper
{


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


   public static int[][] flipLeftToRight(int[][] pixels)
   {

      for (int i = 0; i < pixels.length; i++)
      {
         for (int curr = 0; curr < (pixels[0].length + 1) / 2; curr++)
         {

            int saved = pixels[i][curr];
            pixels[i][curr] = pixels[i][pixels[0].length - 1 - curr];
            pixels[i][pixels[0].length - 1 - curr] = saved;
         }
      }
      return pixels;
   }


   public static int[][] mirror(int width, int height, int[][] in)
   {
      int[][] out = new int[height][width];
      for (int i = 0; i < height; i++)
      {
         for (int j = 0; j < width; j++)
         {
            out[i][width - j - 1] = in[i][j];
         }
      }
      return out;
   }


   public static int[][] rotateClockWise(int[][] pixels)
   {

      int[][] rotate = new int[pixels[0].length][pixels.length];

      for (int i = 0; i < pixels[0].length; i++)
      {
         for (int j = 0; j < pixels.length; j++)
         {
            rotate[i][pixels.length - 1 - j] = pixels[j][i];
         }
      }
      return rotate;
   }


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
