import java.util.Arrays;


public class LCS {

	public static void main(String[] args) {
		
		//String a = " ABHISHEK";
		//String b = " BOAHSEK";
		
		String a = " CHOUDHRAYABHISHEKAMAZING";
		String b = " RACHELABHISHEKGOINGALONE";
		
		
		char[] row = a.toCharArray();
		char[] col = b.toCharArray();
		
		int[][] table = new int[row.length][col.length];
		for(int i=1;i<row.length;i++){
			char elem = row[i];
			for(int j =1;j<col.length;j++){
				if(elem != col[j]){
					int prev1 = table[i][j-1];
					int prev2 = table[i-1][j];
					
					table[i][j] = prev1 >prev2 ? prev1:prev2 ;
				}else if(elem == col[j]){
					table[i][j] = table[i-1][j-1]+1;
				}
				
				
			}
					
		}
		
		 for (int[] arr : table) {
	            System.out.println(Arrays.toString(arr));
	        }
		
		 int i = row.length-1;
		 int j = col.length-1;
		 int pointer = table[i][j];
		 String output = "";
		 while(i > 0 && j > 0){
			 if(pointer == table[i][j-1]){
				 j--;
			 }else if(pointer == table[i-1][j]){
				 i--;
			 } else{
				 output = output+row[i];
				 i--;
				 j--;
				 
			 }
			 pointer = table[i][j];
		 }
		 
		 System.out.println(output+" <- output -> "+new StringBuilder(output).reverse().toString());
	}

}
