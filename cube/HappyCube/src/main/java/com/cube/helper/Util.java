package com.cube.helper;

/**
 * Utilities 
 * @author abc
 *
 */
public class Util
{

	/**
	 * get right vertical elements of an array
	 * @param element
	 * @return
	 */
   public static int[] getRight(int[][] element)
   {
      int[] right = new int[element.length];
      for (int i = 0; i < element.length; i++)
      {
         right[i] = element[i][4];
      }
      return right;
   }

   /**
    * returns left vertical elements of an array
    * @param element
    * @return
    */
   public static int[] getLeft(int[][] element)
   {
      int[] left = new int[element.length];
      for (int i = 0; i < element.length; i++)
      {
         left[i] = element[i][0];
      }
      return left;
   }

   /**
    * get top row of a 2d array or matrix
    * @param element
    * @return
    */
   public static int[] getTop(int[][] element)
   {
      return element[0];
   }

   /**
    * get last row of a 2D array or matrix
    * @param element
    * @return
    */
   public static int[] getBottom(int[][] element)
   {
      return element[element.length - 1];
   }


   /**
    * Convert String array to Matrix like representation
    * 
    * @param cube
    * @return
    */
   public static int[][] convertToArray(String[] cube)
   {

      int[][] matrix = new int[5][5];
      int rowIndex = 0;

      for (String row : cube)
      {
         char[] charArray = row.toCharArray();
         int columnIndex = 0;
         for (char c : charArray)
         {
            if (c == ' ')
            {
               matrix[rowIndex][columnIndex] = 0;
            }
            else
            {
               matrix[rowIndex][columnIndex] = 1;
            }
            columnIndex++;
         }
         rowIndex++;
      }
      return matrix;
   }



}
