package com.cube;

import java.util.List;

import com.cube.model.Cube;

/**
 * Interface represents a Graph based structure to traverse.
 * It can be updated based on other Graph traversal 
 * @author abc
 *
 */
public interface IGraph {

	public List<Cube> traverse(List<int[][]> pieces_left,String colorBlocks,boolean printRequired,boolean filePrintRequired,boolean unfoldedActive);
}
