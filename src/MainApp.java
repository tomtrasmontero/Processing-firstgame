import processing.core.PApplet;

public class MainApp extends PApplet {
    private int x = 100;
    private int diffX = 1;


    public static void main(String[] args) {
        PApplet.main("MainApp", args);
    }
//set size here run once
    public void settings() {
        size(640,480);
    }

//    runs once in the beginning of the video
    public void setup() {
        background(155);
    }

//    loops, this is where you can put the logic
    public void draw() {
//        redraw the background so the ellipse does not leave a trail
        background(155);
//        x, y, width, height
        ellipse(x,100,50 ,50);
//        moving the ellipse to the right
        x = x + diffX;
//        comparing x with width
        if(x > width) {
            diffX = -1;
        }
        if (x < 0){
            diffX = 1;
        }
    }
}
