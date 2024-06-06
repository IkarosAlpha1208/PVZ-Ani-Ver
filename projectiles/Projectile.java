package projectiles;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import zombies.*;

public abstract class Projectile {
    private int speed;
    private int damage;
    // -1 is fire, 0 is no effect, 1 is frost, 2 is stun, 3 is penetrate
    private String stat;
    private int x, y;
    private BufferedImage Image;

    public Projectile(int speed, int damage, String stat, int x, int y, BufferedImage image) {
        this.speed = speed;
        this.damage = damage;
        this.stat = stat;
        this.x = x;
        this.y = y;
    }

    public abstract void isHit(ArrayList<Zombie> zList);

    public abstract void hit();

    public void move(){
        this.x += this.speed;
    }

    public String getStat(){
        return this.stat;
    }
}
