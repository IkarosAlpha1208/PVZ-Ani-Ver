package maps;

import java.util.ArrayList;

import zombies.*;

public class Endless extends Background {

    public Endless(String name) {
        super(name);
        this.mode = "Endless";
        this.maxZombies = 1;
        this.stat = "day";
    }

    @Override
    public void newWave(ArrayList<Zombie> li) {
        Zombie z;
        System.out.println("Wave Number " + this.waveNum);
        this.maxZombies += 2;
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

            if (waveNum <= 3) {
                z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");
            } else {
                if (i % 2 == 0) {
                    z = new Cone(140, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Cone");
                } else {
                    z = new Normal(100, 10, zombieX + i * 10, rowSpawn, randRow[counter][1], "Normal");
                }

            }
            // Add the zombie object into the list
            li.add(z);
            counter++;

        }
    }

    @Override
    public void miniWave(ArrayList<Zombie> li) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'miniWave'");
    }

    @Override
    public void waveOne(ArrayList<Zombie> li) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'waveOne'");
    }

    @Override
    public void waveTwo(ArrayList<Zombie> li) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'waveTwo'");
    }

    @Override
    public void waveThree(ArrayList<Zombie> li) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'waveThree'");
    }

}
