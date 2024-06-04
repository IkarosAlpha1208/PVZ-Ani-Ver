import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
// import plants.*;
import zombies.*;
// import projectiles.*
import javax.sound.sampled.*;

class mainGame extends JPanel implements Runnable, MouseListener, KeyListener {
    BufferedImage background;
    BufferedImage mainMenu;
    BufferedImage grass;
    BufferedImage standing;
    ArrayList <Zombie> zList = new ArrayList<>();
    boolean newWave =true;

    Zombie z1 ;





    int zombieX;
    int walkIndex;
    int maxZombies =5;
    int zombieFrameCounter=0;
    // Normal z1;

    int[] randRow = { 0, 75, 150, 215, 290 };
    int randY;
    Thread thread;
    int FPS = 60;
    int screen;

    public mainGame() {
        // sets up JPanel
        setPreferredSize(new Dimension(770, 429));
        setBackground(Color.WHITE);
        setVisible(true);

        // starting the thread
        thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        mainGame myPanel = new mainGame();
        frame.add(myPanel);
        frame.addMouseListener(myPanel);
        frame.addKeyListener(myPanel);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (screen == 0) {
            background = mainMenu;
            g2d.drawImage(background, 0, 0, this);

        }

        else if (screen == 1) {
            background = grass;
            g2d.drawImage(background, 0, 0, this);
            g2d.drawImage(standing, zombieX, randY,this);
            g2d.drawRect(zombieX, randY, standing.getWidth(), standing.getHeight());
            if(newWave){
                newWave=false;
                addingZombies();

            }





        }
        else if(screen ==3 ){

            g2d.drawString("GAME OVER", 100, randY);

        }


        // g2d.drawImage(background, 0, 0, this);

    }

    public void addingZombies(){
        for(int i =0 ; i<maxZombies ; i++){

            randY = randRow[(int) (Math.random() * (4 - 0 + 1)) + 0];
            zList.add(new Normal(100,10,zombieX,randY));


        }


    }


    public void initialize() {
        // initialize all the stuffff
        try {
            mainMenu = ImageIO.read(new File("assets/backgrounds/main.png"));
            grass = ImageIO.read(new File("assets/backgrounds/grass.png"));
            standing = ImageIO.read(new File("assets/zombies/NormalZombie/zombiewalk1.png"));

            // randY = randRow[(int) (Math.random() * (4 - 0 + 1)) + 0];
            // randY = randRow[3];
            System.out.println(randY);
            zombieX =400;

            screen = 0;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("FIle not Found");
        }

    }

    @Override
    public void run() {

        initialize();

        while (true) {
            // main game loop
            update();
            this.repaint();
            try {
                Thread.sleep(1000 / FPS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        // updates the game

        if(screen==1){
            animation();



        }
       
     




    }

    
    

    public void animation(){
       
     
     for(int i = 0 ; i<zList.size();i++){
        if(zList.get(i).getX() ==185){
            screen=3;
            return;
    
           }
           zombieFrameCounter++;
           if (zombieFrameCounter ==10){
            zList.get(i).setX(zList.get(i).getX()-1);

            
       
            if(walkIndex==8){
                walkIndex=1;
            }
            try {
                standing = ImageIO.read(new File("assets/zombies/NormalZombie/zombiewalk"+walkIndex+".png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("----------"+walkIndex);



            }
            walkIndex++;
            zombieFrameCounter=0;
           }

     }
     
        
       
    



        

    }
    
    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println(e.getX() + " " + e.getY());

        if (e.getX() > 592 && e.getX() < 703 && e.getY() > 225 && e.getY() < 264) {
            screen = 1;

        }

        else if (e.getX() > 582 && e.getX() < 694 && e.getY() > 270 && e.getY() < 308) {
            System.out.println("ENDLESS");

        }

        else if (e.getX() > 576 && e.getX() < 687 && e.getY() > 317 && e.getY() < 353) {
            System.out.println("WIKI");

        }

        else if (e.getX() > 138 && e.getX() < 213 && e.getY() > 318 && e.getY() < 356) {
            System.out.println("GACHA");

        }

        // else if (e.getX() > 590 && e.getX() < 709 && e.getY() > 232 && e.getY() <
        // 270) {

        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseClicked'");
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

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // TODO Auto-generated method stub
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            System.out.println("About");
        } else if (key == KeyEvent.VK_H) {
            System.out.println("Help");
        }

        // throw new UnsupportedOperationException("Unimplemented method
        // 'keyReleased'");

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'keyReleased'");
    }
}