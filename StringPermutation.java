import java.util.ArrayList;
import java.util.List;

//read recursive version of permutation from https://www.youtube.com/watch?v=MQcwxQK2DPA

public class StringPermutation
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String input = "abhiSheK";
		List<String> permute = StringPermutation.permute(input);
		for (String string : permute)
		{
			System.out.println("--> "+string);
		}
	}

	public static List<String> permute(String str){
		ArrayList<String> permutations = new ArrayList<String>();
		if(str == null){
			return null;
		}
		else if(str.length() == 0){
			permutations.add("");
			return permutations;
		}
		
		
		char ch = str.charAt(0);
		String remainder = str.substring(1);
		List<String> wordList = permute(remainder);	
		
		for (String word : wordList)
		{
			for(int i=0;i<=word.length();i++){
				permutations.add(insertCharAt(word,ch,i));
			}
		}
		return permutations;
	}
	
	public static String insertCharAt(String word, char ch, int i){
		String start = word.substring(0,i);
		String end = word.substring(i);
		return start + ch + end;
	}
}
