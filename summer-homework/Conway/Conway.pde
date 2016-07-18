int WIDTH = 400;
int HEIGHT = 400;
int CELL_WIDTH = 20;
int CELL_HEIGHT = 20;

size(400,400);
background(0);
//noStroke();

// No fourth argument means 100% opacity.
for (int i = 0; i < WIDTH/10; i++) {
  for (int j = 0; j < HEIGHT/10; j++) {
    fill(20,40,200);
    rect(i*CELL_WIDTH, j*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);  
  }   
}