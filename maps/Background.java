package maps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import plants.Plant;
import zombies.*;
import java.awt.image.BufferedImage;

public abstract class Background {
    protected BufferedImage background;
    protected int waveNum;
    protected int zombieX;
    protected int maxZombies;

    protected HashMap<Integer, Zombie> zombieGrid;

    public Background(String name) {

        try {
            this.background = ImageIO.read(new File("assets/backgrounds/" + name + ".png"));
        } catch (IOException e) {
            System.out.println("CANNOT READ FILE");
        }

        this.waveNum = 4;
        this.zombieX = 720;

    }

    public abstract void miniWave(ArrayList<Zombie> li);

    public abstract void waveOne(ArrayList<Zombie> li);

    public abstract void waveTwo(ArrayList<Zombie> li);

    public abstract void waveThree(ArrayList<Zombie> li);

    public void addWaveNum() {
        this.waveNum++;
    }

    // Getters & Setters Methods

    public BufferedImage getBackground() {
        return this.background;

    }

    public int getWaveNum() {
        return this.waveNum;
    }

    // public abstract boolean checkRow(Plant p);

}
