package player;

import java.util.*;
import java.io.*;
import plants.*;

//this is the player, with all the variables and methods for player
public class Player {
    //variables
    private String name;
    private int money;
    private int[] ownPlants = new int[19];
    private ArrayList<Plant> team = new ArrayList<Plant>();
    private int highWave;
    private static int currentPlant;
    private static int sunlight;

    public Player(String fileN, ArrayList<Plant> plants) {
        //constructor
        //read in info from save file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("player/" + fileN));
            this.name = reader.readLine();
            this.money = Integer.parseInt(reader.readLine());
            this.highWave = Integer.parseInt(reader.readLine());
            String line = reader.readLine();
            for (int i = 0; i < ownPlants.length; i++) {
                if (line.indexOf(" ") == -1) {
                    line += " ";
                }
                ownPlants[i] = Integer.parseInt(line.substring(0, line.indexOf(" ")));
                line = line.substring(line.indexOf(" ") + 1);
            }
            line = reader.readLine();
            for (int n = 0; n < 6; n++) {
                if (line.indexOf(" ") == -1) {
                    line += " ";
                }
                int id = Integer.parseInt(line.substring(0, line.indexOf(" ")));
                if (id >= 0) {
                    team.add(plants.get(id).createPlant(0, 0, 0, ""));
                }
                line = line.substring(line.indexOf(" ") + 1);
            }

            System.out.println("Successfully read in the save");
            reader.close();
        } catch (FileNotFoundException e) {
            //if no file is found then create a new one
            System.out.println("Cant find the save, creating a new one...");
            this.name = "player";
            this.money = 100;
            this.highWave = 0;
            this.ownPlants[0] = 1;
            this.ownPlants[1] = 1;
            this.ownPlants[2] = 1;
            this.team = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("cant read file");
        }
    }

    //export the player info into the save file
    public void save(String fileN) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("player/" + fileN));
            writer.println(this.name);
            writer.println(this.money);
            writer.println(this.highWave);
            for (int i = 0; i < ownPlants.length; i++) {
                writer.print(ownPlants[i] + " ");
            }
            writer.println("");
            for (int i = 0; i < 6; i++) {
                if (i >= team.size()) {
                    writer.print(-1 + " ");
                } else {
                    writer.print(team.get(i).getId() + " ");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Cant export to the file");
            e.printStackTrace();
        }
    }

    //getter and setter methods
    public static void resetForLevel() {
        sunlight = 500;
        currentPlant = -1;
    }

    public static int changeSunlight(int num) {
        sunlight += num;
        return sunlight;
    }

    public static int getSunlight() {
        return sunlight;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return this.money;
    }

    public void changeMoney(int money) {
        this.money += money;
    }

    public void setHighWave(int num) {
        if (this.highWave < num)
            this.highWave = num;
    }

    public int getHighWave() {
        return this.highWave;
    }

    public static void setCurrentPlant(int p) {
        currentPlant = p;
    }

    public static int getCurrentPlant() {
        return currentPlant;
    }

    public void addTeam(Plant p) {
        this.team.add(p);
    }

    public void removeTeam(int i) {
        this.team.remove(i);
    }

    public ArrayList<Plant> getTeam() {
        return this.team;
    }

    public Plant getTeam(int index){
        return this.team.get(index);
    }

    public void addPlant(int id){
        this.ownPlants[id] += 1;
    }

    public void removePlant(int id) {
        this.ownPlants[id] -= 1;
    }

    public int getOwnPlant(int id) {
        return this.ownPlants[id];
    }

    public int plantSize(){
        return this.ownPlants.length;
    }
}
