import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class WordLadder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String input = "hit";
		String goal = "cog";
		String[] matched = {"hot","dog","dot","lot","log","sog","mot","pot"};
		LinkedList<String> availableList = new LinkedList<String>(Arrays.asList(matched));
		LinkedList<Integer> distanceQueue = new LinkedList<Integer>();
		LinkedList<String> queue = new LinkedList<String>();
		
		availableList.add(goal);
		
		queue.add(input);
		distanceQueue.add(1);
		int result = Integer.MAX_VALUE;
		while(!queue.isEmpty()){
			String currWord = queue.pop();
			int currDist = distanceQueue.pop();
			
			if(currWord.equals(goal)){
				result = Math.min(result, currDist);
			}
			
			for(int i=0;i<currWord.length();i++){
				char[] charArray = currWord.toCharArray();
				
				for(char c ='a';c<='z';c++){
					charArray[i] = c;
					
					String newWord = new String(charArray);
					if(availableList.contains(newWord)){
						queue.add(newWord);
						distanceQueue.add(currDist+1);
						availableList.remove(newWord);
					}
				}
			}
		}
		
		if (result < Integer.MAX_VALUE)
			System.out.println("Steps are "+result);
		else
			System.err.println("Not Possible");
		
	}

}
