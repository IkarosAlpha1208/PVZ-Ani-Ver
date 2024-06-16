package projectiles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.sound.sampled.*;

import zombies.*;

// projectile class, is shooted by plant, and the main use is to hit zombie
public abstract class Projectile {
    //variables
    protected int speed;
    protected int damage;
    protected String stat;
    protected int x, y;
    protected Rectangle hitbox;
    protected BufferedImage img;
    protected Clip hitSound;
    protected double exist;

    public Projectile(int speed, int damage, String stat, int x, int y) {
        this.speed = speed;
        this.damage = damage;
        this.stat = stat;
        this.x = x;
        this.y = y;
    }

    // is just for checking if the projectile hit a zombie
    // and the return is determining whether this projectile
    // gonna be remove or no
    public abstract boolean isHit(ArrayList<Zombie> zList, int x, int y);

    //do dmg to zombie, some projectile have effects
    //it will also be included here
    public abstract void hit(Zombie z);

    // getter and setter
    public void move() {
        this.x += this.speed;
        hitbox.x = this.x;
    }

    public String getStat() {
        return this.stat;
    }

    public BufferedImage getImage() {
        return this.img;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.hitbox.width;
    }

    public int getHeight() {
        return this.hitbox.height;
    }
}
