import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class Driver
{
  public static void main(String[] args)
  {
    WordSearch testCase2 = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
    System.out.println(testCase2.toString());
  }
}
