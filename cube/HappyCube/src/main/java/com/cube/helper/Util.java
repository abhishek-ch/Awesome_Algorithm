package com.cube.helper;

public class Util
{


   public static int[] getRight(int[][] element)
   {
      int[] right = new int[element.length];
      for (int i = 0; i < element.length; i++)
      {
         right[i] = element[i][4];
      }
      return right;
   }


   public static int[] getLeft(int[][] element)
   {
      int[] left = new int[element.length];
      for (int i = 0; i < element.length; i++)
      {
         left[i] = element[i][0];
      }
      return left;
   }


   public static int[] getTop(int[][] element)
   {
      return element[0];
   }


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


   public static void printGrid(int[][] a)
   {
      for (int i = 0; i < 5; i++)
      {
         for (int j = 0; j < 5; j++)
         {
            System.out.printf("%2d", a[i][j]);
         }
         System.out.println();
      }
      System.out.println("-------------");
      // System.out.println(Arrays.deepToString(a));
   }
}
