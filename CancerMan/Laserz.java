package CancerMan;

import javax.swing.*;
import java.awt.*;
import java.awt.image.SinglePixelPackedSampleModel;

public class Laserz {
    private static double width;
    private static double length;
    private double yCenter;
    private double xCenter;
    private Color color;
    private double yVel;
    private boolean ally;

    public Laserz(double row, double col,boolean ally) {
        this. ally = ally;
        yCenter = row;
        xCenter = col;
        width = 1;
        length = 2.5;
        if(this.ally){
            yVel = 365;
            color = (StdDraw.YELLOW);

        }else{
            yVel = 50;
            color = (StdDraw.RED);
        }
    }

    public void setY(double yCenter){
        this.yCenter = yCenter;
    }
    public double getY(){
        return yCenter;
    }
    public void setX(double xCenter){
        this.xCenter = xCenter;
    }
    public double getX(){
        return xCenter;
    }
    public boolean getally(){
        return ally;
    }
    public double getW(){return width;}
    public void setH(double length){this.length = length;}
    public double getH(){
        return length;
    }



    public void SpawnLaser() {
        StdDraw.setPenColor(color);
        //StdDraw.filledRectangle(xCenter,yCenter,width,length);
        StdDraw.picture(xCenter,yCenter,"vecteezy_banana-is-a-yellow-fruit_9343861_940.png", width*4, length, 270);
    }



    public void MoveLasers( double timeElasped){
        if(this.ally){
            yCenter+=(yVel*timeElasped);
        }else{
            yCenter-=(yVel*timeElasped);
        }


    }

}