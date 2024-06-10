package maps;

import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import plants.Plant;
import zombies.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Grass extends Background {
    protected int maxZombies;
    protected int zombieX;

    public Grass(String name) {
        super(name);
        this.waveNum = 1;
        this.maxZombies = 5;
        this.zombieX = 650;

    }

    @Override
    public void waveOne(ArrayList<Zombie> li) {

        System.out.println("Wave Number " + this.waveNum);
        this.waveNum++;
        this.zombieGrid = new HashMap<Integer, Zombie>();

        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 } };
        int randY = 0;
        int randomIndex = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            randomIndex = (int) (Math.random() * (4 - 0 + 1)) + 0;
            randY = randRow[randomIndex][0];

            Zombie z = new Normal(100, 10, zombieX + i * 40, randY, randRow[randomIndex][1]);
            // zombieGrid.put(randRow[randomIndex][1], z);

            // Add the zombie object into the list
            li.add(z);

        }
        System.out.println("AMOUNT OF ZOMBIES " + li.size());

    }

    @Override
    public void waveTwo(ArrayList<Zombie> li) {
        System.out.println("Wave Number " + this.waveNum);
        this.waveNum++;
        this.zombieGrid = new HashMap<Integer, Zombie>();

        this.maxZombies += 5;
        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 } };
        int randY = 0;
        int randomIndex = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            randomIndex = (int) (Math.random() * (4 - 0 + 1)) + 0;
            randY = randRow[randomIndex][0];

            Zombie z = new Normal(100, 10, zombieX + i * 40, randY, randRow[randomIndex][1]);
            zombieGrid.put(randRow[randomIndex][1], z);

            // Add the zombie object into the list
            li.add(z);

        }
        System.out.println("AMOUNT OF ZOMBIES " + li.size());

    }

    @Override
    public void waveThree(ArrayList<Zombie> li) {
        System.out.println("Wave Number " + this.waveNum);
        this.waveNum++;
        this.zombieGrid = new HashMap<Integer, Zombie>();

        this.maxZombies += 5;
        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 } };
        int randY = 0;
        int randomIndex = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            randomIndex = (int) (Math.random() * (4 - 0 + 1)) + 0;
            randY = randRow[randomIndex][0];

            Zombie z = new Normal(100, 10, zombieX + i * 40, randY, randRow[randomIndex][1]);
            zombieGrid.put(randRow[randomIndex][1], z);

            // Add the zombie object into the list
            li.add(z);

        }
        System.out.println("AMOUNT OF ZOMBIES " + li.size());

    }

    @Override
    public boolean checkRow(Plant p) {
        return this.zombieGrid.containsKey(p.getGrid() % 10);
    }

}
