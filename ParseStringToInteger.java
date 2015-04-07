
public class ParseStringToInteger
{

	public static void main(String[] args)
	{
		System.out.println(new ParseStringToInteger().atoi("-2147483648"));
	}

	
	
    public int atoi(String str) {
       	
       	str = str.trim();
		double output = 0;
		int counter = 0;
		boolean suffix = false;
		if(str.length() <= 0){
		    return 0;
		}
		if(str.charAt(0) == '-'){
			suffix = true;
			counter++;
		}else if(str.charAt(0) == '+'){
			counter++;
		}
		for(int i=counter;i<str.length();i++){
			char ch = str.charAt(i);
		if(ch >= '0' && ch <= '9'){
				output = output*10 +(ch-'0');
				counter++;
			}else{
			    break;
			}
		}
		if(suffix){
			output = -output;
		}
		if(output > Integer.MAX_VALUE){
			output = Integer.MAX_VALUE;
		}
		if(output <= Integer.MIN_VALUE){
			output = Integer.MIN_VALUE;
		}
		
		return (int) output;
    
    
    
    }
	
}
