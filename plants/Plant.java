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

    public Plant(int hp, int atk, double atkSpd, double cooldown, int cost, int x, int y, Rectangle hitbox) {
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

    public abstract Plant createPlant(int x, int y, int yTi, String grid);

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

    //check if there is zombie on the same row as the current plant
    public boolean checkRow(ArrayList<Zombie> li) {
        for (int i = 0; i < li.size(); i++) {
            if (Integer.parseInt(this.grid) % 10 == li.get(i).getRow()) {
                return true;
            }

        }
        return false;
    }

    //return if this is done cooldown
    public boolean isCooldown(){
        return (System.currentTimeMillis() - this.lastPlant)/1000 >= this.cooldown;
    }

    public void setCooldown(){
        this.lastPlant = System.currentTimeMillis();
    }
}