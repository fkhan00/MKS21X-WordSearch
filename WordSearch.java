import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class WordSearch{
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd;
    private ArrayList<String> wordsAdded;
    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols, String fileName)
    {
      if(rows < 0 || cols < 0)
      {
        throw new IllegalArgumentException("No negative inputs");
      }
      try
      {
      File document = new File(fileName);
      Scanner scan = new Scanner(document);
      while(scan.hasNext())
      {
        String line = scan.nextLine();
        wordsToAdd.add(line);
      }
    }
    catch(FileNotFoundException error)
    {
      System.out.println("File not found: " + fileName);
      System.exit(1);
    }
      data = new char[cols][rows];
      for(int i = 0; i < cols; i ++)
      {
        for(int j = 0; j < rows; j++)
        {
          data[i][j] = '_';
        }
      }
      Random rand = new Random();
      seed = rand.nextInt();
      randgen = new Random(seed);
      addAllWords();
    }
    public WordSearch(int rows, int cols, String fileName, int randSeed)
    {
      if(rows < 0 || cols < 0)
      {
        throw new IllegalArgumentException("No negative inputs");
      }
      try
      {
      File document = new File(fileName);
      Scanner scan = new Scanner(document);
      while(scan.hasNext())
      {
        String line = scan.nextLine();
        wordsToAdd.add(line);
      }
    }
    catch(FileNotFoundException error)
    {
      System.out.println("File not found: " + fileName);
      System.exit(1);
    }
      data = new char[cols][rows];
      for(int i = 0; i < cols; i ++)
      {
        for(int j = 0; j < rows; j++)
        {
          data[i][j] = '_';
        }
      }
      seed = randSeed;
      randgen = new Random(seed);
      addAllWords();
    }
    private boolean addAllWords()
    {
      Random rng = new Random(123);
      for(int i = 0; i < wordsToAdd.size(); i++)
      {
        int Rincr = rng.nextInt() % 2;
        int Cincr = rng.nextInt() % 2;
        int index  = rng.nextInt() % wordsToAdd.size();
        String work = wordsToAdd.get(index);
        wordsToAdd.remove(index);
        int size = data.length * data[0].length;
        while(addWord(wordsToAdd.get(index), rng.nextInt() % data[0].length, rng.nextInt() % data.length,
         Rincr, Cincr) && size > data.length * data[0].length)
        {
          size -= 1;
        }
        wordsAdded.add(work);
      }
      return wordsToAdd.size() == 0;
    }
    /**Set all values in the WordSearch to underscores'_'*/
    private void clear()
    {
    for(int i = 0; i < data.length; i ++)
    {
      for(int j = 0; j < data[i].length; j++)
      {
        data[i][j] = '_';
    }
    }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString()
    {
      String wordList = "";
      for(int i = 0; i < data.length; i++)
      {
        wordList += "\n";
        wordList += "|";
        for(int j = 0; j < data[i].length; j++)
        {
          wordList += (" " + data[i][j]);
        }
        wordList += "|";
      }
      return wordList;
    }
    public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement)
    {
      if(rowIncrement == 0 && colIncrement == 0)
      {
        return false;
      }

      if(rowIncrement == 1 && colIncrement == 1)
      {
        if(row > data.length || col > data[0].length || row < 0 || col < 0)
        {
          return false;
        }
      }
      else if(word.length() > data.length - row && word.length() > data[row].length - col)
      {
        return false;
      }

      for(int i = 0; i < word.length() ; i ++)
      {
        if(data[row + i * rowIncrement][col + i * colIncrement] != '_' && data[row + i * rowIncrement][col + i * colIncrement] != word.charAt(i))
        {
          return false;
        }
      }
        for(int i = 0; i < word.length(); i++)
        {
          data[row + i * rowIncrement ][col + i * colIncrement ] = word.charAt(i);
        }
        return true;

    }
}
