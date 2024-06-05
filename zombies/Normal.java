package zombies;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Normal extends Zombie {

    public Normal(int hp, int atk, int atkspd, int speed, int x, int y) {
        super(hp, atk, atkspd, speed, x, y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;

    }

    public void setX(int x) {
        this.x = x;

    }

    @Override
    public void attack() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void move() {
        this.x = this.x - 1;
        this.hitX = this.hitX - 1;

        // throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
