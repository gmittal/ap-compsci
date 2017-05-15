// A* Pathfinder
// Written by Gautam Mittal and Aaron Schultz
// AP Computer Science 2016-2017

/*
  INSTRUCTIONS:
  Draw some dots on the grid;
  Press any key to start the game logic.
  Watch the magic unfold.
  Press any key to stop the magic.
  Repeat.
*/

import java.util.Random;
import java.util.Collections;

// basic grid parameters
int WIDTH = 1020;
int HEIGHT = 1020;
int CELL_WIDTH = 20;
int CELL_HEIGHT = 20;

// grid data structure
int[][] GRID = new int[WIDTH/CELL_WIDTH][HEIGHT/CELL_HEIGHT];

boolean running = false;
boolean endPoint = true;
boolean showDistances = false;
Generator maze = new Generator(WIDTH/CELL_WIDTH, HEIGHT/CELL_HEIGHT);
int endX = 11;
int endY = 11;

// Solver variables
int[][] distanceGrid = new int[WIDTH/CELL_WIDTH][HEIGHT/CELL_HEIGHT];
ArrayList<Integer> solution = new ArrayList();

// Set the stage
void setup() 
{
  size(1020,1020);
  background(0);
  frameRate(60);
  
  // Initialize the grid with a random maze
  maze.generateRecursive(new Random());
  //GRID = maze.wallGrid;
  
  for (int x = 0; x < WIDTH/CELL_WIDTH; x++) {
    GRID[x][0] = 1;
    GRID[x][HEIGHT/CELL_HEIGHT - 1] = 1;
  }

  for (int y = 0; y < HEIGHT/CELL_HEIGHT; y++) {
    GRID[0][y] = 1;
    GRID[WIDTH/CELL_WIDTH - 1][y] = 1;
  }  

  
  int initEndX = 2*maze.pointGetX(maze.goalPosition)+1;
  int initEndY = 2*maze.pointGetY(maze.goalPosition)+1;
  GRID[11][11] = 2;
  
  BreadthFirstSearch(endX, endY);

}


// event loop (runs 60x per second)
void draw() 
{
  if (running == true) {
    //AStarSearch();
  }
  
  drawGrid();
  
  if (showDistances)
  for (int i = 0; i < WIDTH/CELL_WIDTH; i++) {
    for (int j = 0; j < HEIGHT/CELL_HEIGHT; j++) {
       int val = distanceGrid[i][j];
       textSize(10);
       fill(255, 255, 255);
       text(val, i*CELL_WIDTH+5, j*CELL_HEIGHT+CELL_HEIGHT/2+2);
    }
  }
 
  
  fill(255, 255, 255);
  stroke(255);
  int x1, y1, x2, y2;
  for (int i = solution.size() - 1; i > 0; i--) {
    x1 = getX(solution.get(i));
    y1 = getY(solution.get(i));
    x2 = getX(solution.get(i - 1));
    y2 = getY(solution.get(i - 1));
    line((int) ((x1 + 0.5) * CELL_WIDTH), (int) ((y1 + 0.5) * CELL_HEIGHT),
        (int) ((x2 + 0.5) * CELL_WIDTH), (int) ((y2 + 0.5) * CELL_HEIGHT));
  } 
}

int coordToInt(int x, int y) {
   return (WIDTH/CELL_WIDTH)*y+x;
}

int getX(int coordInt) {
   return coordInt % (WIDTH/CELL_WIDTH); 
}

int getY(int coordInt) {
   return coordInt/(WIDTH/CELL_WIDTH); 
}

ArrayList<Integer> getNeighbors(int coord)
{
    ArrayList<Integer> neighbors = new ArrayList<Integer>();
    
    if (coord-1 >= 0) {
      if (GRID[getX(coord-1)][getY(coord-1)] != 1) 
        neighbors.add(coord - 1);
    }
    
    if (coord+1 <= WIDTH/CELL_WIDTH*HEIGHT/CELL_HEIGHT) {
      if (GRID[getX(coord+1)][getY(coord+1)] != 1) 
        neighbors.add(coord + 1); 
    }
    
    if (coord-WIDTH/CELL_WIDTH >= 0) {
      if (GRID[getX(coord - WIDTH/CELL_WIDTH)][getY(coord - WIDTH/CELL_WIDTH)] != 1) 
        neighbors.add(coord - WIDTH/CELL_WIDTH);
    }
    
    if (coord+WIDTH/CELL_WIDTH <= WIDTH/CELL_WIDTH*HEIGHT/CELL_HEIGHT) {
      if (GRID[getX(coord + WIDTH/CELL_WIDTH)][getY(coord + WIDTH/CELL_WIDTH)] != 1) 
        neighbors.add(coord + WIDTH/CELL_WIDTH); 
    }
 
    return neighbors;
}

int[][] createDistanceGrid(int startX, int startY)
{
  int[][] dg = new int[WIDTH/CELL_WIDTH][HEIGHT/CELL_HEIGHT];
  
  // set everything to unvisited
  for (int i = 0; i < WIDTH/CELL_WIDTH; i++) {
     for (int j = 0; j < HEIGHT/CELL_HEIGHT; j++) {
        dg[i][j] = -1; 
     }
  }
  
  ArrayList<Integer> frontier = new ArrayList();
  
  dg[startX][startY] = 0; // set the start point
  frontier.add(coordToInt(startX, startY));
  
  while (!frontier.isEmpty()) {
    
    ArrayList<Integer> nextFrontier = new ArrayList();
    frontierLoop: for (int i = 0; i < frontier.size(); i++) {
      ArrayList<Integer> currentNeighbors = getNeighbors(frontier.get(i));
      System.out.println(frontier.size());
      
      for (int j = 0; j < currentNeighbors.size(); j++) {
         if (dg[getX(currentNeighbors.get(j))][getY(currentNeighbors.get(j))] == -1) {
  
            nextFrontier.add(currentNeighbors.get(j)); // -1
            dg[getX(currentNeighbors.get(j))][getY(currentNeighbors.get(j))] = dg[getX(frontier.get(i))][getY(frontier.get(i))] + 1;
      
            if (GRID[getX(currentNeighbors.get(j))][getY(currentNeighbors.get(j))] == 2) {
               nextFrontier = new ArrayList();
               break frontierLoop;
            }
            
         }
      }
    }
    
    frontier = new ArrayList(nextFrontier);
  }
  
  return dg;
}

// The A* Pathfinding Algorithm
void AStarSearch()
{
  for (int i = 0; i < WIDTH/CELL_WIDTH; i++) {
     for (int j = 0; j < HEIGHT/CELL_HEIGHT; j++) {
       // current cell state
       int currentState = GRID[i][j];
       
     }
  }
}


void BreadthFirstSearch(int x, int y)
{
  distanceGrid = createDistanceGrid(1, 1);
  int currentCell = coordToInt(x, y);
  solution = new ArrayList();
  solution.add(currentCell);
  for (int i = distanceGrid[x][y]; i > 0; i--) {
     ArrayList<Integer> currentNeighbors = getNeighbors(currentCell);
     for (int cell : currentNeighbors) {
       if (distanceGrid[getX(cell)][getY(cell)] == i-1) {
          currentCell = cell;
          solution.add(currentCell);
          break;
       }
     }
  }
}


// helper to get the number of neighbors of a cell
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


// draw the grid on the screen
void drawGrid() 
{
  stroke(0);
  // visualize the 2D grid array data
  for (int i = 0; i < WIDTH/CELL_WIDTH; i++) {
    for (int j = 0; j < HEIGHT/CELL_HEIGHT; j++) {
      if (GRID[i][j] == 0) {
        fill(0, 40, 200);
      } else if (GRID[i][j] == 1) {
        fill(2000, 40, 200); 
      } else {
        fill(255, 255, 255);
      }
      rect(i*CELL_WIDTH, j*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);  
    }   
  }
}

// handle user input
void mouseDragged() 
{
  Double ix = Math.floor(mouseX/CELL_WIDTH);
  Double iy = Math.floor(mouseY/CELL_HEIGHT);
  int x = ix.intValue();
  int y = iy.intValue();
  
  if (GRID[x][y] != 2)
  GRID[x][y] = 1;

  
  drawGrid();
}

void mousePressed() 
{
  Double ix = Math.floor(mouseX/CELL_WIDTH);
  Double iy = Math.floor(mouseY/CELL_HEIGHT);
  int x = ix.intValue();
  int y = iy.intValue();
  
  if (GRID[x][y] == 1) {
     GRID[x][y] = 0; 
  } else if (GRID[x][y] == 0) {
     GRID[x][y] = 1;
  }
  
  
 drawGrid();
}

void mouseReleased()
{
   BreadthFirstSearch(endX, endY);
   drawGrid(); 
}

// start stop functionality
void keyPressed() {
  
  if (key == RETURN) {
    Double ix = Math.floor(mouseX/CELL_WIDTH);
    Double iy = Math.floor(mouseY/CELL_HEIGHT);
    int x = ix.intValue();
    int y = iy.intValue();
    
    if (GRID[x][y] != 1) {
    GRID[endX][endY] = 0;
    GRID[x][y] = 2;
      endX = x;
      endY = y;
      BreadthFirstSearch(endX, endY);
    }
  }
  
  if (key == SHIFT)
  showDistances = !showDistances;
  
  
  
  drawGrid();
}






// Aaron's wonderful maze generator to build us some walls - his summer 2016 project
public enum Direction {
 up, down, left, right; 
}

public class Generator {
  public int width, height;
  public int wallThickness;
  public int goalPosition;
  public int[][] wallGrid;
  public ArrayList<Integer> solutionPath = new ArrayList<Integer>();
  public boolean endOnEdge;
  public int[][] distanceGrid = new int[width][height];

  public Generator(int w, int h) {
    width = w;
    height = h;
    wallThickness = 20;
    while (((2 * width + 1) * wallThickness > 1280 || (2 * height + 1) * wallThickness > 704)
        && wallThickness > 1) {
      wallThickness--;
    }
    wallGrid = new int[2 * w + 1][2 * h + 1];
    initGrid();
    endOnEdge = false;
  }

  private void initGrid() {
    for (int x = 0; x < wallGrid.length; x++) {
      for (int y = 0; y < wallGrid[0].length; y++) {
        if (x % 2 == 0 || y % 2 == 0) {
          wallGrid[x][y] = 1;
        }
      }
    }
    knockDownEdge(0);
  }

  public void generateRecursive(Random rand) {
    boolean[][] checkedGrid = new boolean[width][height];
    ArrayList<Integer> cells = new ArrayList<Integer>();
    int currentCell = pointConvert(0, 0);
    cells.add(currentCell);
    int farthestDistance = 0;
    int[][] distanceGrid = new int[width][height];
    ArrayList<Direction> openCells;
    int x, y, nextCell;
    Direction nextMovement;
    checkedGrid[0][0] = true;

    while (!cells.isEmpty()) {
      currentCell = cells.get(cells.size() - 1);
      if (rand.nextBoolean() && rand.nextBoolean()) {
        currentCell = cells.get(rand.nextInt(cells.size()));
      }

      // currentCell = cells.get(0);

      openCells = new ArrayList<Direction>();
      x = pointGetX(currentCell);
      y = pointGetY(currentCell);
      if (x < width - 1 && !checkedGrid[x + 1][y]) {
        openCells.add(Direction.right);
      }
      if (x > 0 && !checkedGrid[x - 1][y]) {
        openCells.add(Direction.left);
      }
      if (y < height - 1 && !checkedGrid[x][y + 1]) {
        openCells.add(Direction.down);
      }
      if (y > 0 && !checkedGrid[x][y - 1]) {
        openCells.add(Direction.up);
      }

      if (openCells.isEmpty()) {

        cells.remove(findIndex(currentCell, cells));
      } else {
        nextMovement = openCells.get(rand.nextInt(openCells.size()));
        x = 2 * x + 1;
        y = 2 * y + 1;
        if (nextMovement == Direction.right) {
          wallGrid[x + 1][y] = 0;
        }
        if (nextMovement == Direction.left) {
          wallGrid[x - 1][y] = 0;
        }
        if (nextMovement == Direction.up) {
          wallGrid[x][y - 1] = 0;
        }
        if (nextMovement == Direction.down) {
          wallGrid[x][y + 1] = 0;
        }
        nextCell = pointFromDirection(currentCell, nextMovement);
        x = pointGetX(nextCell);
        y = pointGetY(nextCell);
        cells.add(nextCell);
        distanceGrid[x][y] = distanceGrid[pointGetX(currentCell)][pointGetY(currentCell)] + 1;

        if (endOnEdge) {
          if (distanceGrid[x][y] > farthestDistance && onEdge(x, y)) {
            farthestDistance = distanceGrid[x][y];
            goalPosition = nextCell;
          }
        } else {
          if (distanceGrid[x][y] > farthestDistance) {
            farthestDistance = distanceGrid[x][y];
            goalPosition = nextCell;
          }
        }

        checkedGrid[pointGetX(nextCell)][pointGetY(nextCell)] = true;
      }
    }

    solutionPath.add(goalPosition);
    for (int i = farthestDistance - 1; i >= 0; i--) {
      currentCell = solutionPath.get(solutionPath.size() - 1);
      x = pointGetX(currentCell);
      y = pointGetY(currentCell);
      if (x < width - 1 && distanceGrid[x + 1][y] == i && wallGrid[x * 2 + 2][y * 2 + 1] == 0) {
        solutionPath.add(pointFromDirection(currentCell, Direction.right));
      } else if (x > 0 && distanceGrid[x - 1][y] == i && wallGrid[x * 2][y * 2 + 1] == 0) {
        solutionPath.add(pointFromDirection(currentCell, Direction.left));
      } else if (y < height - 1 && distanceGrid[x][y + 1] == i && wallGrid[x * 2 + 1][y * 2 + 2] == 0) {
        solutionPath.add(pointFromDirection(currentCell, Direction.down));
      } else if (y > 0 && distanceGrid[x][y - 1] == i && wallGrid[x * 2 + 1][y * 2] == 0) {
        solutionPath.add(pointFromDirection(currentCell, Direction.up));
      }
    }
    solutionPath.add(-1);
    knockDownEdge(goalPosition);

  }

  private int pointConvert(int x, int y) {
    return y * width + x;
  }

  private int pointGetX(int i) {
    return i % width;
  }

  private int pointGetY(int i) {
    return i / width;
  }

  private int pointFromDirection(int i, Direction d) {
    if (d == Direction.right) {
      return i + 1;
    }
    if (d == Direction.left) {
      return i - 1;
    }
    if (d == Direction.up) {
      return i - width;
    }
    if (d == Direction.down) {
      return i + width;
    }
    return i;
  }

  private int findIndex(int x, ArrayList<Integer> cells) {
    for (int i = cells.size() - 1; i >= 0; i--) {
      if (cells.get(i) == x) {
        return i;
      }
    }
    return -1;
  }

  private boolean onEdge(int x, int y) {
    if (x == 0)
      return true;
    if (y == 0)
      return true;
    if (x == width - 1)
      return true;
    if (y == height - 1)
      return true;
    return false;
  }

  private void knockDownEdge(int p) {
    int x = pointGetX(p);
    int y = pointGetY(p);
    if (x == 0)
      wallGrid[0][2 * y + 1] = 0;
    else if (x == width - 1)
      wallGrid[2 * width][2 * y + 1] = 0;
    else if (y == 0)
      wallGrid[2 * x + 1][0] = 0;
    else if (y == height - 1)
      wallGrid[2 * x + 1][2 * height] = 0;
  }
}