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
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      try
      {
      File document = new File(fileName);
      Scanner scan = new Scanner(document);
      while(scan.hasNextLine())
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
      randgen = new Random();
      addAllWords();
      addRandoms();
    }
    public WordSearch(int rows, int cols, String fileName, int randSeed)
    {
      if(rows < 0 || cols < 0)
      {
        throw new IllegalArgumentException("No negative inputs");
      }
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
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
      addRandoms();
    }
    private boolean addAllWords()
    {
      Random rng;
      int size;
      int counter = wordsToAdd.size();
      for(int i = 0; i < counter; i++)
      {
        if(wordsToAdd.size() == 0)
        {
          break;
        }
        rng = new Random();
        int Rincr = Math.abs(rng.nextInt() % 2);
        int Cincr = Math.abs(rng.nextInt() % 2);
        int index = Math.abs(rng.nextInt() % wordsToAdd.size());
        String work = wordsToAdd.get(index);
        size = 0;
        while((size < 500) &&
        !addWord(work.toUpperCase(), Math.abs(rng.nextInt() % data[0].length),
        Math.abs(rng.nextInt() % data.length), Rincr, Cincr))
        {
          size ++;
        }
        wordsToAdd.remove(work);
        wordsAdded.add(work);
        counter ++;
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
      wordList += "\nWords: ";
      for(int i = 0; i < wordsAdded.size(); i++)
      {
        wordList += (wordsAdded.get(i) + " ");
      }
      return wordList;
    }
    public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement)
    {
      if(rowIncrement == 0 && colIncrement == 0)
      {
        return false;
      }

      if(((word.length() > data.length - row) && (Math.abs(rowIncrement) == 1))
       || ((word.length() > data[row].length - col) && Math.abs(colIncrement) == 1))
      {
        return false;
      }
      for(int i = 0; i < word.length(); i ++)
      {
        if(data[row + i * rowIncrement][col + i * colIncrement] != '_'
        && data[row + i * rowIncrement][col + i * colIncrement] != word.charAt(i))
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
    public void addRandoms()
    {
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      Random RandX = new Random();
      int index;
      for(int i = 0; i < data.length; i++)
      {
        for(int j = 0; j < data[0].length; j++)
        {
          if(data[i][j] == '_')
          {
            index = Math.abs(RandX.nextInt() % 26);
            data[i][j] = alphabet.charAt(index);
          }
        }
      }
    }
}
