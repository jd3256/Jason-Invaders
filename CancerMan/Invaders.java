package CancerMan;

import java.awt.*;
import java.awt.Font;

public class Invaders {
    private double xPos;
    private double yPos;
    private int points;

    private Color color;

    private double vel;

    private double stairs;

    private boolean hit;

    private double radius;

    private boolean disappear;

    public Invaders(double xPos, double yPos,int rowNum){
        hit = false;
        disappear = hit;
        this.xPos = xPos;
        this.yPos = yPos;
        vel = 20;
        stairs = 5;
        radius = 2.5;

        if(rowNum ==4 || rowNum == 3){
            points = 1;
            color = StdDraw.RED;
        }
        if(rowNum == 2 || rowNum == 1){
            points = 2;
            color = StdDraw.MAGENTA;
        }
        if(rowNum==0){
            points = 3;
            color = StdDraw.BOOK_LIGHT_BLUE;
        }
    }

    public void drawInvaders(){
        StdDraw.setPenColor(color);
        Font font = new Font("Arial",Font.BOLD,20);
        StdDraw.setFont(font);
        if(!hit) {
            if (points == 1) {
                //StdDraw.filledCircle(xPos, yPos, radius);
                StdDraw.picture(xPos,yPos,"Daco_4258154.png", 2*radius, 2*radius, 0);
            }
            if (points == 2) {
                //StdDraw.text(xPos, yPos, "2");
                StdDraw.picture(xPos,yPos,"Daco_4215839.png", 2*radius, 2*radius, 0);
            }
            if (points == 3) {
                //StdDraw.filledSquare(xPos, yPos, radius);
                StdDraw.picture(xPos,yPos,"Daco_4392705.png", 2*radius, 2*radius, 0);
            }
        }
    }

    public double getXPos(){
        return xPos;
    }

    public void setXPos(double num){
        xPos = num;
    }

    public double getYPos(){
        return yPos;
    }

    public int getPoints(){
        return points;
    }
    public void setColor(Color a){
        color = a;
    }
    public void setPoints(int num){
        points =  num;
    }

    public void setYPos(double num){
        yPos = num;
    }

    public boolean getHit(){
        return hit;
    }
    public void setHit(boolean bool){
        hit = bool;
    }
    public boolean getDisappear(){return disappear;}
    public void setDisappear(boolean bool){
        disappear = bool;
    }


    public double moveXInvaders(double timeElapsed, boolean rightIsTrue){
        if(rightIsTrue){
            xPos = xPos + vel *timeElapsed;
        }else{
            this.xPos = xPos - vel *timeElapsed;
        }

        return xPos;
    }

    public double moveYInvaders(boolean changeInDirection){
        if(changeInDirection){
            yPos = yPos - stairs;
        }
        return yPos;
    }

    public void mudda( double x, double y, double w, double h){
        // 1 = laser x y wh
        // 2 = invader radius

        if(((y-h <= yPos + radius) && (y-h >= yPos - radius)) || ((y+h <= yPos + radius) && (y+h >= yPos - radius)) || ((y + h <= yPos + radius) && (y - h >= yPos -radius)) ||((yPos + radius <= y + h) && (yPos - radius >= y -h))){
            if(((x-w <= xPos + radius) && (x-w >= xPos - radius)) || ((x+w <= xPos + radius) && (x+w >= xPos - radius)) || ((x + w <= xPos + radius) && (x - w >= xPos -radius)) || (((xPos + radius <= x + w) && (xPos - radius >= x -w)))) {
                hit = true;
            }
        }



    }



}