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
import player.*;
import javax.sound.sampled.*;

class mainGame extends JPanel implements Runnable, MouseListener, KeyListener {
    BufferedImage background;
    BufferedImage mainMenu;
    BufferedImage gameOver;
    BufferedImage levels;
    BufferedImage winnerScreen;
    BufferedImage inven;
    BufferedImage teamDisplay;
    BufferedImage grass;
    BufferedImage zombieAni;
    ArrayList<Zombie> zList = new ArrayList<>();
    ArrayList<Lawnmower> lList = new ArrayList<>();
    HashMap<String, Plant> pList = new HashMap<>();
    ArrayList<Plant> plantObjects = new ArrayList<>();
    ArrayList<Projectile> projectileList = new ArrayList<>();
    ArrayList<Projectile> sunList = new ArrayList<>();
    Player player;

    Background map;

    Zombie z1;

    int zombieX = 500;

    // Abmount of zombies on the screen
    int maxZombies = 5;

    int zombieFrameCounter = 0;
    int zombieMoveCounter = 0;
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
        } else if (screen == 4) {
            background = levels;
            g.drawImage(background, 0, 0, this);

        }

        else if (screen == 1) {
            g.drawImage(map.getBackground(), 0, 0, this);
            g.drawImage(teamDisplay, 10, 0, this);
            int y = 20;
            for(Plant p : player.getTeam()){
                g.drawImage(p.getImage(), 32, y, this);
                y += 67;
            }

            // Printint out all the zombies
            for (Plant p : pList.values()) {
                if (!p.isDead())
                    g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                g.drawRect(p.getX() + 10, p.getY() + 10, p.getWidth(), p.getHeight());
            }

            // print out projectile
            for (Projectile p : projectileList) {
                g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                g.drawRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
            }

            // print out all the sun
            for (Projectile p : sunList) {
                g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                g.drawRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
            }

            for (int i = 0; i < zList.size(); i++) {
                g.drawImage(zList.get(i).getAnimation(), zList.get(i).getX(),
                        zList.get(i).getY(), this);

                // print out the plant
            }

            for (int i = 0; i < lList.size(); i++) {
                g.drawImage(lList.get(i).getImage(), lList.get(i).getX(), lList.get(i).getY(), this);
            }

            // animation();

            // Game over screen
        } else if (screen == 5) {
            background = inven;
            g.drawImage(background, 0, 0, this);
            int tile = 22;
            for(Plant p : player.getTeam()){
                g.drawImage(p.getImage(), tile, 18, this);
                tile += 77;
            }
            int x = 56;
            int y = 123;
            for(int i = 0; i < player.plantSize(); i++) {
                g.drawString(player.getOwnPlant(i) + "", x, y + 23);
                if(x >= 427){
                    x = 56;
                    y += 85;
                } else{
                    x += 58;
                }
            }
        } else if (screen == 7) {
            background = gameOver;
            g.drawImage(background, 0, 0, this);
            zList.removeAll(zList);
            lList.removeAll(lList);

            zombieFrameCounter = 0;

        } else if (screen == 8) {
            background = winnerScreen;
            g.drawImage(background, 0, 0, this);

        }
    }

    // Adding the zombies to the arraylist
    public void addingZombies() {

        // 1

        if (!map.getMode().equals("Endless")) {
            if (map.getWaveNum() % 2 == 1 && map.getWaveNum() != 7) {
                System.out.println("HEELLLOOO");
                map.miniWave(zList);
            }

            else if (map.getWaveNum() == 2)
                map.waveOne(zList);

            else if (map.getWaveNum() == 4)
                map.waveTwo(zList);

            else if (map.getWaveNum() == 6)
                map.waveThree(zList);

            // Game won Screen
            else {
                screen = 8;
            }
        } else {
            map.newWave(zList);
        }

    }

    public void addingLawnmowers() {
        for (int i = 0; i < 5; i++) {
            Lawnmower l = new Lawnmower(i);
            lList.add(l);

        }

    }

    public void animation() {

        // every 10 frame change the animation
        zombieFrameCounter++;
        zombieMoveCounter++;
        if (zombieFrameCounter == 20) {
            for (int i = 0; i < zList.size(); i++) {

                if (zList.get(i).getX() <= 145) {
                    screen = 7;
                    return;

                }

                // if (zombieMoveCounter == 20) {
                // if (!zList.get(i).getIsEating())
                // // zList.get(i).move();
                // zombieMoveCounter = 0;
                // }

                zList.get(i).animation();
                if (!zList.get(i).getIsEating() && !zList.get(i).getIsDead())
                    zList.get(i).move();

                zombieFrameCounter = 0;

                // zList.get(i).animateHead();
                // zList.get(i).animateHead();

            }
        }

    }

    public void initialize() {
        // initialize all the stuffff
        try {

            mainMenu = ImageIO.read(new File("assets/backgrounds/main.png"));
            gameOver = ImageIO.read(new File("assets/backgrounds/gameover.png"));
            levels = ImageIO.read(new File("assets/backgrounds/LevelSelect.png"));
            winnerScreen = ImageIO.read(new File("assets/backgrounds/winner.png"));
            inven = ImageIO.read(new File("assets/backgrounds/Team.png"));
            teamDisplay = ImageIO.read(new File("assets/others/teamDisplay.png"));
            plantObjects.add(new Sunflower(0, 0));
            plantObjects.add(new PeaShooter(0, 0));
            plantObjects.add(new Wallnut(0, 0));

            background = mainMenu;
            player = new Player("save.txt", plantObjects);
            screen = 0;
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
                if (p.getStat().contains("sun")) {
                    p.attack(sunList);
                } else if (p.checkRow(zList)) {
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

            // this check if the sun exist for to long and delete it
            for (int i = 0; i < sunList.size(); i++) {
                boolean b = sunList.get(i).isHit(zList, 0, 0);
                if (b) {
                    sunList.remove(i);
                    i--;
                }
            }

            // this is for the lawnmower, moving and looping through all zombies until it hit wall
            for (int i = 0; i < lList.size(); i++) {
                Lawnmower l = lList.get(i);
                l.intersection(zList);
                if (l.getActive())
                    l.move();
                if (l.getX() > 700)
                    lList.remove(i);
            }

            // zombie attacking
            for (int i = 0; i < zList.size(); i++) {
                if (zList.get(i).getRemove()){
                    zList.remove(zList.get(i));
                    i--;
                } else{
                    zList.get(i).attack(pList);
                }
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println(e.getX() + " " + e.getY());

        if (screen == 0) {
            if (e.getX() > 592 && e.getX() < 710 && e.getY() > 225 && e.getY() < 264) {
                background = levels;
                screen = 4;
            }

            else if (e.getX() > 582 && e.getX() < 705 && e.getY() > 270 && e.getY() < 308) {
                System.out.println("ENDLESS");
                map = new Endless("grass");
                addingLawnmowers();
                screen = 1;

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
                player.save("save.txt");
                System.exit(ABORT);
            }
        }

        else if (screen == 4) {
            Player.resetForLevel();
            if (e.getX() > 211 && e.getX() < 286 && e.getY() > 319 && e.getY() < 340) {
                System.out.println("Level 1 ");
                map = new Grass("grass");
                addingLawnmowers();

                screen = 1;

            } else if (e.getX() > 356 && e.getX() < 436 && e.getY() > 319 && e.getY() < 340) {
                System.out.println("Level 2 ");
                map = new Night("night");
                addingLawnmowers();

                screen = 1;

            }

            else if (e.getX() > 505 && e.getX() < 583 && e.getY() > 319 && e.getY() < 340) {
                System.out.println("Level 3 ");
                map = new Boss("grass");
                addingLawnmowers();

                screen = 1;

            } else if (e.getX() > 320 && e.getX() < 462 && e.getY() > 380 && e.getY() < 417) {
                System.out.println("Return");
                screen = 0;

            }
        }

        // team screen
        else if (screen == 5) {
            if(e.getX() >= 20 && e.getX() <= 493 && e.getY() >= 41 && e.getY() <= 111){
                int num = (e.getX()-20)/76;
                if(num >= 6){num -= 1;}
                int i = player.getTeam(num).getId();
                player.addPlant(player.getTeam(num).getId());
                player.removeTeam(num);
            }
            
            if(e.getX() >= 20 && e.getX() <= 482 && e.getY() >= 123 && e.getY() <= 291){
                int x = (e.getX()-20)/58;
                int y = (e.getY()-123)/84;
                int num = x + (y*8);
                if(player.getTeam().size() >= 6){
                    System.out.println("Team is full");
                } else if(player.getOwnPlant(num) <= 0){
                    System.out.println("You dont have this plant");
                }
                else{
                    player.removePlant(num);
                    player.addTeam(plantObjects.get(num));
                }
            }
            if (e.getX() > 22 && e.getX() < 157 && e.getY() > 380 && e.getY() < 448) {
                System.out.println("Save and exit...");
                player.save("save.txt");
                screen = 0;
            }
        }

        // this is the level screen, here are all the statement for planting, collecting sun
        else if (screen == 1) {
            // this check for if player collect sunlight
            for (int i = 0; i < sunList.size(); i++) {
                if (sunList.get(i).isHit(zList, e.getX() - 5, e.getY() - 20)) {
                    sunList.remove(i);
                    break;
                }
            }

            if(e.getX() >= 31 && e.getX() <= 124 && e.getY() >= 45 && e.getY() <= 447){

            }
            if(Player.getCurrentPlant() > -1) {
                System.out.println(Player.getCurrentPlant());
                if (e.getX() > mapLcord && e.getX() < mapRcord && e.getY() > mapUcord && e.getY() < mapDcord) {
                    int xCord = (e.getX() - mapLcord) / blockSizeX;
                    int yCord = (e.getY() - mapUcord) / blockSizeY;
                    String cords = "" + xCord + yCord;

                    //check if therer is already a plant existed in that tile
                    if (pList.containsKey(cords)) {
                        System.out.println("There is already a plant there! at " + cords);
                    } else if (xCord <= 8) {
                        pList.put(cords, plantObjects.get(0).createPlant(mapLcord + xCord * blockSizeX,
                                mapUcord + yCord * (blockSizeY - 10), yCord, cords));
                        System.out.println("Planted" + " " + cords);
                    }
                }
            }
        }

        else if (screen == 7) {

            if (e.getX() > 320 && e.getX() < 463 && e.getY() > 379 && e.getY() < 419) {
                System.out.println("Hello");
                screen = 0;

            }
        } else if (screen == 8) {

            if (e.getX() > 311 && e.getX() < 490 && e.getY() > 389 && e.getY() < 439) {
                screen = 0;

            }
        }
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
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            System.out.println("About");
        } else if (key == KeyEvent.VK_H) {
            System.out.println("Help");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}