package com.cube.test;

import java.util.List;
import java.util.Map;

import com.cube.CubeGraphTraversal;

/**
 * Print Single Unfolded representation of any color block set
 * This is the first challenge of Part 1
 * @author abc
 *
 */
public class Challenge2Test {
	public static void main(String[] args) {
		CubeGraphTraversal traversal = new CubeGraphTraversal(false,false); //true means only one result
		
		TestHelper helper = new TestHelper();
		Map<String, List<int[][]>> cubeBlocksinMatrix = helper.getCubeBlocksinMatrix();
		for (Map.Entry<String,List<int[][]>> entry : cubeBlocksinMatrix.entrySet()) {
			  String key = entry.getKey();
			  List<int[][]> value = entry.getValue();
			  traversal.traverse( value,"output/"+key+"/Challenge2",false,true,false);
			  // do stuff
			}
	}
}
