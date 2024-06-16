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
    // Image components
    BufferedImage background;
    BufferedImage mainMenu;
    BufferedImage gameOver;
    BufferedImage levels;
    BufferedImage inven;
    BufferedImage teamDisplay;
    BufferedImage grass;
    BufferedImage zombieAni;
    BufferedImage aboutScreen;
    BufferedImage helpScreen;
    // List of important objects
    ArrayList<Zombie> zList = new ArrayList<>();
    ArrayList<Lawnmower> lList = new ArrayList<>();
    HashMap<String, Plant> pList = new HashMap<>();
    ArrayList<Plant> plantObjects = new ArrayList<>();
    ArrayList<Projectile> projectileList = new ArrayList<>();
    ArrayList<Projectile> sunList = new ArrayList<>();
    ArrayList<Scoreboard> scores = new ArrayList<>();
    Player player;

    // Map object
    Background map;

    int zombieFrameCounter = 0;
    int zombieMoveCounter = 0;
    // Other important variables
    int mapLcord = 187, mapRcord = 720, mapUcord = 72, mapDcord = 440;
    int blockSizeX = 56, blockSizeY = 75;
    Thread thread;
    int FPS = 60;
    int screen;
    int once;

    // Constructor sets up Jpanel
    public mainGame() {
        // sets up JPanel
        setPreferredSize(new Dimension(770, 429));
        setBackground(Color.WHITE);
        setVisible(true);

        // starting the thread
        thread = new Thread(this);
        thread.start();
    }

    // Main method
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

    // Paint component, method that draws everything on the screen
    // Gets the grpahics obeject and does not return anything
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the Main menu screen
        if (screen == 0) {
            background = mainMenu;
            g.drawImage(background, 0, 0, this);
            once = 0;
            // Draw the level select screen
        } else if (screen == 4) {
            background = levels;
            g.drawImage(background, 0, 0, this);
        }
        // if its the game screen
        else if (screen == 1) {

            // Draw the background & and teams display
            g.drawImage(map.getBackground(), 0, 0, this);

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
            // Draw the zombies
            for (int i = 0; i < zList.size(); i++) {
                g.drawImage(zList.get(i).getAnimation(), zList.get(i).getX(),
                        zList.get(i).getY(), this);

                // print out the plant
            }
            // Draw all the lawnmowers
            // lawnmower picture
            for (int i = 0; i < lList.size(); i++) {
                g.drawImage(lList.get(i).getImage(), lList.get(i).getX(), lList.get(i).getY(), this);
            }

            // this is for the plant team that you have
            g.drawImage(teamDisplay, 10, 0, this);
            g.drawString("" + Player.getSunlight(), 128, 68);
            int y = 20;
            int index = 0;
            for (Plant p : player.getTeam()) {
                g.drawImage(p.getImage(), 32, y, this);
                g2d.setColor(Color.WHITE);
                g2d.drawString("" + p.getCost(), 85, y + 40);
                g2d.setStroke(new BasicStroke(5));
                if (!p.isCooldown()) {
                    g2d.drawLine(32, y, 84, y + 52);
                    g2d.drawLine(32, y + 52, 84, y);
                }
                if (Player.getCurrentPlant() == index) {

                    g2d.drawRect(32, y, 54, 54);
                }
                index++;
                y += 67;
            }

        } else if (screen == 5) {
            background = inven;
            g.drawImage(background, 0, 0, this);
            int tile = 22;

            for (Plant p : player.getTeam()) {
                g.drawImage(p.getImage(), tile, 18, this);
                tile += 77;
            }
            int x = 56;
            int y = 123;
            for (int i = 0; i < player.plantSize(); i++) {
                g.drawString(player.getOwnPlant(i) + "", x, y + 23);
                if (x >= 427) {
                    x = 56;
                    y += 85;
                } else {
                    x += 58;
                }
            }
            // Game over screen

        } else if (screen == 7) {
            background = gameOver;
            g.drawImage(background, 0, 0, this);
            // Reset the zombie & Lawnmower lists

            if (map.getMode().equals("Endless")) {
                player.setHighWave(map.getWaveNum() - 1);
            }

            zombieFrameCounter = 0;

            // Winner Screen
        } else if (screen == 8) {
            // Reset all the lists
            if (once == 0)
                player.changeMoney(map.getMoney());
            once++;

            zList.removeAll(zList);
            lList.removeAll(lList);
            g.drawImage(map.getWinner(), 0, 0, this);

            System.out.println(player.getMoney());

        } else if (screen == 9) {
            background = aboutScreen;
            g.drawImage(background, 0, 0, this);
        } else if (screen == 10) {
            background = helpScreen;
            g.drawImage(background, 0, 0, this);

        }
    }

    // Adding the zombies to the arraylist
    // no return type or paramters
    public void addingZombies() {

        // 1

        // If the gammode is not endless alternate between mini wave and big wave
        // until player beats wave 3
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
            // If its endless just keep on adding newWaves
        } else {
            map.newWave(zList);
        }

    }

    // Adding lawnmowers to the Lawnmower list
    // No return type or paramters
    public void addingLawnmowers() {
        for (int i = 0; i < 5; i++) {
            Lawnmower l = new Lawnmower(i);
            lList.add(l);

        }

    }

    // Animation method for the zombie
    // No return type or paramters
    public void animation() {

        // every 20 frame change the animation
        zombieFrameCounter++;
        zombieMoveCounter++;
        if (zombieFrameCounter == 20) {
            for (int i = 0; i < zList.size(); i++) {

                if (zList.get(i).getX() <= 145) {
                    screen = 7;
                    return;

                }

                zList.get(i).animation();
                // If its not currently dying or eating move the zombie
                if (!zList.get(i).getIsEating() && !zList.get(i).getIsDead())
                    zList.get(i).move();

                zombieFrameCounter = 0;

            }
        }

    }

    // initialize all the images and reading in the score board
    // Does not return anything or have any paramters
    public void initialize() {
        try {

            mainMenu = ImageIO.read(new File("assets/backgrounds/main.png"));
            gameOver = ImageIO.read(new File("assets/backgrounds/gameover.png"));
            levels = ImageIO.read(new File("assets/backgrounds/LevelSelect.png"));
            inven = ImageIO.read(new File("assets/backgrounds/Team.png"));
            aboutScreen = ImageIO.read(new File("assets/backgrounds/credits.png"));
            helpScreen = ImageIO.read(new File("assets/backgrounds/howtoplay.png"));
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

        try {
            Scanner inFile = new Scanner(new File("scoreboard.txt"));
            while (inFile.hasNext()) {
                String line = inFile.next();
                System.out.println(line.length());

                if (line.substring(line.indexOf(")") + 1).length() > 0) {
                    int score = Integer.parseInt(line.substring(line.indexOf(")") + 1));
                    Scoreboard temp = new Scoreboard(score);
                    scores.add(temp);
                }

            }
            inFile.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    // Method to run was part of the template you gave.
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

    // updates all the stuff about the game
    public void update() {

        // If its currently on the game screen
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

            // this is for the lawnmower, moving and looping through all zombies until it
            // hit wall
            for (int i = 0; i < lList.size(); i++) {
                Lawnmower l = lList.get(i);
                l.intersection(zList);
                if (l.getActive())
                    l.move();
                if (l.getX() > 700)
                    lList.remove(i);
            }

            // zombie attacking & zombie removing if its dead
            for (int i = 0; i < zList.size(); i++) {
                if (zList.get(i).getRemove()) {
                    zList.remove(zList.get(i));
                    i--;
                } else {
                    zList.get(i).attack(pList);
                }
            }
        }

    }

    @Override
    // Mouse clicked method, gets the Mouse event and does not return anything
    public void mouseClicked(MouseEvent e) {

        System.out.println(e.getX() + " " + e.getY());

        // All of this is just for the user to clicking on a button
        // If it clickes between a area that were the button exist switch game state
        // If it chooses to start the game intializes background object

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
                Scoreboard playerHigh = new Scoreboard(player.getHighWave());
                scores.add(playerHigh);
                scores.sort(null);

                try {
                    PrintWriter outFile = new PrintWriter(new FileWriter("scoreboard.txt"), true);
                    for (int i = 0; i < scores.size(); i++) {
                        outFile.println(i + 1 + ") " + scores.get(i).getHighWave());

                    }
                    outFile.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                System.exit(ABORT);
            }
        }

        else if (screen == 4)

        {
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
            if (e.getX() >= 20 && e.getX() <= 493 && e.getY() >= 41 && e.getY() <= 111) {
                int num = (e.getX() - 20) / 76;
                if (num >= 6) {
                    num -= 1;
                }
                int i = player.getTeam(num).getId();
                player.addPlant(player.getTeam(num).getId());
                player.removeTeam(num);
            }

            if (e.getX() >= 20 && e.getX() <= 482 && e.getY() >= 123 && e.getY() <= 291) {
                int x = (e.getX() - 20) / 58;
                int y = (e.getY() - 123) / 84;
                int num = x + (y * 8);
                if (player.getTeam().size() >= 6) {
                    System.out.println("Team is full");
                } else if (player.getOwnPlant(num) <= 0) {
                    System.out.println("You dont have this plant");
                } else {
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

        // this is the level screen, here are all the statement for planting, collecting
        // sun
        else if (screen == 1) {
            // this check for if player collect sunlight
            for (int i = 0; i < sunList.size(); i++) {
                if (sunList.get(i).isHit(zList, e.getX() - 5, e.getY() - 20)) {
                    sunList.remove(i);
                    Player.changeSunlight(25);
                    break;
                }
            }

            if (e.getX() >= 31 && e.getX() <= 124 && e.getY() >= 45 && e.getY() <= 446) {
                int y = (e.getY() - 45) / 67;
                if (player.getTeam(y).isCooldown()) {
                    Player.setCurrentPlant(y);
                }
            } else if (Player.getCurrentPlant() > -1) {
                System.out.println(Player.getCurrentPlant());
                if (e.getX() > mapLcord && e.getX() < mapRcord && e.getY() > mapUcord && e.getY() < mapDcord) {
                    int xCord = (e.getX() - mapLcord) / blockSizeX;
                    int yCord = (e.getY() - mapUcord) / blockSizeY;
                    String cords = "" + xCord + yCord;

                    // check if therer is already a plant existed in that tile
                    if (pList.containsKey(cords)) {
                        System.out.println("There is already a plant there! at " + cords);
                    } else if (player.getTeam(Player.getCurrentPlant()).getCost() > Player.getSunlight()) {
                        System.out.println("Not enough sunlight");
                        Player.setCurrentPlant(-1);
                    } else if (xCord <= 8) {
                        pList.put(cords,
                                player.getTeam(Player.getCurrentPlant()).createPlant(mapLcord + xCord * blockSizeX,
                                        mapUcord + yCord * (blockSizeY - 10), yCord, cords));
                        player.getTeam(Player.getCurrentPlant()).setCooldown();
                        Player.changeSunlight(player.getTeam(Player.getCurrentPlant()).getCost() * -1);
                        Player.setCurrentPlant(-1);
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
        } else if (screen == 9) {

            if (e.getX() > 17 && e.getX() < 175 && e.getY() > 399 && e.getY() < 441) {
                screen = 0;

            }
        } else if (screen == 10) {

            if (e.getX() > 30 && e.getX() < 209 && e.getY() > 387 && e.getY() < 440) {
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
    // Keypresssed method, gets a keyEvent object
    // No return value
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (screen == 0) {
            // if the key pressed is a show the about screen
            if (key == KeyEvent.VK_A) {
                System.out.println("About");
                screen = 9;
                // if the key pressedd is h show the help screen
            } else if (key == KeyEvent.VK_H) {
                System.out.println("Help");
                screen = 10;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}