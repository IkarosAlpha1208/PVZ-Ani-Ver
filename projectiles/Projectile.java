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
    protected BufferedImage img;

    public Projectile(int speed, int damage, String stat, int x, int y) {
        this.speed = speed;
        this.damage = damage;
        this.stat = stat;
        this.x = x;
        this.y = y;
    }

    public abstract boolean isHit(ArrayList<Zombie> zList, int x, int y);

    public abstract void hit(Object o);

    public void move(){
        this.x += this.speed;
        hitbox.x = this.x;
    }

    public String getStat(){
        return this.stat;
    }

    public BufferedImage getImage(){
        return this.img;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth(){
        return this.hitbox.width;
    }

    public int getHeight(){
        return this.hitbox.height;
    }
}
