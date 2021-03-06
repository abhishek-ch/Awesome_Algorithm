package com.cube.model;

/**
 * Model class of cube , like skeleton of any cube Block
 * It behaves a graph with 4 nodes left , right , top and bottom
 * @author abc
 *
 */
public class Cube
{


   int[][] element = new int[5][5];

   // hold the reference of adjacent Node
   public Cube left;

   public Cube right;

   public Cube top;

   public Cube bottom;

   // maintains the start row and column index
   int startRow;

   int startColumn;


   public int getStartColumn()
   {
      return startColumn;
   }


   public void setStartColumn(int startColumn)
   {
      this.startColumn = startColumn;
   }


   public Cube(int[][] element)
   {
      this.element = element;
   }


   public Cube()
   {

   }


   public int[] getLeft()
   {
      int[] left = new int[element.length];
      for (int i = 0; i < element.length; i++)
      {
         left[i] = element[i][0];
      }
      return left;
   }


   public int[] getRight()
   {
      int[] right = new int[element.length];
      for (int i = 0; i < element.length; i++)
      {
         right[i] = element[i][4];
      }
      return right;
   }


   public int[] getBottom()
   {
      return element[4];
   }


   public int[] getTop()
   {
      return element[0];
   }


   public int[][] getElement()
   {
      return element;
   }


   public void setStartIndex(int startIndex)
   {
      this.startRow = startIndex;
   }


   public int getStartIndex()
   {
      return startRow;
   }
}
