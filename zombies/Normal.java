package zombies;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Normal extends Zombie {

    public Normal(int hp, int speed, int x, int y) {
        super(hp, speed, x, y);
        // TODO Auto-generated constructor stub
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
        this.x = this.x - 2;
        this.hitX = this.hitX - 2;

        // throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
