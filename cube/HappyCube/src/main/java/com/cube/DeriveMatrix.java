package com.cube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cube.controller.CubeController;
import com.cube.helper.GraphHelper;
import com.cube.helper.Util;
import com.cube.model.Cube;

public class DeriveMatrix
{


   Map<int[][], List<int[][]>> tracker = new HashMap<>();


   public Map<int[][], List<int[][]>> trigger(List<int[][]> pieces_left)
   {
      for (int[][] piece : pieces_left)
      {
         List<int[][]> findAllOrientations = GraphHelper.findAllOrientations(piece);
         tracker.put(piece, findAllOrientations);
      }

      return tracker;

   }


   public List<Cube> backtrack(int slotid, List<int[][]> pieces_left)
   {
      if (slotid == 6)
      {
         List<Cube> list = controller.getList();
         new OutputStructure().betterPrint(controller.getList());
         // System.exit(0);
         controller = new CubeController();
         return list;
      }

      for (int[][] piece : pieces_left)
      {
         List<int[][]> list = tracker.get(piece);
         for (int[][] orientationPiece : list)
         {
            // List<Cube> buildCubeBlock = controller
            // .buildCubeInfrastructure(orientationPiece);

            List<Cube> buildCubeBlock = controller.buildUnfoldedCube(
                  slotid, orientationPiece);

            if (buildCubeBlock.size() == slotid + 1)
            {
               List<int[][]> subList = new ArrayList<int[][]>(pieces_left);
               subList.remove(piece);
               backtrack(slotid + 1, subList);
            }
         }
      }
      return null;

   }

   CubeController controller;


   public DeriveMatrix()
   {
      // TODO Auto-generated constructor stub
      controller = new CubeController();

      // BLUE
      String[] tPiece0 = { "  o  ", " ooo ", "ooooo", " ooo ", "  o  " };
      String[] tPiece1 = { "  o o", "ooooo", " ooo ", "ooooo", " o oo" };
      String[] tPiece2 = { " o o ", " ooo ", "ooooo", " ooo ", "  o  " };
      String[] tPiece3 = { "o o  ", "ooooo", " ooo ", "ooooo", " o o " };
      String[] tPiece4 = { "o o o", "ooooo", " ooo ", "ooooo", "o o o" };
      String[] tPiece5 = { " o o ", "oooo ", " oooo", "oooo ", "oo o " };

      // RED Couldn't find any permutation to unfold red node to 6
      // String[] tPiece0 = { " oo", " ooo ", "ooooo", " ooo ", " o oo" };
      // String[] tPiece1 = { " o o", "oooo ", " oooo", "oooo ", " o " };
      // String[] tPiece2 = { " oo o", "ooooo", " ooo ", "ooooo", "o oo" };
      // String[] tPiece3 = { " o ", "oooo ", " oooo", "oooo ", " o " };
      // String[] tPiece4 = { " oo ", "ooooo", " ooo ", "ooooo", "o o " };
      // String[] tPiece5 = { " oo ", " ooo ", "ooooo", " ooo ", "oo oo" };

      // Purple
      // String[] tPiece0 = { "oo o ", "oooo ", "oooo ", " oooo", "  o  " };
      // String[] tPiece1 = { "   oo", "oooo ", "ooooo", " ooo ", " o o " };
      // String[] tPiece2 = { " o   ", "oooo ", " oooo", "oooo ", "  o  " };
      // String[] tPiece3 = { "oo oo", " oooo", "oooo ", " ooo ", " o o " };
      // String[] tPiece4 = { "  o o", " oooo", "ooooo", "oooo ", "o oo " };
      // String[] tPiece5 = { " o oo", " ooo ", " oooo", "oooo ", "oo o " };

      int[][] convertToArray1 = Util.convertToArray(tPiece0);
      int[][] convertToArray2 = Util.convertToArray(tPiece1);
      int[][] convertToArray3 = Util.convertToArray(tPiece2);
      int[][] convertToArray4 = Util.convertToArray(tPiece3);
      int[][] convertToArray5 = Util.convertToArray(tPiece5);
      int[][] convertToArray6 = Util.convertToArray(tPiece4);

      List<int[][]> listOfMatrix = new ArrayList<>();

      listOfMatrix.add(convertToArray1);
      listOfMatrix.add(convertToArray2);
      listOfMatrix.add(convertToArray3);
      listOfMatrix.add(convertToArray4);
      listOfMatrix.add(convertToArray5);
      listOfMatrix.add(convertToArray6);
      trigger(listOfMatrix);
      backtrack(0, listOfMatrix);
      // System.err.println(controller.getList().size());
      // printPattern(controller.getList());
   }


   public static void main(String[] args)
   {
      DeriveMatrix deriveMatrix = new DeriveMatrix();

   }

}
