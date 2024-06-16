package maps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import zombies.*;
import java.awt.image.BufferedImage;
import javax.sound.sampled.*;

public abstract class Background {
    // Fields to store various attributes of the background
    protected BufferedImage background; // Image of the background
    protected int waveNum; // Current wave number
    protected int zombieX; // X coordinate for spawning zombies
    protected int maxZombies; // Maximum number of zombies
    protected String mode; // Game mode
    // Money to get if they win
    protected int money;
    protected String stat;

    // Winner Screen
    protected BufferedImage winner;

    protected Clip gameMusic;
    protected Clip winnerMusic;

    protected HashMap<Integer, Zombie> zombieGrid; // Map to keep track of zombies on the grid

    // Constructor to initialize the background with a specific image
    public Background(String name) {
        try {
            this.background = ImageIO.read(new File("assets/backgrounds/" + name + ".png"));
        } catch (IOException e) {
            System.out.println("CANNOT READ FILE");
        }

        this.waveNum = 1;
        this.zombieX = 750;
        AudioInputStream sound;

        try {
            sound = AudioSystem.getAudioInputStream(new File("assets/sound/Grasswalk.wav"));
            this.gameMusic = AudioSystem.getClip();
            this.gameMusic.open(sound);
            sound = AudioSystem.getAudioInputStream(new File("assets/sound/winmusic.wav"));
            this.winnerMusic = AudioSystem.getClip();
            this.winnerMusic.open(sound);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

    }

    // Abstract methods to be implemented by subclasses for different waves
    public abstract void miniWave(ArrayList<Zombie> li);

    public abstract void waveOne(ArrayList<Zombie> li);

    public abstract void waveTwo(ArrayList<Zombie> li);

    public abstract void waveThree(ArrayList<Zombie> li);

    // its for what I use the print out for
    // A filler method so the endless class can get acess to it
    public void newWave(ArrayList<Zombie> li) {
        System.out.println("Filler method so we can get access to the endless newwave method");
    }

    // Method to increment the wave number
    // No return type or paramters
    public void addWaveNum() {
        this.waveNum++;
    }

    // Getters & Setters Methods

    // Method to get the background image
    public BufferedImage getBackground() {
        return this.background;
    }

    // Method to get the current wave number
    public int getWaveNum() {
        return this.waveNum;
    }

    // Method to get the game mode
    public String getMode() {
        return this.mode;
    }

    public int getMoney() {
        return this.money;
    }

    public void playMusic() {
        this.gameMusic.start();
        this.gameMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic() {
        this.gameMusic.stop();
    }

    public void playWinMusic() {
        this.winnerMusic.start();
        this.winnerMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopWinMusic() {
        this.winnerMusic.stop();
    }

    public BufferedImage getWinner() {
        return this.winner;
    }

}