package plants;

import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projectiles.Projectile;
import zombies.Zombie;

public abstract class Plant {
    //variables
    protected int hp;
    protected int atk;
    protected double atkSpd;
    protected double cooldown;
    protected int cost;
    protected String name;
    protected int id;
    protected String stat;// mainly for bomb, to let the program know this plant is a bomb
    protected int x, y, yTile;// y tile is for the tile this plant at when we are in a level
    protected long lastAttack;//record the last attack
    protected long lastPlant;//record the last time this plant is puted
    protected Rectangle hitbox;
    protected BufferedImage img;
    protected String grid;//similar to y tile
    protected boolean exist;// if it should be living, mainly for bomb
    protected String describe;// brief description

    //This is the plant class, every plant that extends this have basically the same template
    //usually the only difference is the projectile each plant create, and create plant method
    //I dont think i have to explan more right, i mean there is like 19 plant
    //constructer here
    public Plant(int hp, int atk, double atkSpd, double cooldown, int cost, int x, int y, Rectangle rectangle) {
        //variables
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
    //and return it
    public abstract Plant createPlant(int x, int y, int yTi, String grid);

    //this is for checking if the plant is going to attack
    //and return it
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

    public String getDescribe(){
        return this.describe;
    }

    public String getStat() {
        return this.stat;
    }

    public boolean getExist(){
        return this.exist;
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