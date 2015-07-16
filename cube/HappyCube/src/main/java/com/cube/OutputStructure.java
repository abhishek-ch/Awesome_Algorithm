package com.cube;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cube.helper.Constant;
import com.cube.model.Cube;

public class OutputStructure
{


   private int[][] outputArr = new int[Constant.ROW][Constant.COLUMN];


   public void updateOutput(int[][] input, int row, int column)
   {
      for (int i = 0; i < 5; i++)
      {
         for (int j = 0; j < 5; j++)
         {
            // System.out.printf("%2d", a[i][j]);
            outputArr[row + i][column + j] = input[i][j];
         }
         System.out.println();
      }
   }


   public int[][] getOutputArr()
   {
      return outputArr;
   }


   public void print()
   {
      System.out.println("Printing...");
      String output = "";
      for (int i = 0; i < Constant.ROW; i++)
      {
         for (int j = 0; j < Constant.COLUMN; j++)
         {
            output += outputArr[i][j] == 0 ? " " : "o";
         }
         output += "\n";
      }
      // System.out.println(output);
      console(output);
   }


   private void console(String message)
   {
      DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
      Date date = new Date();
      PrintWriter out;
      try
      {
         out = new PrintWriter(new FileWriter(dateFormat.format(date)
               + ".txt", true), true);
      }
      catch (IOException e)
      {

         throw new RuntimeException(e);

      }
      out.write(message);
      out.close();
   }


   public void betterPrint(List<Cube> list)
   {
      for (Cube cube : list)
      {
         int[][] element = cube.getElement();
         int column = cube.getStartColumn();
         int row = cube.getStartIndex();
         int irow = 0;
         int icol = 0;
         for (int i = row; i < row + 5; i++)
         {
            for (int j = column; j < column + 5; j++)
            {
               outputArr[i][j] = element[irow][icol++];
            }
            icol = 0;
            irow++;
         }
      }
      print();
   }

}
