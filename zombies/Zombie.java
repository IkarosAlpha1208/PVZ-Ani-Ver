package zombies;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Zombie {
    protected int hp;
    protected int x, y;
    protected int speed;
    protected int atk;
    protected int hitX, hitY;
    protected int width, height;
    // The y cord of the head
    protected int headY;

    protected int headX;

    protected boolean isDead;
    protected boolean isWalking;
    protected int walkingIndex;
    protected int dyingIndex;
    protected BufferedImage zombieImage;

    protected BufferedImage currentAnimation;
    protected BufferedImage head;
    // 0 is not being effected, 1 is being slowed, 2 is being stuned
    private int stat;

    public Zombie(int hp, int speed, int x, int y) {
        this.hp = hp;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.headY = y + 20;
        this.headX = x;
        this.isWalking = true;
        this.isDead = false;

        // Making the hitbox closer to the zombie
        this.hitY = y + 58;
        this.hitX = x + 15;
        try {
            this.head = ImageIO.read(new File("assets/zombies/head.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public abstract void attack();

    public abstract void move();

    public void animation() {
        // TODO Auto-generated method stub

        // Need to add methods to see if the zombie is currently eating or dying here.
        // HAVE TO WAIT FOR DAIVD

        if (isWalking) {
            if (this.walkingIndex == 8) {
                this.walkingIndex = 1;

            }

            try {
                zombieImage = ImageIO
                        .read(new File("assets/zombies/NormalZombie/zombiewalk" + this.walkingIndex + ".png"));
                this.currentAnimation = zombieImage;
                this.height = this.currentAnimation.getHeight() - 60;
                this.width = this.currentAnimation.getWidth() - 15;

            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("----ERROR-----" + this.walkingIndex);

            }

            this.walkingIndex++;

        }

        else if (isDead) {

            if (this.dyingIndex == 6) {
                this.isDead = false;

            }
            try {
                zombieImage = ImageIO
                        .read(new File("assets/zombies/NormalZombie/zombiefall" + this.dyingIndex + ".png"));
                this.currentAnimation = zombieImage;
                this.height = this.currentAnimation.getHeight() - 60;
                this.width = this.currentAnimation.getWidth() - 15;

            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("----ERROR-----" + this.dyingIndex);

            }
            this.dyingIndex++;
            animateHead();

        }

    }

    public void animateHead() {
        this.headY += 8;
        this.headX -= 4;

    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        System.out.println("hp: " + this.hp);
    }

    // public boolean isDead() {
    // return this.hp <= 0;
    // }

    // Getters & Setters Methods

    public int getHeadY() {
        return this.headY;
    }

    public int getHeadX() {
        return this.headX;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getX() {
        return x;
    }

    public int getY() {

        if (isDead) {
            return y + 44;
        }

        return y;
    }

    public void setX(int x) {
        this.x = x;
        this.headX = x;

    }

    public boolean getIsDead() {
        return this.isDead;

    }

    // HAve the hitbox closer to the size of the zombie
    // public abstract void setAnimation(BufferedImage i);

    public BufferedImage getAnimation() {
        return this.currentAnimation;
    }

    // public abstract void animateHead();

    public BufferedImage getHead() {
        return this.head;
    }

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
