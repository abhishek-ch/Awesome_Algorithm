package com.cube.test;

import java.util.List;
import java.util.Map;

import com.cube.CubeGraphTraversal;

/**
 * Print all Unfolded representation of any color block set
 * This is the first challenge of Part 2
 * @author abc
 *
 */
public class Challenge1Part2Test {
	public static void main(String[] args) {
		CubeGraphTraversal traversal = new CubeGraphTraversal(false,true); //true means only one result
		
		TestHelper helper = new TestHelper();
		Map<String, List<int[][]>> cubeBlocksinMatrix = helper.getCubeBlocksinMatrix();
		for (Map.Entry<String,List<int[][]>> entry : cubeBlocksinMatrix.entrySet()) {
			  String key = entry.getKey();
			  List<int[][]> value = entry.getValue();
			  traversal.traverse( value,"output/"+key+"/Challenge1part2",false,true,true);
			}
	}
}
