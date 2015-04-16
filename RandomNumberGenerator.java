
public class RandomNumberGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String input = "1234 5678 123 12345 9876";
		String input = "3 2 5 1 7";
		String[] inarr = input.split(" ");
		if(inarr.length==5){
			int A = Integer.parseInt(inarr[0]);
			int B = Integer.parseInt(inarr[1]);
			int X = Integer.parseInt(inarr[2]);
			int K = Integer.parseInt(inarr[3]);
			int M = Integer.parseInt(inarr[4]);
			//(A * X + B) % M
			for(int i=0;i<K+4;i++){
				if(i >= K-1)
				System.out.println(X);
				X = (A * X + B) % M;
				
			}
		}
	}

}
