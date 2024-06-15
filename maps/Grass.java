package maps;

import java.util.ArrayList;
import java.util.HashMap;
import zombies.*;

public class Grass extends Background {

    public Grass(String name) {
        super(name);
        this.mode = "Level 1";

    }

    @Override
    public void miniWave(ArrayList<Zombie> li) {
        this.maxZombies = 3;
        this.waveNum++;

        System.out.println("Helo");

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

            Zombie z = new Normal(100, 10, zombieX + i * 10, randY, randRow[randomIndex][1], "Giant");
            // zombieGrid.put(randRow[randomIndex][1], z);

            // Add the zombie object into the list
            li.add(z);

        }
        System.out.println("AMOUNT OF ZOMBIES " + li.size());

    }

    @Override
    public void waveOne(ArrayList<Zombie> li) {

        System.out.println("Wave Number " + this.waveNum / 2);
        this.maxZombies = 6;

        this.waveNum++;
        // this.zombieGrid = new HashMap<Integer, Zombie>();

        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 } };

        int rowSpawn = 0;
        int counter = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            if (counter == 5)
                counter = 0;

            rowSpawn = randRow[counter][0];

            Zombie z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");

            // Add the zombie object into the list
            li.add(z);
            counter++;

        }
        System.out.println("AMOUNT OF ZOMBIES " + li.size());

    }

    @Override
    public void waveTwo(ArrayList<Zombie> li) {
        System.out.println("Wave Number " + this.waveNum / 2);
        this.waveNum++;
        this.zombieGrid = new HashMap<Integer, Zombie>();

        this.maxZombies = 10;
        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 } };
        int rowSpawn = 0;
        int counter = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            if (counter == 5)
                counter = 0;

            rowSpawn = randRow[counter][0];

            Zombie z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");

            // Add the zombie object into the list
            li.add(z);
            counter++;

        }

    }

    @Override
    public void waveThree(ArrayList<Zombie> li) {
        System.out.println("Wave Number " + this.waveNum / 2);
        this.waveNum++;
        this.zombieGrid = new HashMap<Integer, Zombie>();

        this.maxZombies = 15;
        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 } };
        int rowSpawn = 0;
        int counter = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            if (counter == 5)
                counter = 0;
            rowSpawn = randRow[counter][0];

            Zombie z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");

            // Add the zombie object into the list
            li.add(z);
            counter++;

        }

    }

    // @Override
    // public boolean checkRow(Plant p) {
    // return this.zombieGrid.containsKey(p.getGrid() % 10);
    // }

}
