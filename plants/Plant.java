package plants;

import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projectiles.Projectile;
import zombies.Zombie;

public abstract class Plant {
    protected int hp;
    protected int atk;
    protected double atkSpd;
    protected double cooldown;
    protected int cost;
    protected String name;
    protected int id;
    protected String stat;
    protected int x, y, yTile;
    protected long lastAttack;
    protected long lastPlant;
    protected Rectangle hitbox;
    protected BufferedImage img;
    protected String grid;
    protected int aniCounter;
    protected boolean attacking;
    protected double exist;

    public Plant(int hp, int atk, double atkSpd, double cooldown, int cost, int x, int y, Rectangle rectangle) {
        this.hp = hp;
        this.atk = atk;
        this.atkSpd = atkSpd;
        this.cooldown = cooldown;
        this.cost = cost;
        this.x = x;
        this.y = y;
        this.hitbox = rectangle;
    }

    //Either gonna shoot projectile, or just hit the zombie when collide with it(To be honest there only 1 plant that do this)
    public abstract void attack(ArrayList<Projectile> projectileList);

    //create a duplicate of this plant
    public abstract Plant createPlant(int x, int y, int yTi, String grid);

    //this is for checking if the plant is going to attack
    public abstract boolean checkRow(ArrayList<Zombie> li);

    //getter and setter methods
    public void takeDmg(int damage) {
        this.hp -= damage;
    }

    public boolean isDead() {
        return this.hp <= 0;
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

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public String getStat() {
        return this.stat;
    }

    public int getCost(){
        return this.cost;
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

    public int getGrid() {
        return Integer.parseInt(this.grid);
    }

    //return if this is done cooldown
    public boolean isCooldown(){
        return (System.currentTimeMillis() - this.lastPlant)/1000 >= this.cooldown;
    }

    public void setCooldown(){
        this.lastPlant = System.currentTimeMillis();
    }
}