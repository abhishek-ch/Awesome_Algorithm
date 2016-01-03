import java.util.Stack;

/**
 * Created by abc on 03/01/2016.
 *
 * Use stack to track each starting parenthesis and pop out when closing comes
 * 1. If there is a situation when stack is empty it does mean there is either no starting parenthesis
 * so all invalid location , so maintain a pointer till we don't reach a valid index where a starting
 * parenthesis is available.
 *
 * 2. So any situation when we get a closing index , we can find the last longest possible pair
 * is with current index - last perfect index.
 *
 * 3. In any case if stack is non empty but we are popping value it does mean we can find the longest
 * pair till the index of value present in the head of Stack because head of the stack is the last
 * known starting parenthesis which couldn't get any closing yet, so get the longest streak from
 * current index - stack.peek()
 *
 *
 * @link https://leetcode.com/problems/longest-valid-parentheses/
 */

public class LongestValidParenthesis {


    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        int lastEmptyState = -1;
        for(int i=0;i<s.length();i++){
            char curr = s.charAt(i);
            if(curr == '('){
                stack.add(i); // add the current index of finding starting character
            }else{
                //means a closing character
                if(stack.isEmpty()){
                    //if we still finds the stack is empty so we found an invalid closing
                    //parenthesis so no point of tracking just make it sure to count the
                    //longest streak from this point onwwards because it broke the chain
                    lastEmptyState = i;
                }else{
                    stack.pop();
                    if(stack.empty()){
                        //valid case we finished all the track till now , so get the
                        result = Math.max((i-lastEmptyState),result);
                    }else{
                        result = Math.max((i - stack.peek()),result);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LongestValidParenthesis().longestValidParentheses("((()()((())))(()"));
    }
}
