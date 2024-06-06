package zombies;
import plants.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Normal extends Zombie {

    public Normal(int hp, int speed, int x, int y) {
        super(hp, speed, x, y);
        this.walkingIndex = 1;
        this.dyingIndex = 1;
        this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
        // TODO Auto-generated constructor stub
    }

    public void setX(int x) {
        this.x = x;

    }

    @Override
    public void attack(HashMap<String, Plant> pList) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void move() {
        this.x = this.x - 1;
        this.hitX = this.hitX - 1;
        setRec();

        // throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
