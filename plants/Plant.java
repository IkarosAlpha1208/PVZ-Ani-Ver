package plants;

import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projectiles.Projectile;
import zombies.Zombie;

public abstract class Plant {
    protected int hp;
    protected int atk;
    protected int atkSpd;
    protected int cooldown;
    protected int cost;
    protected int stat;
    protected int x, y, yTile;
    protected long lastAttack;
    protected Rectangle hitbox;
    protected BufferedImage img;
    protected boolean isDead;

    public Plant(int hp, int atk, int atkSpd, int cooldown, int cost, int x, int y, Rectangle hitbox) {
        this.hp = hp;
        this.atk = atk;
        this.atkSpd = atkSpd;
        this.cooldown = cooldown;
        this.cost = cost;
        this.x = x;
        this.y = y;
        this.hitbox = hitbox;
        this.isDead = false;
        this.lastAttack = System.currentTimeMillis();
    }

    public abstract void attack(ArrayList<Projectile> projectileList, ArrayList<Zombie> zList);

    public void takeDmg(int damage) {
        this.hp -= damage;
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public void setIsDead(boolean b) {
        this.isDead = b;
    }

    public int getHealth() {
        return this.hp;
    }

    public Rectangle getRec() {
        return this.hitbox;
    }

    public int getWidth() {
        return this.hitbox.width;
    }

    public int getHeight() {
        return this.hitbox.height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int yTile() {
        return (this.y - 72) / 65;
    }

    public void setCord(int x, int y) {
        this.x = x;
        this.y = y;
        this.hitbox.x = x;
        this.hitbox.y = y;
    }

    public BufferedImage getImage() {
        return this.img;
    }

    public void setImage(BufferedImage img) {
        this.img = img;
    }

    public abstract Plant createPlant(int x, int y, int yTi);

}