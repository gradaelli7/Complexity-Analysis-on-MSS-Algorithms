/* Program that implements four algorithms for solving the "Maximum Subsequence Sum" 
 * problem. A set of integer numbers in one line are read from an ASCII or text file 
 * and the program displays the corresponding execution times for either an individual 
 * algorithm or all four algorithms.
 *
 * Giacomo Radaelli and Parth Mehta
 * 03/22/21
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MSS{
        public static void main(String[] args)
        {//create scanner object
         Scanner kb = new Scanner(System.in);

         //start while loop that runs automatically first time, and
         //continues to run if user inputs "y", if "n" stops running
         boolean run = true;
         while (run)
         {
          System.out.println("Please enter the name of an ASCII or text file that contains a set of integer numbers in one line, separated by a single comma:");
          String input_file = kb.nextLine();

          //initialize file reader
          FileReader input;
          BufferedReader br;

          //begin try catch block, checking for FileNotFound and IO Exceptions
          try
          {
           input = new FileReader(new File(input_file));
           br = new BufferedReader(input);

           //convert line from file into an integer array
           String line = br.readLine();
           String[] line_array = line.split(",");
           int[] new_array = new int[line_array.length];
           for (int i=0;i<line_array.length;i++)
           {
            new_array[i] = Integer.parseInt(line_array[i]);
           }

           System.out.println("Do you want to run an individual algorithm or all four algorithms together (type i for individual or t for together)?:");
           String runType = kb.nextLine();

           if (runType.equalsIgnoreCase("i"))
           {
            System.out.println("Which algorithm would you like to run (1 for MSS1, 2 for MSS2, 3 for MSS3, 4 for MSS4)?:");
            String algType = kb.nextLine();

            //only MSS1
            if (algType.equalsIgnoreCase("1"))
            {
             long startTime = System.nanoTime();
             int finalSum = MSS1(new_array);
             long endTime = System.nanoTime();
             long duration = endTime - startTime;
             long durationMillis = TimeUnit.NANOSECONDS.toMillis(duration);
             System.out.println("MSS1:" + "\n" + "The maximum sum is: " + finalSum + "\n" + "Execution time: " + duration + " nanoseconds (" + durationMillis + " milliseconds)");
            }
            //only MSS2
            else if (algType.equalsIgnoreCase("2"))
            {
             long startTime = System.nanoTime();
             int finalSum = MSS2(new_array);
             long endTime = System.nanoTime();
             long duration = endTime - startTime;
             long durationMillis = TimeUnit.NANOSECONDS.toMillis(duration);
             System.out.println("MSS2:" + "\n" + "The maximum sum is: " + finalSum + "\n" + "Execution time: " + duration + " nanoseconds (" + durationMillis + " milliseconds)");
            }
            //only MSS3
            else if (algType.equalsIgnoreCase("3"))
            {
             long startTime = System.nanoTime();
             int finalSum = MSS3(new_array,0,(new_array.length) - 1);
             long endTime = System.nanoTime();
             long duration = endTime - startTime;
             long durationMillis = TimeUnit.NANOSECONDS.toMillis(duration);
             System.out.println("MSS3:" + "\n" + "The maximum sum is: " + finalSum + "\n" + "Execution time: " + duration + " nanoseconds (" + durationMillis + " milliseconds)");
            }
            //MSS4
            else if (algType.equalsIgnoreCase("4"))
            {
             long startTime = System.nanoTime();
             int finalSum = MSS4(new_array);
             long endTime = System.nanoTime();
             long duration = endTime - startTime;
             long durationMillis = TimeUnit.NANOSECONDS.toMillis(duration);
             System.out.println("MSS4:" + "\n" + "The maximum sum is: " + finalSum + "\n" + "Execution time: " + duration + " nanoseconds (" + durationMillis + " milliseconds)");
            }
           }

           //all four algorithms together
           else if (runType.equalsIgnoreCase("t"))
           {
            long startTime = System.nanoTime();
            int finalSum = MSS1(new_array);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            long durationMillis = TimeUnit.NANOSECONDS.toMillis(duration);
            System.out.println("Algorithm MSS1" + "\n" + "The maximum sum is: " + finalSum + "\n" + "Execution time: " + duration + " nanoseconds (" + durationMillis + " milliseconds)" + "\n\n");

            long startTime2 = System.nanoTime();
            int finalSum2 = MSS2(new_array);
            long endTime2 = System.nanoTime();
            long duration2 = endTime2 - startTime2;
            long durationMillis2 = TimeUnit.NANOSECONDS.toMillis(duration2);
            System.out.println("Algorithm MSS2" + "\n" + "The maximum sum is: " + finalSum2 + "\n" + "Execution time: " + duration2 + " nanoseconds (" + durationMillis2 + " milliseconds)" + "\n\n");

            long startTime3 = System.nanoTime();
            int finalSum3 = MSS3(new_array,0,(new_array.length) - 1);
            long endTime3 = System.nanoTime();
            long duration3 = endTime3 - startTime3;
            long durationMillis3 = TimeUnit.NANOSECONDS.toMillis(duration3);
            System.out.println("Algorithm MSS3" + "\n" + "The maximum sum is: " + finalSum3 + "\n" + "Execution time: " + duration3 + " nanoseconds (" + durationMillis3 + " milliseconds)" + "\n\n");

            long startTime4 = System.nanoTime();
            int finalSum4 = MSS4(new_array);
            long endTime4 = System.nanoTime();
            long duration4 = endTime4 - startTime4;
            long durationMillis4 = TimeUnit.NANOSECONDS.toMillis(duration4);
            System.out.println("Algorithm MSS4" + "\n" + "The maximum sum is: " + finalSum4 + "\n" + "Execution time: " + duration4 + " nanoseconds (" + durationMillis4 + " milliseconds)" + "\n\n");
           }
           System.out.println("Do you want to run the program again (y for yes and n for no):");
           String rerun = kb.nextLine();

           if (rerun.equalsIgnoreCase("n"))
           {
            run = false;
           }
          }
          catch(FileNotFoundException e)
          {
           System.out.println(e.getMessage());
          }
          catch(IOException e)
          {
           System.out.println(e.getMessage());
          }
         }
        }

         //MSS1 method
         public static int MSS1(int[] numbers)
         {
          int maxSum = 0;
          for (int i = 0; i < numbers.length; i++) //loop through length of array 
          {
           for (int j = i; j < numbers.length; j++) //loop from j to length of array n times where n is length of array 
           {
            int sum = 0;
            for (int k = i; k < j; k++) //add each element from array, one at a time, to sum
            {
             sum += numbers[k];
            }
            if (sum > maxSum) //update maxSum if sum bigger than maxSum
            {
             maxSum = sum;
            }
           }
          }
          return maxSum;
         }

         //MSS2 method
         public static int MSS2(int[] numbers)
         {
          int maxSum = 0;
          for (int i = 0; i < numbers.length; i++) //loop through length of array
          {
           int sum = 0;
           for (int j = i; j < numbers.length; j++) //add each element from array, one at a time, to sum and update maxSum if sum bigger than maxSum
           {
            sum += numbers[j];
            if (sum > maxSum)
            {
             maxSum = sum;
            }
           }
          }
          return maxSum;
         }

         //MSS3 method
         public static int MSS3(int[] numbers, int start, int stop)
         {//base case: only one element
          if (start == stop)
          {
           return Math.max(numbers[start],0);
          }
          int midpt = (start + stop)/2; //find middle of array
          int rightMaxSum = MSS3(numbers,midpt+1,stop); //calculate rightMaxSum recursively
          int leftMaxSum = MSS3(numbers,start,midpt); //calculate leftMaxSum recursively

          //calculate maxLeftBoundSum, going from middle of array to start of the array
          int leftSum = 0;
          int maxLeftBoundSum = 0;
          for (int i=midpt; i>=0 ; i--)
          {
           leftSum += numbers[i];
           if (leftSum > maxLeftBoundSum)
           {
            maxLeftBoundSum = leftSum;
           }
          }

          //calculate maxRightBoundSum, going from middle of array to end of array
          int rightSum = 0;
          int maxRightBoundSum = 0;
          for (int i=midpt+1; i<stop; i++)
          {
           rightSum += numbers[i];
           if (rightSum > maxRightBoundSum)
           {
            maxRightBoundSum = rightSum;
           }
          }

          int maxSum = maxRightBoundSum + maxLeftBoundSum;

          return Math.max(Math.max(rightMaxSum,leftMaxSum),maxSum);
         }

         //MSS4 method
         public static int MSS4(int[] numbers)
         {
          int maxSum = 0;
          int sum = 0;
          for (int j = 0; j < numbers.length; j++) //loop through length of array
          {
           sum += numbers[j]; //add element to sum
           if (sum > maxSum) //update maxSum
           {
            maxSum = sum;
           }
           else //get rid of past if sum is negative
           {
            if (sum < 0)
            {
             sum = 0;
            }
           }
          }
          return maxSum;
         }
}

