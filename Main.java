package CancerMan;



import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(750,750);
        StdDraw.setScale(0, 100);
        StdDraw.enableDoubleBuffering();
        double timeElapsed = .017;
        double youMove = 20;
        double youRow = 7.5;
        Invaders[][] invaders = new Invaders[5][7];
        Invaders[][] homeinvaders = new Invaders[5][5];

        boolean shot = false;
        boolean shot2 = false;
        boolean shot3 = false;


        boolean lose = false;
        boolean win = false;
        Laserz shoot = new Laserz(0,0,true);
        Laserz dodge = new Laserz(0,0,false);
        Laserz dodge2 = new Laserz(0,0,false);

        int lottery;
        int lottery2;
        int timer=0;
        double MouseX = StdDraw.mouseX();
        double MouseY = StdDraw.mouseY();
        boolean mouse = false;


        for(int row = 0; row<invaders.length; row++){
            for(int col = 0; col < invaders[0].length; col++){
                invaders[row][col] = new Invaders(10+10*col,90-5*row,row);
            }
        }
        for(int row = 0; row<homeinvaders.length; row++){
            for(int col = 0; col < homeinvaders[0].length; col++){
                homeinvaders[row][col] = new Invaders(10+10*col,65-5*row, row);

            }
        }
        boolean direction = true;
        boolean down = false;
        Font among = new Font("Arial", Font.BOLD, 50);
        boolean start = false;
        int score;
    while (!start) {
        drawLines();
        StdDraw.setFont(among);
        StdDraw.setPenColor(Color.lightGray);
        StdDraw.filledRectangle(50, 80, 25, 10);
        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
        StdDraw.text(50, 80, "Jason Invaders");
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(50, 25, 15, 10);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(50, 25, "Start");


        MouseX = StdDraw.mouseX();
        MouseY = StdDraw.mouseY();
        if (MouseX <= 50 + 15 && MouseX >= 50 - 15 && MouseY <= 25 + 10 && MouseY >= 25 - 10 && !StdDraw.isMousePressed() && mouse) {
            start = true;
        }

        if (StdDraw.isMousePressed()) {
            mouse = true;
        }
        if (homeinvaders[homeinvaders.length - 1][homeinvaders[0].length - 1].getXPos() >= 90) {
            direction = false;
            down = true;
        }
        if (homeinvaders[0][0].getXPos() <= 10) {
            direction = true;
            down = true;
        }

        for (int row = 0; row < homeinvaders.length; row++) {
            for (int col = 0; col < homeinvaders[0].length; col++) {
                homeinvaders[row][col].setXPos(homeinvaders[row][col].moveXInvaders(timeElapsed, direction));
                homeinvaders[row][col].drawInvaders();
            }
        }
        StdDraw.show(); //Because we have called StdDraw.enableDoubleBuffering(), everything that you draw up until this point will be loaded into java's memory but not actually drawn. Calling StdDraw.draw() then draws everything at once that is loaded into java's memory.
        StdDraw.pause((int) (timeElapsed * 1000)); //You must pass to the pause method the number of milliseconds to pause for; so we multiply by 1000 because our timeElapsed variable is in seconds, not milliseconds.
        StdDraw.clear();
    }


        while(start) {


                drawLines();


                if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                    if (youMove > 5) {
                        youMove--;
                    }
                }

                if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                    if (youMove < 95) {
                        youMove++;
                    }
                }

                if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && !win && !lose) {
                    if (!shot) {
                        shoot.setX(youMove);
                    }
                    shot = true;
                }


                if (shot) {
                    shoot.SpawnLaser();
                    shoot.MoveLasers(timeElapsed);

                }

                if (shoot.getY() > 100) {
                    shot = false;
                    shoot.setY(youRow);
                }

                if (!lose) {
                    drawYou(youRow, youMove);
                }

                if (invaders[invaders.length - 1][invaders[0].length - 1].getXPos() >= 90) {
                    direction = false;
                    down = true;
                }
                if (invaders[0][0].getXPos() <= 10) {
                    direction = true;
                    down = true;
                }


                if (!lose) {
                    for (int row = 0; row < invaders.length; row++) {
                        for (int col = 0; col < invaders[0].length; col++) {
                            invaders[row][col].setXPos(invaders[row][col].moveXInvaders(timeElapsed, direction));
                            invaders[row][col].setYPos(invaders[row][col].moveYInvaders(down));
                            invaders[row][col].drawInvaders();
                        }
                    }
                }
                down = false;
                for (int row = 0; row < invaders.length; row++) {
                    for (int col = 0; col < invaders[0].length; col++) {
                        invaders[row][col].mudda(shoot.getX(), shoot.getY(), shoot.getW(), shoot.getH());

                        if (invaders[row][col].getHit() != invaders[row][col].getDisappear()) {
                            invaders[row][col].setDisappear(invaders[row][col].getHit());
                            shot = false;
                            shoot.setY(youRow);

                        }

                    }
                }


                int biscuits = invaders.length - 1;

                if (!win && !lose) {
                    lottery = (int) (Math.random() * (invaders[0].length - 1));
                    lottery2 = (int) (Math.random() * (invaders[0].length - 1));
                    while (invaders[biscuits][lottery].getHit() && invaders[biscuits][lottery2].getHit()) {
                        if (biscuits > 0) {
                            biscuits--;
                        } else {
                            biscuits = invaders.length - 1;

                            break;
                        }


                    }


                        if (timer % 20 == 0) {
                            if (!shot2 && !invaders[biscuits][lottery].getHit()) {

                                dodge.setX(invaders[biscuits][lottery].getXPos());
                                dodge.setY(invaders[biscuits][lottery].getYPos());
                            }
                            shot2 = true;

                        }
                        if (shot2) {
                        dodge.SpawnLaser();
                        dodge.MoveLasers(timeElapsed);
                        }
                        if (dodge.getY() < 0) {
                        shot2 = false;
                        dodge.setY(invaders[biscuits][lottery].getYPos());
                        }

                        if (timer % 20 == 0) {
                            if (!shot3 && !invaders[biscuits][lottery2].getHit()) {
                                dodge2.setX(invaders[biscuits][lottery2].getXPos());
                                dodge2.setY(invaders[biscuits][lottery2].getYPos());
                            }
                            shot3 = true;

                        }
                        if (shot3) {
                            dodge2.SpawnLaser();
                            dodge2.MoveLasers(timeElapsed);
                        }

                        if (dodge2.getY() < 0) {
                            shot3 = false;
                            dodge2.setY(invaders[biscuits][lottery2].getYPos());
                        }



                }


                win = true;
                for (int row = 0; row < invaders.length; row++) {
                    for (int col = 0; col < invaders[0].length; col++) {
                        if (!invaders[row][col].getHit()) {
                            win = false;
                        }

                    }
                }

                if (win) {
                    StdDraw.setPenColor(200, 0, 255);
                    StdDraw.setFont(among);

                    StdDraw.text(50, 50, "YOU'RE THANOS!!!");
                }

                    for (int row = 0; row < invaders.length; row++) {
                        for (int col = 0; col < invaders[0].length; col++) {
                            if ((invaders[row][col].getYPos() <= 10 && !invaders[row][col].getHit()) && (!win)) {
                                lose = true;


                            }
                        }
                    }


                    if (Jasonas(dodge.getX(), dodge.getY(), dodge.getW(), dodge.getH(), youRow, youMove, 5, 2.5) || Jasonas(dodge2.getX(), dodge2.getY(), dodge2.getW(), dodge2.getH(), youRow, youMove, 5, 2.5) && !win) {
                        lose = true;
                    }

                MouseX = StdDraw.mouseX();
                MouseY = StdDraw.mouseY();

                if (lose) {
                    StdDraw.setFont(among);
                    StdDraw.text(50, 75, "YOUR BAD AT VIDEO GAMES!!!");
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledRectangle(50, 25, 15, 5);
                   // StdDraw.filledRectangle(50, 10, 15, 5);
                    StdDraw.picture(50,50,"Daco_4392135.png", 30, 30, 0);


                    StdDraw.setPenColor(Color.WHITE);
                    StdDraw.text(50, 25, "Restart");
                   // StdDraw.text(50, 10, "Home");

                }

            if (StdDraw.isMousePressed()) {
                mouse = true;
            }

                if (MouseX <= 50 + 15 && MouseX >= 50 - 15 && MouseY <= 25 + 10 && MouseY >= 25 - 10 && !StdDraw.isMousePressed() && mouse && lose) {
                    lose = false;

                    for (int row = 0; row < invaders.length; row++) {
                        for (int col = 0; col < invaders[0].length; col++) {
                            invaders[row][col] = new Invaders(10 + 10 * col, 90 - 5 * row, row);
                            invaders[row][col].setDisappear(false);
                            invaders[row][col].setHit(false);
                        }
                    }
                    dodge.setY(0);
                    dodge2.setY(0);


                }

            if (!StdDraw.isMousePressed()) {
                mouse = false;
            }
                score = 0;
                for(int row = 0; row< invaders.length;row++){
                    for(int col =0; col < invaders[0].length;col++){
                        if(invaders[row][col].getHit()){
                            score+=invaders[row][col].getPoints();
                        }
                    }
                }
                if(!lose && !win) {
                    StdDraw.setPenColor(Color.BLACK);
                    StdDraw.text(90, 92, "Score: " + score);
                }

                timer++;
                StdDraw.show(); //Because we have called StdDraw.enableDoubleBuffering(), everything that you draw up until this point will be loaded into java's memory but not actually drawn. Calling StdDraw.draw() then draws everything at once that is loaded into java's memory.
                StdDraw.pause((int) (timeElapsed * 1000)); //You must pass to the pause method the number of milliseconds to pause for; so we multiply by 1000 because our timeElapsed variable is in seconds, not milliseconds.
                StdDraw.clear(); //This clears everything drawn on the screen. You must redraw the image you wish to display for each frame of an animation

        }
    }

    public static void drawLines(){
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledRectangle(50,50,50,50);
        for(int i = 0; i<20;i++){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(0,5*i,100,5*i);
        }
        for(int i = 0; i<20;i++){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(5*i,0,5*i,100);
        }

    }


    public static void drawYou(double row, double col) {
        double yCenter = row;
        double xCenter = col;
        StdDraw.setPenColor(StdDraw.BOOK_RED.brighter());
        StdDraw.filledRectangle(xCenter,yCenter,5,2.5);
        StdDraw.filledCircle(xCenter,yCenter+2.5, 2);

    }

    public static boolean Jasonas ( double x, double y, double w, double h, double yPos, double xPos, double width, double height){
        // 1 = laser x y wh
        // 2 = invader radius
        boolean hit = false;

        if(((y-h <= yPos + height) && (y-h >= yPos - height)) || ((y+h <= yPos + height) && (y+h >= yPos - height)) || ((y + h <= yPos + height) && (y - h >= yPos -height)) ||((yPos + height <= y + h) && (yPos - height >= y -h))){
            if(((x-w <= xPos + width) && (x-w >= xPos - width)) || ((x+w <= xPos + width) && (x+w >= xPos - width)) || ((x + w <= xPos + width) && (x - w >= xPos -width)) || (((xPos + width <= x + w) && (xPos - width >= x -w)))) {
                hit = true;
            }
        }

        return hit;

    }
}