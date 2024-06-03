import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import plants.*;
import zombies.*;
import projectiles.*;
import javax.sound.sampled.*;

class mainGame extends JPanel implements Runnable, MouseListener{
    Thread thread;
    int FPS = 60;

    public mainGame(){
        //sets up JPanel
		setPreferredSize(new Dimension(1000, 1000));
        setBackground(Color.WHITE);
		setVisible(true);
		
		//starting the thread
		thread = new Thread(this);
		thread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
		mainGame myPanel = new mainGame();
		frame.add(myPanel);
		frame.addMouseListener(myPanel);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Hello World", 100, 100);
    }
    @Override
    public void run(){
        while(true) {
			//main game loop
			update();
			this.repaint();
			try {
				Thread.sleep(1000/FPS);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
    }

    public void update() {
        //updates the game
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}