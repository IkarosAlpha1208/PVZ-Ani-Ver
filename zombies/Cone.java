package zombies;

import java.util.HashMap;

import plants.Plant;

public class Cone extends Zombie {

    public Cone(int hp, int damage, int x, int y, int row, String name) {
        super(hp, damage, x, y, row, name);
        this.path = "assets/zombies/ConeheadZombie/conehead";

    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        this.x = this.x - 3;
        this.hitX = this.hitX - 3;
        setRec();

    }

}