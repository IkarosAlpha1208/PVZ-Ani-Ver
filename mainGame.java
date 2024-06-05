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
    BufferedImage gameOver;

    BufferedImage grass;
    BufferedImage zombieAni;
    ArrayList<Zombie> zList = new ArrayList<>();
    boolean newWave = true;

    Zombie z1;

    int zombieX = 700;
    int walkIndex = 1;

    // Abmount of zombies on the screen
    int maxZombies = 5;

    int zombieFrameCounter = 0;
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

        if (screen == 0) {
            background = mainMenu;
            g.drawImage(background, 0, 0, this);

        }

        else if (screen == 1) {
            background = grass;
            g.drawImage(background, 0, 0, this);
            // g.drawImage(zombieAni, zombieX, randY, this);
            // g.drawRect(zombieX, randY, zombieAni.getWidth(), zombieAni.getHeight());
            if (newWave) {
                newWave = false;
                addingZombies();

            }

            // Printint out all the zombies
            for (int i = 0; i < zList.size(); i++) {
                g.drawImage(zList.get(i).getAnimation(), zList.get(i).getX(),
                        zList.get(i).getY(), this);

                g.drawRect(zList.get(i).getHitX(), zList.get(i).getHitY(), zList.get(i).getWidth(),
                        zList.get(i).getHeight());

            }

            animation();

            // Game over screen
        } else if (screen == 7) {
            background = gameOver;
            g.drawImage(background, 0, 0, this);
            zList.removeAll(zList);
            newWave = true;
            zombieX = 700;
            zombieFrameCounter = 0;

        }

        // g.drawImage(background, 0, 0, this);

    }

    // Adding the zombies to the arraylist
    public void addingZombies() {

        for (int i = 0; i < maxZombies; i++) {

            // putting them in random rows
            randY = randRow[(int) (Math.random() * (4 - 0 + 1)) + 0];
            // Add the zombie object into the list
            zList.add(new Normal(100, 10, zombieX + i * 4, randY));

        }

    }

    public void animation() {

        // every 10 frame change the animation
        zombieFrameCounter++;
        if (zombieFrameCounter == 10) {
            for (int i = 0; i < zList.size(); i++) {
                if (zList.get(i).getX() == 145) {
                    screen = 7;
                    return;

                }

                zList.get(i).move();

                if (walkIndex == 8) {
                    walkIndex = 1;
                }
                try {
                    zombieAni = ImageIO.read(new File("assets/zombies/NormalZombie/zombiewalk" + walkIndex + ".png"));
                    zList.get(i).setAnimation(zombieAni);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("----ERROR-----" + walkIndex);

                }
                walkIndex++;
                zombieFrameCounter = 0;
            }
        }

    }

    public void initialize() {
        // initialize all the stuffff
        try {
            mainMenu = ImageIO.read(new File("assets/backgrounds/main.png"));
            grass = ImageIO.read(new File("assets/backgrounds/grass.png"));
            gameOver = ImageIO.read(new File("assets/backgrounds/gameover.png"));

            zombieAni = ImageIO.read(new File("assets/zombies/NormalZombie/zombiewalk1.png"));

            // randY = randRow[(int) (Math.random() * (4 - 0 + 1)) + 0];
            // randY = randRow[3];
            System.out.println(randY);

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

        // if (screen == 1) {
        // animation();

        // }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println(e.getX() + " " + e.getY());

        if (screen == 0) {
            if (e.getX() > 592 && e.getX() < 710 && e.getY() > 225 && e.getY() < 264) {
                screen = 1;
                // System.out.println("PLAY");

            }

            else if (e.getX() > 582 && e.getX() < 705 && e.getY() > 270 && e.getY() < 308) {
                System.out.println("ENDLESS");

            }

            else if (e.getX() > 576 && e.getX() < 692 && e.getY() > 317 && e.getY() < 353) {
                System.out.println("WIKI");

            }

            else if (e.getX() > 138 && e.getX() < 217 && e.getY() > 318 && e.getY() < 360) {
                System.out.println("GACHA");

            }

            else if (e.getX() > 640 && e.getX() < 678 && e.getY() > 386 && e.getY() < 419) {
                System.out.println("Exist");
                System.exit(ABORT);

            }

        }

        else if (screen == 7) {

            if (e.getX() > 320 && e.getX() < 463 && e.getY() > 379 && e.getY() < 419) {
                screen = 0;

            }
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