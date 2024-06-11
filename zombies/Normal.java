package zombies;

import plants.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

public class Normal extends Zombie {

    public Normal(int hp,int damage, int x, int y, int row) {
        super(hp,damage, x, y, row);

        this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
        this.path = "assets/zombies/NormalZombie/zombie";
    }

    public void setX(int x) {
        this.x = x;

    }

        @Override
    public void move() {
        this.x = this.x - 3;
        this.hitX = this.hitX - 3;
        setRec();

        // throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
