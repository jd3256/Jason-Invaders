package CancerMan;

import java.awt.*;

public class You {
    private double yCenter;
    private double xCenter;
    private Color color;

    public static void draw(double row, double col) {
        double yCenter = row;
        double xCenter = col;
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle(xCenter,yCenter,5,2.5);
        StdDraw.filledCircle(xCenter,yCenter+2.5, 2);

    }




}

    //oops
