/**
 * Created by achoudhary on 29/12/2015.
 */
public class RegularExpressionMatching {


    public boolean isMatch(String s, String p) {

        if(p.length() == 0)
            return s.length() == 0;

        if(p.length() == 1 || p.charAt(1) != '*'){
            if(s.length() < 1 || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))){
                return false;
            }
            return isMatch(s.substring(1),p.substring(1));
        }else{
            //current index character will be *
            //now traverse through each element and check
            //since this loop is checking the current character is * , so to pass or match any regex
            //it must check the current chracter is either . or exactly matching the character
            int len = s.length();
            int i = -1;
            while(i < len && (i < 0 || (p.charAt(0) == '.') || p.charAt(0) == s.charAt(i))){
                //if matches happens pass the next character and pattern -> next -> next
                //so if [isMatch("abccc",".*bc*")]
                //then now it will pass b and c for validation
                if(isMatch(s.substring(i+1),p.substring(2))){
                    return true;
                }
                i++;
            }
            return false;
        }


    }

    public static void main(String[] args) {
        System.out.println("Regex matching with Recursion  : " + new RegularExpressionMatching().isMatch("aab", "c*a*b"));
    }
}
