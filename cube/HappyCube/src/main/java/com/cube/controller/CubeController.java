package com.cube.controller;

import java.util.ArrayList;
import java.util.List;

import com.cube.OutputStructure;
import com.cube.helper.Constant;
import com.cube.model.Cube;

public class CubeController
{


   private List<Cube> cubeList = new ArrayList<>();

   private int[][] store = new int[Constant.ROW][Constant.COLUMN];


   public List<Cube> getList()
   {
      return cubeList;
   }


   public List<Cube> buildUnfoldedCube(int sequence, int[][] matrix)
   {
      if (sequence == 0)
      {
         // since just a blank scenario , add the cube
         Cube cube = new Cube(matrix);
         cube.setStartIndex(10);
         cube.setStartColumn(10);
         cubeList.add(cube);
         new OutputStructure().updateOutput(matrix, 10, 10);
      }
      if (cubeList.size() > 0)
      {
         switch (sequence)
         {

         case 1:
            // Right
            Cube cube1 = cubeList.get(0);
            if (CubeValidation.fitRightBasicSequence(cube1, matrix, store))
            {
               Cube cube = new Cube(matrix);
               cube.setStartIndex(10);
               cube.setStartColumn(15);
               cube1.right = cube;
               cube.left = cube1;
               cubeList.add(cube);
               new OutputStructure().updateOutput(matrix, 10, 15);
            }
            break;
         case 2:
            // Left of Middle Node
            cube1 = cubeList.get(0);
            if (CubeValidation.fitLeftBasicSequence(cube1, matrix, store))
            {
               Cube cube = new Cube(matrix);
               cube.setStartIndex(10);
               cube.setStartColumn(5);
               cube1.left = cube;
               cube.right = cube1;
               cubeList.add(cube);
               new OutputStructure().updateOutput(matrix, 10, 5);
            }
            break;

         case 3:
            // Top of Middle Node
            cube1 = cubeList.get(0);
            if (CubeValidation.fitTopBasicSequence(cube1, matrix, store))
            {
               Cube cube = new Cube(matrix);
               cube.setStartIndex(5);
               cube.setStartColumn(10);
               cube1.top = cube;
               cube.bottom = cube1;
               cubeList.add(cube);
               new OutputStructure().updateOutput(matrix, 5, 10);
            }
            break;
         case 4:
            // Bottom of Middle Node
            cube1 = cubeList.get(0);
            if (CubeValidation.fitBottomBasicSequence(cube1, matrix, store))
            {
               Cube cube = new Cube(matrix);
               cube.setStartIndex(15);
               cube.setStartColumn(10);
               cube1.bottom = cube;
               cube.top = cube1;
               cubeList.add(cube);
               new OutputStructure().updateOutput(matrix, 15, 10);
            }
            break;
         case 5:
            Cube cube4 = cubeList.get(4);
            if (CubeValidation.fitBottomBasicSequence(cube4, matrix, store))
            {
               Cube cube = new Cube(matrix);
               cube.setStartIndex(15);
               cube.setStartColumn(10);
               cube4.bottom = cube;
               cube.top = cube4;
               cubeList.add(cube);
               new OutputStructure().updateOutput(matrix, 20, 10);
            }
            break;
         // Last or tail of the Tree
         }
      }
      return cubeList;
   }


   public List<Cube> buildCubeInfrastructure(int[][] matrix)
   {
      try
      {
         if (cubeList.size() > 0)
         {
            for (int i = 0; i < cubeList.size(); i++)
            {
               Cube cube = cubeList.get(i);
               if (cube.left == null && CubeValidation.fitLeftBasicSequence(cube, matrix, store))
               {
                  Cube leftcube = new Cube(matrix);
                  // reset the left
                  leftcube.setStartIndex(cube.getStartIndex());
                  leftcube.setStartColumn(cube.getStartColumn() - 5);
                  cube.left = leftcube;
                  leftcube.right = cube;
                  cubeList.add(leftcube);
                  updateStore(matrix, leftcube.getStartIndex(),
                        leftcube.getStartColumn());
                  // System.out.println(cube.getStartIndex()+" =
                  // "+(cube.getStartColumn()-5)+" LEFT");
                  break;
               }
               else if (cube.right == null
                     && CubeValidation.fitRightBasicSequence(cube, matrix, store))
               {
                  Cube newcube = new Cube(matrix);
                  cube.right = newcube;
                  newcube.left = cube;
                  newcube.setStartIndex(cube.getStartIndex());
                  newcube.setStartColumn(cube.getStartColumn() + 5);
                  updateStore(matrix, newcube.getStartIndex(),
                        newcube.getStartColumn());
                  cubeList.add(newcube);
                  // System.out.println(cube.getStartIndex()+" =
                  // "+(cube.getStartColumn()+5)+" RIGHT");
                  break;
               }
               else if (cube.top == null
                     && CubeValidation.fitTopBasicSequence(cube, matrix, store))
               {
                  Cube newcube = new Cube(matrix);
                  cube.top = newcube;
                  newcube.bottom = cube;
                  newcube.setStartIndex(cube.getStartIndex() - 5);
                  newcube.setStartColumn(cube.getStartColumn());
                  updateStore(matrix, newcube.getStartIndex(),
                        newcube.getStartColumn());
                  // System.out.println((cube.getStartIndex()-5)+" =
                  // "+(cube.getStartColumn())+" TOP");
                  cubeList.add(newcube);
                  break;
               }
               else if (cube.bottom == null
                     && CubeValidation.fitBottomBasicSequence(cube, matrix, store))
               {
                  Cube newcube = new Cube(matrix);
                  cube.bottom = newcube;
                  newcube.top = cube;
                  newcube.setStartIndex(cube.getStartIndex() + 5);
                  newcube.setStartColumn(cube.getStartColumn());
                  updateStore(matrix, newcube.getStartIndex(),
                        newcube.getStartColumn());
                  cubeList.add(newcube);
                  // System.out.println((cube.getStartIndex()+5)+" =
                  // "+(cube.getStartColumn())+" BOTTOM");
                  break;
               }
            }
         }
         else
         {
            Cube cube = new Cube(matrix);
            cubeList.add(cube);
            cube.setStartIndex(10);
            cube.setStartColumn(10);
            updateStore(matrix, 10, 10);
         }
      }
      catch (Exception e)
      {
         // System.err.println("Error occured from : " +
         // e.getLocalizedMessage());
      }

      return cubeList;
   }


   private void updateStore(int[][] matrix, int row, int column)
   {
      int irow = 0;
      int icol = 0;
      for (int i = row; i < row + 5; i++)
      {
         for (int j = column; j < column + 5; j++)
         {
            store[i][j] = matrix[irow][icol++];
         }
         icol = 0;
         irow++;
      }
   }

}
