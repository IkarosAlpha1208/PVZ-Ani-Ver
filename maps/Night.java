package maps;

import java.util.ArrayList;

import zombies.*;

public class Night extends Background {
    protected int zombiex;

    public Night(String name) {
        super(name);
        this.mode = "Level 2";

        // TODO Auto-generated constructor stub
    }

    public void miniWave(ArrayList<Zombie> li) {
        this.maxZombies = 3;
        this.waveNum++;

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

            Zombie cone = new Cone(140, 10, zombieX + i * 10, randY, randRow[randomIndex][1], "Cone");
            // zombieGrid.put(randRow[randomIndex][1], z);

            // Add the zombie object into the list
            li.add(cone);

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
        Zombie z;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            if (counter == 5)
                counter = 0;

            rowSpawn = randRow[counter][0];

            if (i % 2 == 0) {
                z = new Cone(140, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Cone");
            } else {
                z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");
            }

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

        this.maxZombies = 10;
        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 } };
        int rowSpawn = 0;
        int counter = 0;
        Zombie z;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            if (counter == 5)
                counter = 0;

            rowSpawn = randRow[counter][0];

            if (i % 2 == 0) {
                z = new Cone(140, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Cone");
            } else {
                z = new Bucket(125, 15, zombieX + i * 10, rowSpawn, randRow[counter][1], "Bucket");
            }

            // Add the zombie object into the list
            li.add(z);
            counter++;

        }

    }

    @Override
    public void waveThree(ArrayList<Zombie> li) {
        System.out.println("Wave Number " + this.waveNum / 2);
        this.waveNum++;

        this.maxZombies = 15;
        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 } };
        int rowSpawn = 0;
        int counter = 0;
        Zombie z;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            if (counter == 5)
                counter = 0;

            rowSpawn = randRow[counter][0];

            if (i % 2 == 0) {
                z = new Cone(140, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Cone");
            } else {
                z = new Bucket(125, 15, zombieX + i * 10, rowSpawn, randRow[counter][1], "Bucket");
            }

            // Add the zombie object into the list
            li.add(z);
            counter++;

        }

    }
}
