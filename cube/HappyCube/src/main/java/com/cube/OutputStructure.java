package com.cube;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cube.helper.Constant;
import com.cube.model.Cube;

/**
 * This class is responsible to handle entire Output Handling of the Happy Cube
 * 
 * @author abc
 * 
 */
public class OutputStructure
{


   private int[][] outputArr = new int[Constant.ROW][Constant.COLUMN];

   private static Set<String> uniquePatterns = new HashSet<>();

   String colorBlocks;

   boolean printRequired;

   boolean filePrintRequired;


   /**
    * 
    * @param colorBlocks
    *           name of the Color Block
    * @param printRequired
    *           is printing in console required
    * @param filePrintRequired
    *           is content should be print in file
    */
   public OutputStructure(String colorBlocks, boolean printRequired, boolean filePrintRequired)
   {
      this.colorBlocks = colorBlocks;
      this.printRequired = printRequired;
      this.filePrintRequired = filePrintRequired;
      File file = new File(colorBlocks);
      file.mkdirs();
   }


   public void reset()
   {
      outputArr = new int[Constant.ROW][Constant.COLUMN];
   }


   public void updateOutput(int[][] input, int row, int column)
   {
      for (int i = 0; i < 5; i++)
      {
         for (int j = 0; j < 5; j++)
         {
            outputArr[row + i][column + j] = input[i][j];
         }
         // System.out.println();
      }
   }


   public int[][] getOutputArr()
   {
      return outputArr;
   }


   public void print()
   {
      String output = "";
      for (int i = 0; i < Constant.ROW; i++)
      {
         for (int j = 0; j < Constant.COLUMN; j++)
         {
            output += outputArr[i][j] == 0 ? " " : "o";
         }
         output += "\n";
      }
      if (uniquePatterns.add(output))
      {
         if (printRequired)
         {
            System.out.println(output);
         }
         if (filePrintRequired)
         {
            console(output);
         }
      }
   }


   /**
    * print console output in file
    * 
    * @param message
    */
   private void console(String message)
   {
      DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
      Date date = new Date();
      PrintWriter out;
      try
      {
         out = new PrintWriter(new FileWriter(colorBlocks + "/" + dateFormat.format(date) + ".txt", true), true);
      }
      catch (IOException e)
      {

         throw new RuntimeException(e);

      }
      out.write(message);
      out.close();
   }


   /**
    * Prints all available unique cube
    * 
    * @param list
    * @param unfold
    */
   public void betterPrint(List<Cube> list, boolean unfold)
   {
      boolean invalid = false;
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
               if (!unfold && outputArr[i][j] == 1)
               {
                  invalid = true;
                  break;
               }
               outputArr[i][j] = element[irow][icol++];
            }
            icol = 0;
            irow++;
         }
      }
      if (!invalid)
         print();
   }

}
