package zombies;

import java.util.HashMap;

import plants.Plant;

// Class for the cone Zombie   subclass of Zombie Class
public class Cone extends Zombie {
    // Constructor, gets the hp,damage, x, y, row, and name and pass most of it to
    // the zombie Constructor
    public Cone(int hp, int damage, int x, int y, int row, String name) {
        super(hp, damage, x, y, row, name);
        // Path to the cone zombie folder
        this.path = "assets/zombies/ConeheadZombie/conehead";

    }

    // Movement method, makes the zombie move 2 pixels
    // no return value or paramters
    // The hitbox moves along with the zombie
    @Override
    public void move() {
        // TODO Auto-generated method stub
        this.x = this.x - 2;
        this.hitX = this.hitX - 2;
        setRec();

    }

}