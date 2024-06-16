package maps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import zombies.*;

public class Grass extends Background {

    // Constructor for Grass class
    public Grass(String name) {
        super(name); // Call the constructor of the superclass Background
        this.mode = "Level 1"; // Set the game mode to "Level 1"
        this.money = 100;
        this.stat = "day"; // Set the type of this level
        try {
            this.winner = ImageIO.read(new File("assets/backgrounds/winner3.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("File error");
        }

    }

    @Override
    // Method to generate a mini wave of zombies
    // Add the zombies to the provided list
    // Return nothing
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
            // Putting them in random rows
            randomIndex = (int) (Math.random() * (4 - 0 + 1)) + 0;
            randY = randRow[randomIndex][0];

            Zombie z = new Normal(100, 10, zombieX + i * 10, randY, randRow[randomIndex][1], "Normal");

            // Add the zombie object into the list
            li.add(z);
        }
        System.out.println("AMOUNT OF ZOMBIES " + li.size());
    }

    @Override
    // Generate the first wave of zombies
    // Add the zombies to the provided list
    // Return nothing
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
            // Cycle through the rows for each zombie
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
    // Generate the second wave of zombies
    // Add the zombies to the provided list
    // Return nothing
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
            // Cycle through the rows for each zombie
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
    // Generate the third wave of zombies
    // Add the zombies to the provided list
    // Return nothing
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
            // Cycle through the rows for each zombie
            if (counter == 5)
                counter = 0;
            rowSpawn = randRow[counter][0];

            Zombie z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");

            // Add the zombie object into the list
            li.add(z);
            counter++;
        }
    }
}
