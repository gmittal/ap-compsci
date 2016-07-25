// Conway's Game of Life
// Written by Gautam Mittal
// AP Computer Science 2015-2016

/*
  INSTRUCTIONS:
  Draw some dots on the grid;
  Press any key to start the game logic.
  Watch the magic unfold.
  Press any key to stop the magic.
  Repeat.
*/

int WIDTH = 400;
int HEIGHT = 400;
int CELL_WIDTH = 20;
int CELL_HEIGHT = 20;

// grid data structure
int[][] GRID = new int[WIDTH][HEIGHT];
boolean running = false;

void setup() 
{
  size(400,400);
  background(0);
  frameRate(60);
  
  // initialize the grid with zeros (if only numpy java)
  for (int i = 0; i < WIDTH/10; i++) {
    for (int j = 0; j < HEIGHT/10; j++) {
       GRID[i][j] = 0; 
    }
  }
}


void draw() 
{
  if (running == true) {
    conway();
  }
  
  drawGrid();
}

void conway()
{
  for (int i = 0; i < WIDTH/10; i++) {
     for (int j = 0; j < HEIGHT/10; j++) {
       int currentState = GRID[i][j];
       
       int neighborCount = 0;
       neighborCount += addNeighbor(i-1, j-1);
       neighborCount += addNeighbor(i-1, j);
       neighborCount += addNeighbor(i-1, j+1);
       neighborCount += addNeighbor(i, j-1);
       neighborCount += addNeighbor(i, j+1);
       neighborCount += addNeighbor(i+1, j-1);
       neighborCount += addNeighbor(i+1, j);
       neighborCount += addNeighbor(i+1, j+1);
       
       if (currentState == 0) {
          if (neighborCount == 3) {
             GRID[i][j] = 1; 
          }
       } else if (currentState == 1) {
          if (neighborCount <= 1) {
             GRID[i][j] = 0; 
          }
          
          if (neighborCount >= 4) {
             GRID[i][j] = 0; 
          }
          
          if (neighborCount == 2 || neighborCount == 3) {
             GRID[i][j] = currentState; 
          }
       }
       
     }
  }
}


int addNeighbor(int row, int col) {
  int n = 0;
   try {
     if (GRID[row][col] == 1) {
       n = 1;
     }
   } catch (ArrayIndexOutOfBoundsException e) {
     // do nothing
   }
   
   return n;
}


// draw the grid display
void drawGrid() 
{
  // No fourth argument means 100% opacity.
  for (int i = 0; i < WIDTH/10; i++) {
    for (int j = 0; j < HEIGHT/10; j++) {
      fill(20*GRID[i][j]*100,40,200);
      rect(i*CELL_WIDTH, j*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);  
    }   
  }
}

// handle user input
void mousePressed() 
{
  Double ix = Math.floor(mouseX/CELL_WIDTH);
  Double iy = Math.floor(mouseY/CELL_HEIGHT);
  int x = ix.intValue();
  int y = iy.intValue();
  
  if (GRID[x][y] == 0) {
    GRID[x][y] = 1;
  } else {
    GRID[x][y] = 0; 
  }
  
  drawGrid();
}

void keyPressed()
{
  if (running == true) {
     running = false;
  } else {
    running = true;
  }
  
}