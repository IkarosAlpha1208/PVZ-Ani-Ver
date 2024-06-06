package projectiles;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import zombies.*;

public abstract class Projectile {
    protected int speed;
    protected int damage;
    protected String stat;
    protected int x, y;
    protected Rectangle hitbox;
    protected BufferedImage Image;

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
        hitbox.x = this.x;
    }

    public String getStat(){
        return this.stat;
    }
}
