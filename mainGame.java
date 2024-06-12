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
import maps.*;
import javax.sound.sampled.*;
import maps.*;

class mainGame extends JPanel implements Runnable, MouseListener, KeyListener {
    BufferedImage background;
    BufferedImage mainMenu;
    BufferedImage gameOver;
    BufferedImage levels;
    BufferedImage inven;

    BufferedImage grass;
    BufferedImage zombieAni;
    ArrayList<Zombie> zList = new ArrayList<>();
    HashMap<String, Plant> pList = new HashMap<>();
    ArrayList<Plant> selectedPlants = new ArrayList<>();
    ArrayList<Plant> plantObjects = new ArrayList<>();
    ArrayList<Projectile> projectileList = new ArrayList<>();
    ArrayList<Projectile> sunList = new ArrayList<>();

    Background map;

    Zombie z1;

    int zombieX = 500;

    // Abmount of zombies on the screen
    int maxZombies = 5;

    int zombieFrameCounter = 0;
    // Normal z1;

    int[] randRow = { 0, 75, 150, 215, 290 };
    int randY;
    int mapLcord = 187, mapRcord = 720, mapUcord = 72, mapDcord = 440;
    int blockSizeX = 56, blockSizeY = 75;
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
        else if(screen ==4 ){
            background = levels;
            g.drawImage(background, 0, 0, this);

        }
       
      

        else if (screen == 1) {
            g.drawImage(map.getBackground(), 0, 0, this);

            // Printint out all the zombies
            for (int i = 0; i < zList.size(); i++) {
                g.drawImage(zList.get(i).getAnimation(), zList.get(i).getX(),
                        zList.get(i).getY(), this);

                // // If the zombie is dead show the head falling off
                // if (zList.get(i).getIsDead())
                // g.drawImage(zList.get(i).getHead(), zList.get(i).getHeadX(),
                // zList.get(i).getHeadY(), this);

            }
            for (Plant p : pList.values()) {
                if (!p.isDead())
                    g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                g.drawRect(p.getX() + 10, p.getY() + 10, p.getWidth(), p.getHeight());
            }

            for (Projectile p : projectileList) {
                g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                g.drawRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
            }

            for(Projectile p : sunList) {
                g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                g.drawRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
            }

            // animation();

            // Game over screen
        } else if(screen == 5){
            background = inven;
            g.drawImage(background, 0, 0, this);
        }else if (screen == 7) {
            background = gameOver;
            g.drawImage(background, 0, 0, this);
            zList.removeAll(zList);

            zombieFrameCounter = 0;

        } else if (screen == 8) {
            g.drawString("GAME WON", 400, 400);

        }

        // g.drawImage(background, 0, 0, this);

    }

    // Adding the zombies to the arraylist
    public void addingZombies() {

        if (map.getWaveNum() == 1)
            map.waveOne(zList);
        else if (map.getWaveNum() == 2)
            map.waveTwo(zList);
        else if (map.getWaveNum() == 3)
            map.waveThree(zList);

        // Game won Screen
        else {
            screen = 8;
        }

    }

    public void animation() {

        // every 10 frame change the animation
        zombieFrameCounter++;
        if (zombieFrameCounter == 30) {
            for (int i = 0; i < zList.size(); i++) {

                if (zList.get(i).getX() <= 145) {
                    screen = 7;
                    return;

                }

                if (!zList.get(i).getIsEating())
                    zList.get(i).move();
                zList.get(i).animation();
                // zList.get(i).animateHead();
                // zList.get(i).animateHead();
                zombieFrameCounter = 0;
            }
        }

    }

    public void initialize() {
        // initialize all the stuffff
        try {
            mainMenu = ImageIO.read(new File("assets/backgrounds/main.png"));
            gameOver = ImageIO.read(new File("assets/backgrounds/gameover.png"));
            zombieAni = ImageIO.read(new File("assets/zombies/NormalZombie/zombiewalk1.png"));
            levels = ImageIO.read(new File("assets/backgrounds/LevelSelect.png"));
            inven = ImageIO.read(new File("assets/backgrounds/Team.png"));
            plantObjects.add(new Sunflower(0,0));
            plantObjects.add(new PeaShooter(0, 0));
            plantObjects.add(new Wallnut(0,0));
        } catch (IOException e) {
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

        if (screen == 1) {
            animation();
            if (zList.size() == 0) {
                addingZombies();
            }

            // plant shooting
            for (Plant p : pList.values()) {
                if(p.getStat().contains("sun")){
                    p.attack(sunList);
                }
                else if (p.checkRow(zList)){
                    p.attack(projectileList);
                }
            }

            // projectile moving & hitting
            for (int i = 0; i < projectileList.size(); i++) {
                projectileList.get(i).move();
                boolean b = projectileList.get(i).isHit(zList, 0, 0);
                if (b) {
                    projectileList.remove(i);
                    i--;
                }
            }

            for(int i = 0; i < sunList.size(); i++) {
                // Sunlight s = (Sunlight) sunList.get(i);
                boolean b = sunList.get(i).isHit(zList, 0, 0);
                if (b) {
                    sunList.remove(i);
                    i--;
                }
            }
            
            // zombie attacking
            for (int i = 0; i < zList.size(); i++) {
                zList.get(i).attack(pList);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(screen);

        System.out.println(e.getX() + " " + e.getY());

        if (screen == 0) {
            if (e.getX() > 592 && e.getX() < 710 && e.getY() > 225 && e.getY() < 264) {
                background = levels;
                screen = 4;
            }

            else if (e.getX() > 582 && e.getX() < 705 && e.getY() > 270 && e.getY() < 308) {
                System.out.println("ENDLESS");

            }

            else if (e.getX() > 576 && e.getX() < 692 && e.getY() > 317 && e.getY() < 353) {
                System.out.println("TEAM");
                screen = 5;
            }

            else if (e.getX() > 138 && e.getX() < 217 && e.getY() > 318 && e.getY() < 360) {
                System.out.println("GACHA");

            }

            else if (e.getX() > 640 && e.getX() < 678 && e.getY() > 386 && e.getY() < 419) {
                System.out.println("Exist");
                System.exit(ABORT);

            }

        }

        else if(screen == 4){
             if (e.getX() > 211 && e.getX() < 286 && e.getY() > 319 && e.getY() < 340) {
                System.out.println("Level 1 ");
                map = new Grass("grass");
                screen = 1 ;


            }
            else if (e.getX() > 356 && e.getX() < 436 && e.getY() > 319 && e.getY() < 340) {
                System.out.println("Level 2 ");

            }

            else if (e.getX() > 505 && e.getX() < 583 && e.getY() > 319 && e.getY() < 340) {
                System.out.println("Level 3 ");

            }
        }

        else if(screen == 5){
            if(e.getX() > 22 && e.getX() < 157 && e.getY() > 380 && e.getY() < 448){
                System.out.println("Save and exit...");
                screen = 0;
            }
        }

        else if (screen >= 1 && screen <= 3) {
            for(int i = 0; i < sunList.size(); i++) {
                if(sunList.get(i).isHit(zList, e.getX() - 5, e.getY() - 20)) {
                    sunList.remove(i);
                    break;
                }
            }

            if (e.getX() > mapLcord && e.getX() < mapRcord && e.getY() > mapUcord && e.getY() < mapDcord) {
                int xCord = (e.getX() - mapLcord) / blockSizeX;
                int yCord = (e.getY() - mapUcord) / blockSizeY;
                String cords = "" + xCord + yCord;
                if (pList.containsKey(cords)) {
                    System.out.println("There is already a plant there! at " + cords);
                } else if( xCord <= 8) {
                    pList.put(cords, plantObjects.get(0).createPlant(mapLcord + xCord * blockSizeX,
                            mapUcord + yCord * (blockSizeY - 10), yCord, cords));
                    System.out.println("Planted" + " " + cords);
                }
            }
        }

        else if (screen == 7) {

            if (e.getX() > 320 && e.getX() < 463 && e.getY() > 379 && e.getY() < 419) {
                System.out.println("Hello");
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
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

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
    public void keyReleased(KeyEvent e) {}
}