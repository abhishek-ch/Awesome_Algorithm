public class WordSearch_Recursion {

	/**
	 * DFS Technique used to solve
	 */
	String[][] input = new String[][] { { "A", "B", "C", "E" },
					    { "S", "F", "C", "S" }, 
					    { "A", "D", "E", "E" } };
	
	int r = 0;
	int c = 0;

	public boolean searchWord(String matchingString){
		r = input.length;
		if(r == 0) 
			return false;
		c = input[0].length;
		if(c == 0)
			return false;
		boolean[][] visited = new boolean[r][c];
		for(int i=0;i<r;i++){
			for(int j = 0;j< c;j++){
				if(findWord(input,i,j,0,matchingString,visited))
					return true;
			}
		}
		
		
		return false;
	}
	
	public boolean findWord(String[][] input,int row, int column, int match,
			String matchingString, boolean[][] visited) {
		if (match == matchingString.length()) {
			return true;
		}
		//System.out.println(row+" "+column+" m "+match);
		if(row < 0 || row >= r || column >= c || column<0){
			return false;
		}
		if(visited[row][column])
			return false;
		String charAt = ""+matchingString.charAt(match);
		if(!charAt.equals(input[row][column])){
			return false;
		}
		visited[row][column] = true;
		boolean result = (findWord(input,row+1,column,match+1,matchingString,visited)
				|| findWord(input,row-1,column,match+1,matchingString,visited)
				|| findWord(input,row,column+1,match+1,matchingString,visited)
				|| findWord(input,row,column-1,match+1,matchingString,visited));
		visited[row][column] = false;
		
		
		return result;

	}

	public static void main(String[] args) {
		WordSearch_Recursion wordSearch = new WordSearch_Recursion();
		boolean findWord = wordSearch.searchWord("ABAB");
		System.out.println("VALU "+findWord);
	}

}
