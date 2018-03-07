import processing.core.PApplet;
import processing.core.PFont;


public class MainApp extends PApplet {
    Ball[] balls = new Ball[18];
    PFont f;
    int timer = 1;

    public static void main(String[] args) {
        PApplet.main("MainApp", args);
    }
//set size here run once
    public void settings() {
        size(640,480);
    }

//    runs once in the beginning of the video
    public void setup() {
//        timer
        f = createFont("Arial",16,true);
        background(155);

        for(int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(parseInt((int) random(50,400)),
            parseInt((int) random(50,400)),parseInt((int)(random(2,8))),
            parseInt((int)(random(2,8))), parseInt((int) random(10,100)));
        }
    }

//    loops, this is where you can put the logic
    public void draw() {
        background(0);
//        int score = showScore();

//      create ball and move
        for(int i = 0; i < balls.length; i++) {
            balls[i].display();
            balls[i].update();

        }
        mouseCollide();

        textFont(f);
        textAlign(RIGHT);
        text("Score: " + timer,100,450);
        timer += 1;

    }

    int showScore() {
        return this.timer / 100;
    }

    public void mousePressed() {
//        show reset button TODO
        if (!looping) {
            reset();
        }
    }


    void reset() {
        //        timer
        f = createFont("Arial",16,true);
        background(0);
        this.balls = new Ball[18];
        this.timer = 1;

        for(int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(parseInt((int) random(50,400)),
                    parseInt((int) random(50,400)),parseInt((int)(random(2,8))),
                    parseInt((int)(random(2,8))), parseInt((int) random(10,100)));
        }

        loop();

    }

    void mouseCollide() {
        for(int i = 0; i < balls.length; i++ ) {
            if(balls[i].pointInEllipse(mouseX, mouseY)) {
                balls[i].pop();
            }
        }
    }


    public class Ball {
        private int y = 100;
        private int x = 100;
        private int diffX = 0;
        private int diffY = 5;
        private int size = 50;

        public Ball(int x, int y, int diffX, int diffY, int size) {
            this.x = x;
            this.y = y;
            this.diffY = diffY;
            this.diffX = diffX;
            this.size = size;
        }

        public void move() {
            y += diffY;
            x += diffX;
        }

//        We need to be able to change dx and dy
        public void setdy(int dy) {
            diffY = dy;
        }

        public void setdx(int dx) {
            diffX = dx;
        }
//        We need to see where the ball is
        public int getdy() {
            return this.diffY;
        }

        public int getdx() {
            return this.diffX;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSize() {
            return size;
        }

        public void display() {
//        x, y, width, height
            ellipse(x,y,size ,size);
        }

        public boolean isCollidingVertical() {
            if( getX() + (getSize()/2) > width || getX() - (getSize()/2) < 0) {
                return true;
            }
            return false;
        }

        public boolean isCollidingHorizontal() {
            if( getY() + (getSize()/2) > height || getY() - (getSize()/2) < 0) {
                return true;
            }
            return false;
        }

        public void checkCollisions() {
            if( isCollidingHorizontal()) {
                setdy( getdy() * -1);
            }
            if( isCollidingVertical()) {
                setdx( getdx() * -1);
            }
        }

        public void update() {
            move();
            checkCollisions();
        }

        public boolean pointInEllipse(int x, int y) {
            double distance = Math.sqrt(Math.pow((x - getX()), 2) + Math.pow((y - getY()), 2));
            if (distance < getSize()/2) {
                return true;
            }
            return false;
        }

        public void pop() {
            noLoop();
        }
    }
}
