package zombies;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public abstract class Zombie {
    protected int hp;
    protected int x, y;
    protected int speed;
    protected int atk;
    protected int hitX, hitY;
    protected int width, height;

    protected BufferedImage currentAnimation;

    // 0 is not being effected, 1 is being slowed, 2 is being stuned
    private int stat;

    public Zombie(int hp, int speed, int x, int y) {
        this.hp = hp;
        this.speed = speed;
        this.x = x;
        this.y = y;
        // Making the hitbox closer to the zombie
        this.hitY = y + 58;
        this.hitX = x + 15;
    }

    public abstract void attack();

    public abstract void move();

    public void takeDamage(int damage) {
        this.hp -= damage;
        System.out.println("hp: " + this.hp);
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;

    }

    // HAve the hitbox closer to the size of the zombie
    public void setAnimation(BufferedImage i) {
        this.currentAnimation = i;
        this.height = this.currentAnimation.getHeight() - 60;
        this.width = this.currentAnimation.getWidth() - 15;

    }

    public BufferedImage getAnimation() {

        return this.currentAnimation;

    }

    // public void setHitX(int x) {
    // this.hitX = x;
    // }

    // public void setHitY(int y) {
    // this.hitY = y;
    // }

    // public void setWidth(int width) {
    // this.width = this.currentAnimation.getWidth();
    // }

    // public void setHeight(int height) {
    // this.height = this.currentAnimation.getHeight() - 50;
    // }

    public int getHitX() {
        return this.hitX;
    }

    public int getHitY() {
        return this.hitY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
