package eu.javalab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Canvas extends JPanel {

    private static final long serialVersionUID = -4983228416754041415L;
    private List<Ball> balls = new ArrayList<Ball>();
    private int w;
    private int h;

    public Canvas(int width, int height, int fps) {
	w = width;
	h = height;
	setBackground(new Color(66, 66, 231));
	setPreferredSize(new Dimension(w, h));
	setBorder(BorderFactory.createLineBorder(new Color(165, 165, 255), 50));
	setupBalls(20);
	ScheduledExecutorService exec = Executors.newScheduledThreadPool(balls.size());
	exec.scheduleAtFixedRate(new Runnable() {
	    @Override
	    public void run() {
		repaint();
	    }
	}, 0, 1000/fps, TimeUnit.MILLISECONDS);
    }
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	g2.setColor(Color.white);
	Ball b = null;
	for (int i = 0; i < balls.size(); ++i) {
	    b = balls.get(i);
	    g2.drawRenderedImage(b, AffineTransform.getTranslateInstance(b.getX(), b.getY()));
	    //g2.drawString(String.valueOf(b.getXSpeed()), (float)b.getX(), (float)b.getY());
	}
    }
    
    private void setupBalls(int n) {
	Random r = new Random();
	for (int i = 0; i < n; ++i) {
	    balls.add(new Ball(new Dimension(10, 10), new Dimension(w, h), new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), 50, r.nextInt(w-100)+50, r.nextInt(h-100)+50, r.nextInt(20)+1));
	}
    }
    
}
