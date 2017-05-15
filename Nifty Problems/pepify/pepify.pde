import gab.opencv.*;
import processing.video.*;
import java.awt.Rectangle;
Capture cam; 
PImage superFace;
OpenCV opencv; // face detection

// ENABLE/DISABLE PEPIFY FEATURE
boolean pepify = true;

void setup() {
  cam = new Capture(this, 1280/2, 720/2, 60);
  cam.start(); 
  opencv = new OpenCV(this, 1280/2, 720/2);
  size(640, 360); 
  opencv.loadCascade(OpenCV.CASCADE_FRONTALFACE);
  
  superFace = loadImage("face.png");
}
 
void draw() {
  if (cam.available()) { 
    cam.read(); 
    opencv.loadImage(cam);
    image(cam, 0, 0);
    
    noFill();
    stroke(0, 255, 0);
    strokeWeight(1);
    Rectangle[] faces = opencv.detect();

    for (int i = 0; i < faces.length; i++)
      if (pepify)
        image(superFace, faces[i].x, faces[i].y, faces[i].width, faces[i].height);   
      else
        rect(faces[i].x, faces[i].y, faces[i].width, faces[i].height);
  }
}  
 
void keyReleased() {
   pepify = !pepify; 
}