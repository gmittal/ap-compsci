int WIDTH = 400;
int HEIGHT = 400;
int CELL_WIDTH = 20;
int CELL_HEIGHT = 20;

// grid data structure
int[][] GRID = new int[WIDTH][HEIGHT];

void setup() {
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


void draw() {
  drawGrid();
}

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

void mousePressed() {
  Double ix = Math.floor(mouseX/CELL_WIDTH);
  Double iy = Math.floor(mouseY/CELL_HEIGHT);
  int x = ix.intValue();
  int y = iy.intValue();
  
  if (GRID[x][y] == 0) {
    GRID[x][y] = 1;
  } else {
    GRID[x][y] = 0; 
  }
  
}