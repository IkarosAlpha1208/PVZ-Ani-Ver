package plants;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projectiles.Projectile;


public abstract class Plant {
    protected int hp;
    protected int atk;
    protected int atkSpd;
    protected int cooldown;
    protected int cost;
    protected int stat;
    protected int x, y;
    protected Rectangle hitbox;
    protected BufferedImage img;

    public Plant(int hp, int atk, int atkSpd, int cooldown, int cost, int x, int y, Rectangle hitbox) {
        this.hp = hp;
        this.atk = atk;
        this.atkSpd = atkSpd;
        this.cooldown = cooldown;
        this.cost = cost;
        this.x = x;
        this.y = y;
        this.hitbox = hitbox;
    }

    public abstract void attack(ArrayList<Projectile> projectileList);

    public void takeDmg(int damage) {
        this.hp -= damage;
    }

    public boolean isDead(){
        return this.hp <= 0;
    }

    public Rectangle getRec(){
        return this.hitbox;
    }

    public int getWidth(){
        return this.hitbox.width;
    }

    public int getHeight(){
        return this.hitbox.height;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setCord(int x, int y){
        this.x = x;
        this.y = y;
        this.hitbox.x = x;
        this.hitbox.y = y;
    }

    public BufferedImage getImage(){
        return this.img;
    }

    public void setImage(BufferedImage img){
        this.img = img;
    }

    public abstract Plant createPlant(int x, int y);
}