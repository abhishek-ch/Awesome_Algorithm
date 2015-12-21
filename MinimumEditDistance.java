/**
 * Created by achoudhary on 21/12/2015.
 */
import java.util.*;

public class MinimumEditDistance {


    /**
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minSteps(String word1, String word2){
        char[] charFirst = word1.toCharArray(); //Ctrl+Alt+V
        char[] charSec = word2.toCharArray();

        int[][] table = new int[charSec.length+1][charFirst.length+1]; //column representation is first

        //creating the first skeleton row and column filling
        for(int i=0;i<= charFirst.length;i++){
            table[0][i] = i;
        }

        for(int i=0;i<= charSec.length;i++){
            table[i][0] = i;
        }


        //make sure to update the array with one increment
        //if we keep i it actually means previous row as (i+1) is the main
        // row to be tracked as we are supposed to ignore first row and first
        //column with default value
        for(int i=0;i<charSec.length;i++){
            for(int j=0;j<charFirst.length;j++){
                if(charSec[i] == charFirst[j]){
                    table[i+1][j+1] = table[i][j];
                }else{
                    table[i+1][j+1] = Math.min(Math.min(table[i][j],table[i][j+1]),table[i+1][j])+1;
                }
            }
        }

        printGrid(table);
        return table[table.length-1][table[0].length-1];
    }

    public static void main(String[] args){
       System.out.println("Minimum Step Required to Convert a String to another :=> "+new MinimumEditDistance().minSteps("abcdef","azced")) ;
    }

    public void printGrid(int[][] input)
    {
        for(int i = 0; i < input.length; i++)
        {
            for(int j = 0; j < input[0].length; j++)
            {
                System.out.printf("%5d ", input[i][j]);
            }
            System.out.println();
        }
    }
}
