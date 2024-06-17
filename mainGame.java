import java.util.*;
import java.util.Map.Entry;
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
    BufferedImage winnerScreen;
    BufferedImage inven;
    BufferedImage teamDisplay;
    BufferedImage gacha;
    BufferedImage extract;
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
    LinkedList<Integer> rolls = new LinkedList<>();
    Player player;

    // Background music
    Clip menuMusic;
    Clip loserMusic;
    Clip teamMusic;
    Clip zombieComing;

    // Map object
    Background map;

    int zombieFrameCounter = 0;
    int zombieMoveCounter = 0;
    //gacha variables
    int rollType = 0;
    int[] common = {0, 1, 2, 3, 6};
    int[] rare = {4, 5, 7, 9, 10};
    int[] epic = {11, 12, 13, 14, 15, 16, 17, 18};
    // Other important variables
    // this is the planting area
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
            loserMusic.stop();
            teamMusic.stop();
            zombieComing.setFramePosition(0);
            menuMusic.start();
            menuMusic.loop(Clip.LOOP_CONTINUOUSLY);

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
            menuMusic.stop();
            try {
                // Draw the background & and teams display
                g.drawImage(map.getBackground(), 0, 0, this);
    
                // Printint out all the zombies
                for (Plant p : pList.values()) {
                    if (!p.isDead())
                        g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                }
    
                // print out projectile
                for (Projectile p : projectileList) {
                    g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                    g.drawRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
                }
    
                // print out all the sun
                for (Projectile p : sunList) {
                    g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                }
                // Draw the zombies
                for (int i = 0; i < zList.size(); i++) {
                    g.drawImage(zList.get(i).getAnimation(), zList.get(i).getX(),
                            zList.get(i).getY(), this);
    
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
                    //put a cross if the plant is on cooldown
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
                if(Player.getCurrentPlant() == 7){
                    g2d.drawRect(121, 78, 48, 48);
                }
            } catch (ConcurrentModificationException e) {
                System.out.println(e.getMessage());
                System.out.println("OH no there is some problem with the list");
            }

        // Gacha screen, where you can roll for plant
        } else if(screen == 2){
            menuMusic.stop();
            teamMusic.start();
            teamMusic.loop(Clip.LOOP_CONTINUOUSLY);
            background = gacha;
            g2d.setColor(Color.WHITE);
            Font font = new Font("Arial", Font.PLAIN, 16);
            g2d.setFont(font);
            g.drawImage(background, 0, 0, this);
            g2d.drawString("" + player.getMoney(), 249, 398);
        
        // extraction screen, showing you what plant you got
        } else if(screen == 3){
            background = extract;
            g.drawImage(background, 0, 0, this);
            if(rollType == 0){
                g.drawImage(plantObjects.get(rolls.get(0)).getImage(), 328, 131, this);
            }else{
                int x = 85;
                for(int i = 0; i < 10; i++){
                    g.drawImage(plantObjects.get(rolls.get(i)).getImage(), x, 131, this);
                    x += 60;
                }
            }
        
        // team screen, where you select your team
        } else if (screen == 5) {
            menuMusic.stop();
            teamMusic.start();
            teamMusic.loop(Clip.LOOP_CONTINUOUSLY);
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
            if(Player.getCurrentPlant() == -1){
                Player.setCurrentPlant(0);
            }
            g.drawImage(plantObjects.get(Player.getCurrentPlant()).getImage(), 578, 36, this);
            g.drawString(plantObjects.get(Player.getCurrentPlant()).getDescribe(), 512, 213);
            Font font = new Font("Arial", Font.PLAIN, 16);
            g2d.setFont(font);
            g2d.setColor(Color.WHITE);
            g2d.drawString("" + player.getMoney(), 270, 395);

            // Game over screen
        } else if (screen == 7) {
            map.stopMusic();
            // Stopping the eating noise from the zombie
            for (int i = 0; i < zList.size(); i++) {

                zList.get(i).stopEatingSound();
            }

            loserMusic.start();
            loserMusic.loop(Clip.LOOP_CONTINUOUSLY);

            background = gameOver;
            g.drawImage(background, 0, 0, this);
            // Reset the zombie & Lawnmower lists

            zList.removeAll(zList);
            lList.removeAll(lList);
            pList.clear();
            projectileList.removeAll(projectileList);
            if (map.getMode().equals("Endless")) {
                player.setHighWave(map.getWaveNum() - 1);
            }

            zombieFrameCounter = 0;

            // Winner Screen
        } else if (screen == 8) {
            map.stopMusic();
            map.playWinMusic();
            // Reset all the lists
            if (once == 0)
                player.changeMoney(map.getMoney());
            once++;

            zList.removeAll(zList);
            lList.removeAll(lList);
            pList.clear();
            projectileList.removeAll(projectileList);
            background = winnerScreen;
            g.drawImage(background, 0, 0, this);

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

            if (map.getWaveNum() == 1)
                zombieComing.start();

            if (map.getWaveNum() % 2 == 1 && map.getWaveNum() != 7) {
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
    // and the important variables like plants and player
    // Does not return anything or have any paramters
    public void initialize() {
        try {

            mainMenu = ImageIO.read(new File("assets/backgrounds/main.png"));
            gameOver = ImageIO.read(new File("assets/backgrounds/gameover.png"));
            levels = ImageIO.read(new File("assets/backgrounds/LevelSelect.png"));
            winnerScreen = ImageIO.read(new File("assets/backgrounds/winner.png"));
            inven = ImageIO.read(new File("assets/backgrounds/Team.png"));
            aboutScreen = ImageIO.read(new File("assets/backgrounds/credits.png"));
            helpScreen = ImageIO.read(new File("assets/backgrounds/howtoplay.png"));
            teamDisplay = ImageIO.read(new File("assets/others/teamDisplay.png"));
            gacha = ImageIO.read(new File("assets/backgrounds/gacha.png"));
            extract = ImageIO.read(new File("assets/backgrounds/extraction.png"));

            plantObjects.add(new Sunflower(0, 0));
            plantObjects.add(new PeaShooter(0, 0));
            plantObjects.add(new Wallnut(0, 0));
            plantObjects.add(new FreezeShooter(0, 0));
            plantObjects.add(new Jalepeno(0, 0));
            plantObjects.add(new Cherry(0, 0));
            plantObjects.add(new SmallShroom(0, 0));
            plantObjects.add(new BigShroom(0, 0));
            plantObjects.add(new Coffee(0, 0));
            plantObjects.add(new GatlingPea(0, 0));
            plantObjects.add(new Repeater(0, 0));
            plantObjects.add(new DawnClair(0, 0));
            plantObjects.add(new Mahou(0, 0));
            plantObjects.add(new NClair(0, 0));
            plantObjects.add(new RHeath(0, 0));
            plantObjects.add(new Yisang(0, 0));
            plantObjects.add(new SwordFaust(0, 0));
            plantObjects.add(new ClockFaust(0, 0));
            plantObjects.add(new Meur(0, 0));

            background = mainMenu;
            player = new Player("save.txt", plantObjects);
            screen = 0;
        } catch (IOException e) {
            System.out.println("FIle not Found");
        }

        try {
            Scanner inFile = new Scanner(new File("scoreboard.txt"));
            System.out.println("Scores");
            while (inFile.hasNext()) {
                String line = inFile.nextLine();

                if (line.length() > 0) {
                    String number = line.substring(line.indexOf(")") + 2);
                    System.out.println(line.substring(0, line.indexOf(")") + 1) + number);

                    int score = Integer.parseInt(number);
                    Scoreboard temp = new Scoreboard(score);
                    scores.add(temp);
                }

            }
            inFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Loading in background music
        AudioInputStream sound;

        try {
            sound = AudioSystem.getAudioInputStream(new File("assets/sound/theme.wav"));
            menuMusic = AudioSystem.getClip();
            menuMusic.open(sound);
            sound = AudioSystem.getAudioInputStream(new File("assets/sound/losemusic.wav"));
            loserMusic = AudioSystem.getClip();
            this.loserMusic.open(sound);
            sound = AudioSystem.getAudioInputStream(new File("assets/sound/teamselection.wav"));
            teamMusic = AudioSystem.getClip();
            teamMusic.open(sound);
            sound = AudioSystem.getAudioInputStream(new File("assets/sound/zombiesarecoming.wav"));
            zombieComing = AudioSystem.getClip();
            zombieComing.open(sound);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
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

        try {
            // If its currently on the game screen
            if (screen == 1) {
                animation();
                if (zList.size() == 0) {
                    addingZombies();
                }
    
                // plant shooting
                Iterator<Entry<String, Plant>> itP = pList.entrySet().iterator();
                while(itP.hasNext()) {
                    Plant p = itP.next().getValue();
                    if (p.getStat().contains("sun")) {
                        p.attack(sunList);
                    } else if (p.checkRow(zList)) {
                        p.attack(projectileList);
                        if(p.getStat().equalsIgnoreCase("bomb") && !p.getExist()){
                            itP.remove();
                        }
                    }
                }
    
                // projectile moving & hitting
                Iterator<Projectile> itPro = projectileList.iterator();
                while(itPro.hasNext()) {
                    Projectile pro = (Projectile) itPro.next();
                    pro.move();
                    boolean b = pro.isHit(zList, 0, 0);
                    if (b) {
                        itPro.remove();
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
        } catch (ConcurrentModificationException e) {
            System.out.println(e.getMessage());
            System.out.println("OH no there is some problem with the list on update");
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
                Player.resetForLevel();
                map = new Endless("grass");
                map.playMusic();
                addingLawnmowers();
                screen = 1;

            }

            else if (e.getX() > 576 && e.getX() < 692 && e.getY() > 317 && e.getY() < 353) {
                System.out.println("TEAM");
                screen = 5;
            }

            else if (e.getX() > 138 && e.getX() < 217 && e.getY() > 318 && e.getY() < 360) {
                System.out.println("GACHA");
                screen = 2;

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
                map.playMusic();
                addingLawnmowers();

                screen = 1;

            } else if (e.getX() > 356 && e.getX() < 436 && e.getY() > 319 && e.getY() < 340) {
                System.out.println("Level 2 ");
                map = new Night("night");
                map.playMusic();
                addingLawnmowers();

                screen = 1;

            }

            else if (e.getX() > 505 && e.getX() < 583 && e.getY() > 319 && e.getY() < 340) {
                System.out.println("Level 3 ");
                map = new Boss("grass");
                map.playMusic();
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
                player.addPlant(player.getTeam(num).getId());
                player.removeTeam(num);
            }

            if (e.getX() >= 20 && e.getX() <= 482 && e.getY() >= 123 && e.getY() <= 367) {
                int x = (e.getX() - 20) / 58;
                int y = (e.getY() - 123) / 84;
                int num = x + (y * 8);
                if(num != 8){
                    Player.setCurrentPlant(num);
                }
                if(num == 8){
                    System.out.println("Not available");
                } else if (player.getTeam().size() >= 6) {
                    System.out.println("Team is full");
                } else if (player.getOwnPlant(num) <= 0) {
                    System.out.println("You dont have this plant");
                } else {
                    player.removePlant(num);
                    player.addTeam(plantObjects.get(num).createPlant(0, 0, 0, ""));
                }
            }
            if (e.getX() > 22 && e.getX() < 157 && e.getY() > 380 && e.getY() < 448) {
                System.out.println("Save and exit...");
                player.save("save.txt");
                menuMusic.setFramePosition(0);
                screen = 0;
            }
        }

        else if(screen == 2){
            if(e.getX() >= 656 && e.getX() <= 754 && e.getY() >= 53 && e.getY() <= 91){
                screen = 0;
            } else if(e.getX() >= 32 && e.getX() <= 192 && e.getY() >= 307 && e.getY() <= 362 && player.getMoney() >= 100){
                System.out.println("roll 1");
                player.changeMoney(-100);
                rollType = 0;
                rolls.add(rolling());
                screen = 3;

            } else if(e.getX() >= 32 && e.getX() <= 192 && e.getY() >= 386 && e.getY() <= 442 && player.getMoney() >= 1000){
                System.out.println("roll 10");
                player.changeMoney(-1000);
                rollType = 1;
                for(int i = 0; i < 10; i++){
                    rolls.add(rolling());
                }
                screen = 3;
            }
        }

        else if(screen == 3){
            if(e.getX() >= 296 && e.getX() <= 496 && e.getY() >= 373 && e.getY() <= 440){
                System.out.println("exit extraction");
                screen = 2;
                while(rolls.size() > 0){
                    player.addPlant(rolls.get(0));
                    rolls.remove(0);
                }

            }
        }

        // this is the level screen, here are all the statement for planting, collecting
        // sun
        else if (screen == 1) {
            // this check for if player collect sunlight
            try {
                for (int i = 0; i < sunList.size(); i++) {
                    if (sunList.get(i).isHit(zList, e.getX() - 5, e.getY() - 20)) {
                        sunList.remove(i);
                        Player.changeSunlight(25);
                        break;
                    }
                }
 
                // this is for player to select plant to put down
                if(e.getX() >= 133 && e.getX() <= 168 && e.getY() >= 116 && e.getY() <= 149){
                    Player.setCurrentPlant(7);
                } else if (e.getX() >= 31 && e.getX() <= 124 && e.getY() >= 45 && e.getY() <= 446) {
                    int y = (e.getY() - 45) / 67;
                    if (player.getTeam(y).isCooldown()) {
                        Player.setCurrentPlant(y);
                    }
                } else if (Player.getCurrentPlant() > -1) {
                    if (e.getX() > mapLcord && e.getX() < mapRcord && e.getY() > mapUcord && e.getY() < mapDcord) {
                        int xCord = (e.getX() - mapLcord) / blockSizeX;
                        int yCord = (e.getY() - mapUcord) / blockSizeY;
                        String cords = "" + xCord + yCord;
    
                        // check if therer is already a plant existed in that tile and if player have enough sunlight
                        // if all yes then put the plant down
                        if (pList.containsKey(cords)) {
                            if(Player.getCurrentPlant() == 7){
                                pList.remove(cords);
                            }
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
           } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Something happened in screen 1");
           }
        }

        else if (screen == 7) {

            if (e.getX() > 320 && e.getX() < 463 && e.getY() > 379 && e.getY() < 419) {
                menuMusic.setFramePosition(0);
                screen = 0;

            }
        } else if (screen == 8) {

            if (e.getX() > 311 && e.getX() < 490 && e.getY() > 389 && e.getY() < 439) {
                map.stopWinMusic();
                menuMusic.setFramePosition(0);
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

    public int rolling(){
        Random random = new Random();
        int ran = random.nextInt(100) + 1;
        if(ran <= 60){
            ran = random.nextInt(common.length);
            return common[ran];
        } else if(ran <= 90){
            ran = random.nextInt(rare.length);
            return rare[ran];
        } else{
            ran = random.nextInt(epic.length);
            return epic[ran];
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