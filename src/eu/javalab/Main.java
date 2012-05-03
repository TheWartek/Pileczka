package eu.javalab;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main extends JApplet {
    
    private static final long serialVersionUID = 4648172894076113183L;

    public Main() {
	initComponents();
    }
    
    private void initComponents() {
	setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
	Canvas c = new Canvas(800, 600, 60);
	add(c);
    }
    
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		JApplet ap = new Main();
		JFrame f = new JFrame();
		f.setTitle("Pi³eczka");
		f.add(ap);
		f.setResizable(false);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(screenSize.width/2 - f.getWidth()/2, screenSize.height/2 - f.getHeight()/2);
		f.setVisible(true);
	    }
	});
    }
    
}
