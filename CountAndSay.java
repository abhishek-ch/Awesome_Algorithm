
public class CountAndSay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String countAndSay = new CountAndSay().countAndSay(1);
		System.out.println(countAndSay);
	}

	
	//count & say
	 public String countAndSay(int n) {
	        String output = "";
	        int sum = 0;
	        char[] charArray = (""+n).toCharArray();
	        char prev = charArray[0];
	        for (int i = 0; i < charArray.length; i++) {
				if(charArray[i] == prev){
					sum++;
				}else{
					output += ""+sum+""+prev;
					prev = charArray[i];
					sum = 1;
				}
				
			}
	        output += ""+sum+""+prev;
	        return output;
	    }
}
