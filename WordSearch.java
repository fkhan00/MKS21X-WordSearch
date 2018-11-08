public class WordSearch{
    private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */

    public WordSearch(int rows,int cols)
    {
      data = new char[cols][rows];
      for(int i = 0; i < cols; i ++)
      {
        for(int j = 0; j < rows; j++)
        {
          data[i][j] = '_';
        }
      }
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
        for(int j = 0; j < data[i].length; j++)
        {
          wordList += (" " + data[i][j]);
        }
      }
      return wordList;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col)
    {
      if(row > data[0].length || col > data.length || row < 0 || col < 0)
      {
        throw new IndexOutOfBoundsException("that row or column isn't in this array");
      }
      if(word.length() > data[row].length - col)
      {
        return false;
      }
      int counter = 0;
      for(int i = col; i < word.length(); i ++)
      {
        if(data[row][i] != '_' && data[row][i] != word.charAt(counter))
        {
          return false;
        }
        counter ++;
      }
      counter = 0;
      for(int i = col; i < word.length(); i ++)
      {
        data[row][i] = word.charAt(counter);
        counter ++;
      }
      return true;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col)
    {
      if(row > data[0].length || col > data.length || row < 0 || col < 0)
      {
        throw new IndexOutOfBoundsException("that row or column isn't in this array");
      }
      if(word.length() > data.length - row)
      {
        return false;
      }
      int counter = 0;
      for(int i = row; i < word.length() ; i ++)
      {
        if(data[i][col] != '_' && data[i][col] != word.charAt(counter))
        {
          return false;
        }
        counter ++;
      }
      counter = 0;
      for(int i = row; i < word.length(); i ++)
      {
        data[i][col] = word.charAt(counter);
        counter ++;
      }
      return true;
    }

    public boolean addWordDiagonal(String word, int row, int col)
    {
      if(row > data[0].length || col > data.length || row < 0 || col < 0)
      {
        throw new IndexOutOfBoundsException("that row or column isn't in this array");
      }
      if(word.length() > data.length - row || word.length() > data[row].length - col)
      {
        return false;
      }

      for(int i = 0; i < word.length() ; i ++)
      {
        if(data[row + i][col + i] != '_' && data[row + i][col + i] != word.charAt(i))
        {
          return false;
        }
      }
        for(int i = 0; i < word.length(); i++)
        {
          data[row + i][col + i] = word.charAt(i);
        }
        return true;
    }
}
