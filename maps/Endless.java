package maps;

import java.util.ArrayList;
import zombies.*;

public class Endless extends Background {

    // Constructor for Endless class
    public Endless(String name) {
        super(name);
        this.mode = "Endless";
        this.maxZombies = 1;
        this.stat = "day";
    }

    @Override
    // Generate a new wave of zombies
    // Add the zombies to the provided list
    // No return type;
    public void newWave(ArrayList<Zombie> li) {
        Zombie z;

        System.out.println("Wave Number " + this.waveNum); // Print the current wave number
        this.maxZombies += 2; // Increase the maximum number of zombies for the next wave
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
        int zombieChoose = 0;
        for (int i = 0; i < maxZombies; i++) {
            // Putting zombies in random rows
            if (counter == 5)
                counter = 0;

            rowSpawn = randRow[counter][0];

            // Alternate between Normal and Cone, bucket, and giant zombies based on wave
            // number
            if (waveNum <= 3) {
                z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");
            } else if (waveNum > 3 && waveNum <= 6) {
                if (i % 2 == 0) {
                    z = new Cone(280, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Cone");
                } else {
                    z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");
                }
            } else if (waveNum > 6 && waveNum <= 9) {
                if (zombieChoose == 0) {
                    z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");
                } else if (zombieChoose == 1) {
                    z = new Cone(280, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Cone");
                } else {
                    z = new Bucket(650, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Bucket");
                }
                zombieChoose++;
                if (zombieChoose > 2)
                    zombieChoose = 0;

            } else {
                z = new Giant(1500, 15, zombieX + i * 10, rowSpawn, randRow[counter][1], "Giant");

            }

            // Add the zombie object into the list
            li.add(z);
            counter++;
        }
    }

    // Abstract methods from Background class that are not used in Endless
    @Override
    public void miniWave(ArrayList<Zombie> li) {
        // miniWave is not implemented for Endless, so this method throws
        // UnsupportedOperationException
        throw new UnsupportedOperationException("Unimplemented method 'miniWave'");
    }

    @Override
    public void waveOne(ArrayList<Zombie> li) {
        // waveOne is not implemented for Endless, so this method throws
        // UnsupportedOperationException
        throw new UnsupportedOperationException("Unimplemented method 'waveOne'");
    }

    @Override
    public void waveTwo(ArrayList<Zombie> li) {
        // waveTwo is not implemented for Endless, so this method throws
        // UnsupportedOperationException
        throw new UnsupportedOperationException("Unimplemented method 'waveTwo'");
    }

    @Override
    public void waveThree(ArrayList<Zombie> li) {
        // waveThree is not implemented for Endless, so this method throws
        // UnsupportedOperationException
        throw new UnsupportedOperationException("Unimplemented method 'waveThree'");
    }

}
