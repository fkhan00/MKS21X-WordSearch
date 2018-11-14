import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class Driver
{
  public static void main(String[] args)
  {
    try
    {
    if(args.length < 3 || args.length > 5)
    {
      throw new IndexOutOfBoundsException("\nArguments should be given number of rows, number of columns, \n, document file, and optionally seed number");
    }
    if(args.length == 3)
    {
    WordSearch testCase2 = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
    System.out.println(testCase2.toString());
  }
  if (args.length == 4)
  {
    WordSearch testCase3 = new WordSearch(Integer.parseInt(args[0]),
     Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), "");
    System.out.println(testCase3.toString());
  }
  if (args.length == 5)
  {
    WordSearch testCase3 = new WordSearch(Integer.parseInt(args[0]),
     Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), args[4]);
    System.out.println(testCase3.toString());
  }
  }
  catch (IllegalArgumentException error)
  {
    System.out.println("You gave a negative input for either row or column");
  }
}

}
