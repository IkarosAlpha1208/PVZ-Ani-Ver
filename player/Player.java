package player;
import java.util.*;
import java.io.*;
import plants.*;

public class Player {
    private String name;
    private int money;
    private ArrayList<Plant> ownPlants;
    private static int sunlight;

    public Player() {
        this.name = "Player";
        this.money = 100;
        ownPlants = new ArrayList<Plant>();
    }

    public static void resetSunlight(){
        sunlight = 200;
    }

    public static int changeSunlight(int num){
        sunlight += num;
        return sunlight;
    }

    public static int getSunlight(){
        return sunlight;
    }
}
