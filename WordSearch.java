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
    public WordSearch(int rows, int cols, String fileName, int randSeed, String key)
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
      if (! key.equals("key"))
      {
        addRandoms();
    }
    }
    private boolean addAllWords()
    {
      int counter = wordsToAdd.size();
      for(int i = 0; i < counter; i++)
      {
        if(wordsToAdd.size() == 0)
        {
          break;
        }
        int Rincr = Math.abs(randgen.nextInt() % 2);
        int Cincr = Math.abs(randgen.nextInt() % 2);
        int index = Math.abs(randgen.nextInt() % wordsToAdd.size());
        String work = wordsToAdd.get(index);
        for(int size = 0; size < 500; size++)
        {
        if(addWord(work.toUpperCase(), Math.abs(index % data[0].length),
         Math.abs(randgen.nextInt() % data.length), Rincr, Cincr))
         {
           wordsToAdd.remove(work);
           wordsAdded.add(work);
           break;
         }
      }
      wordsToAdd.remove(work);
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
      wordList += ("Seed Number " + seed);
      return wordList;
    }
    public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement)
    {
      if(rowIncrement == 0 && colIncrement == 0)
      {
        return false;
      }
      if(rowIncrement < 0 || colIncrement < 0)
      {
        word = Reverse(word);
      }
      if((word.length() > data.length - row) || (word.length() > data[row].length - col))
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
      int index;
      for(int i = 0; i < data.length; i++)
      {
        for(int j = 0; j < data[0].length; j++)
        {
          if(data[i][j] == '_')
          {
            index = Math.abs(randgen.nextInt() % 26);
            data[i][j] = alphabet.charAt(index);
          }
        }
      }
    }
    public String Reverse(String words)
    {
      String output = "";
      for(int i = words.length() - 1; i >= 0; i++)
      {
        output += words.substring(i, i + 1);
      }
      return output;
    }
}
