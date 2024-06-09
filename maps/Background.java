package maps;

import java.io.BufferedReader;
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
    protected boolean newWave;

    protected HashMap<Integer, Zombie> zombieGrid;

    public Background(String name) {

        try {
            this.background = ImageIO.read(new File("assets/backgrounds/" + name + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("CANNOT READ FILE");
        }

        this.waveNum = 1;
        this.newWave = true;

    }

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

    public boolean getNewWave() {
        return this.newWave;
    }

    public int getWaveNum() {
        return this.waveNum;
    }

    public void setNewWave(Boolean b) {
        this.newWave = b;
    }

    public abstract boolean checkRow(Plant p);

}
