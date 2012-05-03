package eu.javalab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ball extends BufferedImage {

    private static final long serialVersionUID = -629144081400642128L;
    private Vector vx = new Vector();
    private Vector vy = new Vector();
    private double x = 0.;
    private double y = 0.;
    private long w = 0;
    private long h = 0;
    private double margin = 0.;
    private double maxx = 0.;
    private double maxy = 0.;

    public Ball(Dimension size, final Dimension canvasSize, Color c, int canvasMargin, int posx, int posy, int speed) {
	super(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
	x = posx;
	y = posy;
	w = size.width;
	h = size.height;
	vx.value = speed;
	vy.value = 1.;
	Random r = new Random();
	vx.value *= r.nextBoolean() ? -1 : 1;
	vy.value *= r.nextBoolean() ? -1 : 1;
	maxx = canvasSize.width - (w) - canvasMargin;
	maxy = canvasSize.height - (h) - canvasMargin;
	margin = canvasMargin;
	
	Graphics2D g2 = createGraphics();
	g2.setColor(c);
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2.fillOval(0, 0, (int)w, (int)h);
	ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
	exec.scheduleAtFixedRate(new Runnable() {
	    @Override
	    public void run() {
		if (x > maxx) {
		    vx.value *= -.5;
		    x = maxx;
		} else if (x < margin) {
		    vx.value *= -.5;
		    x = margin;
		}
		if (y > maxy) {
		    vy.value *= -.8;
		    y = maxy;
		} else if (y < margin) {
		    vy.value *= -.8;
		    y = margin;
		}
		x += vx.value;
		vy.value += (9.81 * .15 * .15) / 2.;
		y += vy.value;
	    }
	}, 0, 10, TimeUnit.MILLISECONDS);
    }

    public double getX() {
	return Double.valueOf(x);
    }

    public double getY() {
	return Double.valueOf(y);
    }

    public long getW() {
	return w;
    }

    public long getH() {
	return h;
    }
    
    public double getXSpeed() {
	return vx.value;
    }
    
    public double getYSpeed() {
	return vy.value;
    }
}
