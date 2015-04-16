
public class CheckingCable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] input = {"3 5","make 1 2 1000","check 1 3 500","make 3 2 2000","check 1 3 500","check 1 3 1500"};
		
		if(input.length > 1){
			String[] basic = input[0].split(" ");
			int N = Integer.parseInt(basic[0]);
			int instructions = Integer.parseInt(basic[1]);
			
			int[][] arr = new int[N][N];
			for(int i=1;i<instructions;i++){
				String[] values = input[i].split(" ");
				int row = Integer.parseInt(values[1])-1;
				int col = Integer.parseInt(values[2])-1;
				int sum = Integer.parseInt(values[3]);
				if(!values[0].equals("make")){
					String output = "NO";
					//check exact column value
					int exactcolumn = arr[row][col];
					if(exactcolumn != 0){
						if(exactcolumn >= sum){
							output = "YES";
						}else{
							output = "NO";
						}
					}else if(i < instructions-1){
						
						for(int j=0;j<instructions;j++){
							if(arr[i][col] >= sum){ //particular row has that link
								//if that link has reference with the expected row
								if(arr[row][i] > 0){
									
								}
							}
						}
						
						
						for(int j=i+1;j<instructions;j++){
							//if particular row has some link with other column
							if(arr[row][j] > 0){
								//if link is there so A-B-C can be made
								if(arr[j][col] >= sum){
									output = "YES";
								}
							}
						}
					}
					System.out.println(output);
				}
				//A-B = B-A
				arr[row][col] = sum;
				arr[col][row] = sum;
				
				
				for(int k =0 ; k<arr.length;k++){
					for(int p=0;p<arr.length;p++){
						System.out.print(arr[k][p]+"\t");
					}
					
					System.out.println();
				}
				System.out.println("--------------------------");
			}
		}
						
						
						
						
						
						
						
						
	}

}
