public class Grid {
  Block[][] block;
  private final int COLS;
  private final int ROWS;
  private int score;
  
  public Grid(int cols, int rows) {
    COLS = cols;
    ROWS = rows;
    block = new Block[COLS][ROWS];
    initBlocks();  // initializes all blocks to empty blocks
  }
    
  public Block getBlock(int col, int row) {
    return block[col][row];
  }
  
  public void setBlock(int col, int row, int value, boolean changed) {
    block[col][row] = new Block(value, changed);
  }
  
  public void setBlock(int col, int row, int value) {
    setBlock(col, row, value, false);
  }
  
  public void setBlock(int col, int row) {
    setBlock(col, row, 0, false);
  }
  
  public void setBlock(int col, int row, Block b) {
    block[col][row] = b;
  }
  
  public void initBlocks() {
    for (int y = 0; y < block.length; y++)
      for (int x = 0; x < block[y].length; x++)
        setBlock(x, y, new Block());
  }
  
  public boolean isValid(int col, int row) {
    if (!(col >= 0 && col < COLS))
      return false;
    if (!(row >= 0 && row < ROWS))
      return false;
    return true;
  }
  
  public void swap(int col1, int row1, int col2, int row2) {
    Block tmp = getBlock(col1, row1);
    setBlock(col1, row1, getBlock(col2, row2));
    setBlock(col2, row2, tmp);
  }
  
  public boolean canMerge(int col1, int row1, int col2, int row2) {
    return isValid(col1, row1) && 
           isValid(col2, row2) && 
           getBlock(col1, row1).getValue() == getBlock(col2, row2).getValue();
  }
  
  public void clearChangedFlags() {
    for(int col = 0; col < COLS; col++) {
      for(int row = 0; row < ROWS; row++) {
        block[col][row].setChanged(false);
      }
    }
  }
 
  // Is there an open space on the grid to place a new block?
  public boolean canPlaceBlock() {
    return getEmptyLocations().size() > 0;
  }
  
  public ArrayList<Location> getEmptyLocations() {
    // Put all locations that are currently empty into locs
    ArrayList<Location> emptyLocs = new ArrayList<Location>();
    for (int y = 0; y < ROWS; y++)
      for (int x = 0; x < COLS; x++)
        if (getBlock(x, y).isEmpty())
          emptyLocs.add(new Location(x, y));
          
    return emptyLocs;
  }
  
  public Location selectLocation(ArrayList<Location> locs) {
    return locs.get((int) (Math.random()*locs.size()));
  }
  
  // Randomly select an open location to place a block.
  public void placeBlock() {
    Location randomLocation = selectLocation(getEmptyLocations());
    int blockValue = (int) (Math.random()*8) == 0 ? 4 : 2;
    setBlock(randomLocation.getCol(), randomLocation.getRow(), new Block(blockValue, false));
  }
  
  // Are there any adjacent blocks that contain the same value?
  public boolean hasCombinableNeighbors() {
    for (int y = 0; y < ROWS; y++)
      for (int x = 0; x < COLS; x++) {
        if (canMerge(x-1, y, x, y) || 
            canMerge(x+1, y, x, y) ||
            canMerge(x, y-1, x, y) ||
            canMerge(x, y+1, x, y))
            return true; 
      }
      
    return false;
  }
   
  // Notice how an enum can be used as a data type
  //
  // This is called ) method  the KeyEvents tab
  public boolean someBlockCanMoveInDirection(DIR dir) {
    for (int y = 0; y < ROWS; y++)
      for (int x = 0; x < COLS; x++) {
        if (!getBlock(x, y).isEmpty()) {          
          if (dir == DIR.WEST && (canMerge(x-1, y, x, y) || (isValid(x-1, y) && getBlock(x-1, y).isEmpty()))) {
            return true;
          }
          else if (dir == DIR.EAST && (canMerge(x+1, y, x, y) || (isValid(x+1, y) && getBlock(x+1, y).isEmpty()))) {
            return true;
          }
          else if (dir == DIR.NORTH && (canMerge(x, y-1, x, y) || (isValid(x, y-1) && getBlock(x, y-1).isEmpty()))) {
            return true;
          }
   
          else if (dir == DIR.SOUTH && (canMerge(x, y+1, x, y) || (isValid(x, y+1) && getBlock(x, y+1).isEmpty()))) {
            return true; 
          }
        }
      }
   
    return false;
 
  }
  
  // Computes the number of points that the player has scored
  public void computeScore() {
    score = 0;
    for (int y = 0; y < ROWS; y++)
      for (int x = 0; x < COLS; x++)
        score += getBlock(x, y).getValue();
  }
  
  public int getScore() {
    return score;
  }
  
  public void showScore() {
      textFont(scoreFont);
      fill(#FFFFFF);
      text(getScore(), width/2, SCORE_Y_OFFSET);
      textFont(blockFont);
  }
  
  public void showBlocks() {
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        Block b = block[col][row];
        if (!b.isEmpty()) {
          float adjustment = (log(b.getValue()) / log(2)) - 1;
          fill(color(242 , 241 - 8*adjustment, 239 - 8*adjustment));
          rect(GRID_X_OFFSET + (BLOCK_SIZE + BLOCK_MARGIN)*col, GRID_Y_OFFSET + (BLOCK_SIZE + BLOCK_MARGIN)*row, BLOCK_SIZE, BLOCK_SIZE, BLOCK_RADIUS);
          fill(color(108, 122, 137));
          text(str(b.getValue()), GRID_X_OFFSET + (BLOCK_SIZE + BLOCK_MARGIN)*col + BLOCK_SIZE/2, GRID_Y_OFFSET + (BLOCK_SIZE + BLOCK_MARGIN)*row + BLOCK_SIZE/2 - Y_TEXT_OFFSET);
        } else {
          fill(BLANK_COLOR);
          rect(GRID_X_OFFSET + (BLOCK_SIZE + BLOCK_MARGIN)*col, GRID_Y_OFFSET + (BLOCK_SIZE + BLOCK_MARGIN)*row, BLOCK_SIZE, BLOCK_SIZE, BLOCK_RADIUS);
        }
      }
    }
  }
  
  // Copy the contents of another grid to this one
  public void gridCopy(Grid other) {
    for (int y = 0; y < ROWS; y++)
      for (int x = 0; x < COLS; x++)
        setBlock(x, y, other.getBlock(x, y));
  }
  
  public boolean isGameOver() {
    return !someBlockCanMoveInDirection(DIR.NORTH) && 
           !someBlockCanMoveInDirection(DIR.SOUTH) &&
           !someBlockCanMoveInDirection(DIR.WEST) &&
           !someBlockCanMoveInDirection(DIR.EAST);
  }
  
  public void showGameOver() {
    fill(#0000BB);
    text("GAME OVER", GRID_X_OFFSET + 2*BLOCK_SIZE + 15, GRID_Y_OFFSET + 2*BLOCK_SIZE + 15);
  }
  
  public String toString() {
    String str = "";
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        str += block[col][row].getValue() + " ";
      }
      str += "\n";   // "\n" is a newline character
    }
    return str;
  }
}