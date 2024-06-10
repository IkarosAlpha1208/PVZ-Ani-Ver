package zombies;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import javax.imageio.ImageIO;

import plants.*;

public abstract class Zombie {
    protected int hp;
    protected int x, y;
    protected int speed;
    protected int atk;
    protected int hitX, hitY;
    protected int width, height;
    protected Rectangle hitbox;
    // The y cord of the head
    protected int headY;

    protected int headX;

    protected boolean isDead;
    protected boolean isWalking;
    protected boolean isEating;

    protected boolean remove;
    protected int walkingIndex;
    protected int dyingIndex;
    protected int eatingIndex;
    protected BufferedImage zombieImage;
    protected int atkSpd;
    protected long lastAttack;
    protected int row;

    protected BufferedImage currentAnimation;
    protected BufferedImage head;
    // 0 is not being effected, 1 is being slowed, 2 is being stuned
    private int stat;

    public Zombie(int hp, int speed, int x, int y, int row) {
        this.hp = hp;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.row = row;
        this.headY = y + 20;
        this.headX = x;
        this.isWalking = true;
        this.isDead = false;
        this.isEating = false;
        this.remove = false;
        this.walkingIndex = 1;
        this.dyingIndex = 1;
        this.eatingIndex = 1;
        this.atkSpd = 3;
        this.lastAttack = System.currentTimeMillis();

        // Making the hitbox closer to the zombie
        this.hitY = y + 58;
        this.hitX = x + 15;
        this.hitbox = new Rectangle(this.hitX, this.hitY, this.width, this.height);

        try {
            this.head = ImageIO.read(new File("assets/zombies/head.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void attack(HashMap<String, Plant> pList);

    public abstract void move();

    public void animation() {

        // Need to add methods to see if the zombie is currently eating or dying here.
        // HAVE TO WAIT FOR DAIVD
        isDead();

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
                System.out.println("----ERROR-----" + this.walkingIndex);

            }
            // System.out.println("++++++++++++++++" + this.wal);

            this.walkingIndex++;

        }

        else if (isDead) {

            if (this.dyingIndex == 6) {
                this.isDead = false;
                this.remove = true;

            }
            try {
                zombieImage = ImageIO
                        .read(new File("assets/zombies/NormalZombie/zombiefall" + this.dyingIndex + ".png"));
                this.currentAnimation = zombieImage;
                this.height = this.currentAnimation.getHeight() - 60;
                this.width = this.currentAnimation.getWidth() - 15;

            } catch (IOException e) {
                System.out.println("----ERROR-----" + this.dyingIndex);

            }

            this.dyingIndex++;

            animateHead();

        } else if (isEating) {

            if (this.eatingIndex == 8) {
                this.eatingIndex = 1;

            }
            try {
                zombieImage = ImageIO
                        .read(new File("assets/zombies/NormalZombie/zombieeating" + this.eatingIndex + ".png"));
                this.currentAnimation = zombieImage;
                this.height = this.currentAnimation.getHeight() - 60;
                this.width = this.currentAnimation.getWidth() - 15;

            } catch (IOException e) {
                System.out.println("----ERROR-----" + this.eatingIndex);

            }

            this.eatingIndex++;

        }

    }

    public void animateHead() {
        this.headY += 3;
        // this.headX -= 1;

    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        // System.out.println("hp: " + this.hp);
    }

    // Getters & Setters Methods

    public int getRow() {
        return this.row;
    }

    public boolean getRemove() {
        return this.remove;
    }

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
        if (isDead) {
            return x - 16;
        }
        return x;
    }

    public int getY() {

        if (isDead) {
            return y + 44;
        }

        return y;
    }

    public int yTile() {
        return (this.hitY - 82) / 72;
    }

    public void setX(int x) {
        this.x = x;
        this.headX = x;

    }

    public void isDead() {
        if (this.hp <= 0 && this.dyingIndex < 6) {
            this.isDead = true;
            this.isWalking = false;
            // this.dyingIndex = 1;

        }
    }

    public boolean getIsDead() {
        return this.isDead;

    }

    public boolean getIsEating() {
        return this.isEating;
    }

    public BufferedImage getAnimation() {
        return this.currentAnimation;
    }

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

    public void setRec() {
        this.hitbox = new Rectangle(this.hitX, this.hitY, this.width, this.height);
    }

    public Rectangle getRec() {
        return this.hitbox;
    }
}
