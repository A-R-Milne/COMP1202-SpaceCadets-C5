import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private double t = 0;
    public Graphics myGraphics;
    private int totalLoops;
    
    public void paintComponents(int bigR, int littleR, int o, int red, int green, int blue, int loops) {
        myGraphics = this.getGraphics();
        myGraphics.setColor(new Color((float)red/255,(float)green/255,(float)blue/255));
        int loopsComplete = totalLoops;
        totalLoops += loops;
        while(totalLoops > loopsComplete) {
            loopsComplete++;
            while(getT() < 2*Math.PI*loopsComplete) {
                drawPoint(littleR, bigR, o);
            }
        }
    }
    
    public void clear() {
        myGraphics = this.getGraphics();
        myGraphics.setColor(Color.WHITE);
        myGraphics.fillRect(0,0,1200,900);
        setT(0);
    }
    
    public void drawPoint(int littleR, int bigR, int o) {
        double x1 = getPencilX(bigR, littleR, o);
        double y1 = getPencilY(bigR, littleR, o);
        setT(getT()+(Math.PI/180));
        double x2 = getPencilX(bigR, littleR, o);
        double y2 = getPencilY(bigR, littleR, o);
        myGraphics.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
    }
    
    public double getPencilX(int bigR, int littleR, int o) {
        return ((bigR-littleR)*Math.cos(getT()) + o*Math.cos(((double) (bigR - littleR) /littleR)*getT())) + 500;
    }

    public double getPencilY(int bigR, int littleR, int o) {
        return ((bigR-littleR)*Math.sin(getT()) - o*Math.sin(((double) (bigR - littleR) /littleR)*getT())) + 400;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }
}
