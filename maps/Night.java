package maps;

import java.util.ArrayList;
import zombies.*;

public class Night extends Background {
    protected int zombiex; // This variable is not currently used in the class

    // Constructor for Night class
    public Night(String name) {
        super(name); // Call the constructor of the superclass Background
        this.mode = "Level 2"; // Set the game mode to "Level 2"
        this.money = 200;

    }

    @Override
    // Generate a mini wave of zombies
    // Add the zombies to the provided list
    // No return value

    public void miniWave(ArrayList<Zombie> li) {
        this.maxZombies = 3; // Set the maximum number of zombies for the mini wave
        this.waveNum++; // Increment the wave number

        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 }
        };

        int randY = 0;
        int randomIndex = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            randomIndex = (int) (Math.random() * (4 - 0 + 1)) + 0;
            randY = randRow[randomIndex][0];

            Zombie cone = new Cone(280, 10, zombieX + i * 10, randY, randRow[randomIndex][1], "Cone");
            // Add the zombie object into the list
            li.add(cone);
        }
        System.out.println("AMOUNT OF ZOMBIES " + li.size());
    }

    @Override
    // Generate the first wave of zombies
    // Add the zombies to the provided list
    // No return value
    public void waveOne(ArrayList<Zombie> li) {
        System.out.println("Wave Number " + this.waveNum / 2);
        this.maxZombies = 6; // Set the maximum number of zombies for wave one
        this.waveNum++; // Increment the wave number

        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 }
        };

        int rowSpawn = 0;
        int counter = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            if (counter == 5)
                counter = 0;

            rowSpawn = randRow[counter][0];

            // Alternating between Cone and Normal zombies based on index
            Zombie z;
            if (i % 2 == 0) {
                z = new Cone(280, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Cone");
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
    // Generate the second wave of zombies
    // Add the zombies to the provided list
    // No return value

    public void waveTwo(ArrayList<Zombie> li) {
        System.out.println("Wave Number " + this.waveNum / 2);
        this.waveNum++; // Increment the wave number

        this.maxZombies = 10; // Set the maximum number of zombies for wave two

        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 }
        };

        int rowSpawn = 0;
        int counter = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            if (counter == 5)
                counter = 0;

            rowSpawn = randRow[counter][0];

            // Alternating between Cone and Bucket zombies based on index
            Zombie z;
            if (i % 2 == 0) {
                z = new Cone(280, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Cone");
            } else {
                z = new Bucket(650, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Bucket");
            }

            // Add the zombie object into the list
            li.add(z);
            counter++;
        }
    }

    @Override
    // Generate the third wave of zombies
    // Add the zombies to the provided list
    // No return value

    public void waveThree(ArrayList<Zombie> li) {
        System.out.println("Wave Number " + this.waveNum / 2);
        this.waveNum++; // Increment the wave number

        this.maxZombies = 15; // Set the maximum number of zombies for wave three

        int[][] randRow = {
                { 0, 0 },
                { 75, 1 },
                { 150, 2 },
                { 215, 3 },
                { 290, 4 }
        };

        int rowSpawn = 0;
        int counter = 0;
        for (int i = 0; i < maxZombies; i++) {
            // putting them in random rows
            if (counter == 5)
                counter = 0;

            rowSpawn = randRow[counter][0];

            // Alternating between Cone and Bucket zombies based on index
            Zombie z;
            if (i % 2 == 0) {
                z = new Cone(280, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Cone");
            } else {
                z = new Bucket(650, 15, zombieX + i * 10, rowSpawn, randRow[counter][1], "Bucket");
            }

            // Add the zombie object into the list
            li.add(z);
            counter++;
        }
    }
}
