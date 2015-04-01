//http://www.careercup.com/question?id=21263687
public class FrequencyOfIntegerUsingCountSort
{

	public static void main(String[] args)
	{
		int[] input = {1,5,3,7,4,5,6,8,9,5,4,10,4};
		int n = input.length;
		for(int i=0;i<input.length;i++){
			input[input[i] % n] += n;
		}
		for(int i=0;i<n;i++) {
			int frequency = input[i]/n;
			int element=i;
			System.out.println("Item "+element+", Frequency = "+ frequency );
		  }
		
	}

}
