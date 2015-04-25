import java.util.Stack;

public class MatchingParenthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new MatchingParenthesis().isValid("(("));
	}

	public boolean isValid(String s) {
		boolean output = true;
		char[] charArray = s.toCharArray();
		Stack<Character> queue = new Stack<Character>();
		char pop = 'c';
		if(charArray.length <= 1)
			return false;
		for (char c : charArray) {
			if(c == '(' || c == '{' || c == '['){
				queue.push(c);
			}else{ 
				
				if(queue.size()>0){
					pop = queue.pop();
				}else{
					System.out.println("EXTRA");
					output = false;
					break;
				}
				if(c == ')' && pop == '('){
					continue;
				}
				else if(c == '}' && pop == '{'){
					continue;
				}
				else if(c == ']' && pop == '['){
					continue;
				}else{
					//System.out.println("POPPP "+pop+" CC "+c+" count "+count);
					output = false;
					break;
				}
			}
		}
		if(queue.size() >0)
			output = false;
		return output;

	}
}
