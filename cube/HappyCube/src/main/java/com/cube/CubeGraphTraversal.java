package com.cube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cube.controller.CubeController;
import com.cube.helper.GraphHelper;
import com.cube.model.Cube;

/**
 * This class is responsible to iterate through each orientation (permutations/rotations) of block/piece
 * and pass it to {@link CubeController} for validating its existence in the expected unfolded or all cube structure.
 * 
 * This class is based on Graph Traversal mechanism using backtracking recursion.
 * @author abc
 *
 */
public class CubeGraphTraversal implements IGraph
{


private Map<int[][], List<int[][]>> tracker = new HashMap<>();
   private CubeController controller;
   private OutputStructure outputStructure;

   /**
    * returns all the orientations of each piece or block , see {@link GraphHelper}
    * 
    * @param pieces_left
    * @return
    */
   private Map<int[][], List<int[][]>> getNodeOrientations(List<int[][]> pieces_left)
   {
      for (int[][] piece : pieces_left)
      {
         List<int[][]> findAllOrientations = GraphHelper.findAllOrientations(piece);
         tracker.put(piece, findAllOrientations);
      }

      return tracker;

   }

   /**
    * handles the backtracking of Graph Traversal based on each orientation .
    * Its like each edge permutation of Node in the graph. Here the permutation defines about
    * left, right , top and bottom of each block or piece of happy cube
    * 
    * @param slotid recursively increment the slot_id, max expected is 6
    * @param pieces_left pieces left while backtraking
    * @param isUnfoldedActive Unfolded representation or not
    * @return
    */
   private List<Cube> traverseGraph(int slotid, List<int[][]> pieces_left, boolean isUnfoldedActive)
   {
      if (slotid == 6)
      {
         List<Cube> list = controller.getList();
         outputStructure.betterPrint(controller.getList());
         if(isSingleOutput){
        	 return list;
         }
         controller = new CubeController(outputStructure);
         outputStructure.reset();
         return list;
      }

      for (int[][] piece : pieces_left)
      {
         List<int[][]> list = tracker.get(piece);
         for (int[][] orientationPiece : list)
         {
        	 List<Cube> buildCubeBlock = null;

        	 
        	 if(isUnfoldedActive){
        		 buildCubeBlock = controller.buildUnfoldedCube(
                  slotid, orientationPiece);
        	 }else{
               buildCubeBlock = controller
               .buildCubeInAnyForm(orientationPiece);
        	 }

            if (buildCubeBlock != null && buildCubeBlock.size() == slotid + 1)
            {
               List<int[][]> subList = new ArrayList<int[][]>(pieces_left);
               subList.remove(piece);
               traverseGraph(slotid + 1, subList,isUnfoldedActive);
            }
         }
      }
      return null;

   }


   
   /**
    * client exposed method to traverse the graph.
    * 
    * @param pieces_left returns 2D array representation matrix of block structure (1,0)
    * @param colorBlocks name of the block
    * @param printRequired do you want the console to print the output for debugging
    * @param filePrintRequired do you want to print the content into a file placed in currProjDir/colorBlock/...
    * @param unfoldedActive do you want unfolded representation of cube or all permutation
    * @return
    */
   public List<Cube> traverse(List<int[][]> pieces_left,String colorBlocks,boolean printRequired,boolean filePrintRequired,boolean unfoldedActive){
	   getNodeOrientations(pieces_left);
	   outputStructure = new OutputStructure(colorBlocks,printRequired,filePrintRequired);
	   controller = new CubeController(outputStructure);
	   return traverseGraph(0,pieces_left,unfoldedActive);
	   
   }
   

   
   
   boolean isSingleOutput;
   /**
    * 
    * @param isSingleOutput do you want to display only one result
    */
   public CubeGraphTraversal(boolean isSingleOutput)
   {
	   this.isSingleOutput = isSingleOutput;
   }



}
