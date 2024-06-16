package zombies;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;

import plants.*;

public abstract class Zombie {
    // Fields to store various attributes of the zombie
    protected int hp; // Zombie's health points
    protected int x, y; // Zombie's coordinates
    protected int speed; // Zombie's speed
    protected int atk; // Zombie's attack power
    protected int hitX, hitY; // Coordinates for hitbox
    protected int width, height; // Width and height of zombie
    protected Rectangle hitbox; // Hitbox for collision detection
    protected int headY; // Y coordinate of zombie's head
    protected int headX; // X coordinate of zombie's head

    // States and status flags
    protected boolean isDead; // Whether the zombie is dead
    protected boolean isWalking; // Whether the zombie is walking
    protected boolean isEating; // Whether the zombie is eating
    protected String path; // Path to zombie's images

    protected boolean remove; // Whether the zombie should be removed
    protected int walkingIndex; // Index for walking animation
    protected int dyingIndex; // Index for dying animation
    protected int eatingIndex; // Index for eating animation
    protected BufferedImage zombieImage; // Current image of the zombie
    protected int atkSpd; // Attack speed
    protected long lastAttack; // Time of the last attack
    protected int row; // Row position of the zombie
    protected Plant currentEating; // Plant that the zombie is currently eating
    protected int damage; // Damage the zombie deals

    protected BufferedImage currentAnimation; // Current animation frame
    protected BufferedImage head; // Image of zombie's head
    protected String name; // Name of the zombie
    protected String extenstion = ""; // Extension for image files

    // Constructor to initialize the zombie's attributes
    public Zombie(int hp, int damage, int x, int y, int row, String name) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
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

        // Load the head image
        try {
            this.head = ImageIO.read(new File("assets/zombies/head.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to handle zombie's attack on plants
    // Gets the hashmap of all the plants on the screen
    // Void return type
    public void attack(HashMap<String, Plant> pList) {
        Iterator<String> iter = pList.keySet().iterator();

        while (iter.hasNext()) {
            String currentKey = (String) iter.next();
            Plant p = pList.get(currentKey);
            long currentTime = System.currentTimeMillis();

            // Check if the zombie collides with a plant and can attack
            if (p.getRec().intersects(this.hitbox) && !this.isDead
                    && (currentTime - lastAttack) / 1000 >= atkSpd && !p.isDead()) {
                this.lastAttack = currentTime;
                this.isEating = true;
                this.isWalking = false;
                p.takeDmg(this.damage);
                this.currentEating = p;
            }

            // Check if the plant is dead
            if (p.isDead()) {
                this.isEating = false;
                this.isWalking = true;
                iter.remove();
            }
            // If the plant the zombie was eating is removed
            if (!pList.containsValue(this.currentEating)) {
                this.isEating = false;
                this.isWalking = true;
            }
        }

        // If no plants are left
        if (pList.size() == 0 && !this.isDead && !this.remove) {
            this.isEating = false;
            this.isWalking = true;
        }
    }

    // Abstract method to handle zombie's movement
    public abstract void move();

    // Method to handle zombie's animations
    // No return type or parameters
    public void animation() {
        isDead();

        if (isWalking) {
            // Handle walking animation
            if (this.walkingIndex == 8) {
                this.walkingIndex = 1;
            }

            try {
                zombieImage = ImageIO
                        .read(new File(this.path + "walk" + this.extenstion + this.walkingIndex + ".png"));
                this.currentAnimation = zombieImage;
                this.height = this.currentAnimation.getHeight() - 60;
                this.width = this.currentAnimation.getWidth() - 15;
            } catch (IOException e) {
                System.out.println("----ERROR-----" + this.walkingIndex);
            }
            this.walkingIndex++;
        } else if (isDead) {
            // Handle dying animation
            System.out.println("Hello");
            if (this.dyingIndex == 6) {
                this.isDead = false;
                this.remove = true;
            }
            try {
                zombieImage = ImageIO
                        .read(new File(
                                "assets/zombies/NormalZombie/zombiefall" + this.dyingIndex + ".png"));
                this.currentAnimation = zombieImage;
                this.height = this.currentAnimation.getHeight() - 60;
                this.width = this.currentAnimation.getWidth() - 15;
            } catch (IOException e) {
                System.out.println("----ERROR-----" + this.dyingIndex);
            }
            this.dyingIndex++;
        } else if (isEating) {
            // Handle eating animation
            if (this.eatingIndex == 8) {
                this.eatingIndex = 1;
            }
            try {
                zombieImage = ImageIO
                        .read(new File(this.path + "eat" + this.extenstion + this.eatingIndex + ".png"));
                this.currentAnimation = zombieImage;
                this.height = this.currentAnimation.getHeight() - 60;
                this.width = this.currentAnimation.getWidth() - 15;
            } catch (IOException e) {
                System.out.println("----ERROR-----" + this.eatingIndex);
            }
            this.eatingIndex++;
        }
    }

    // Method to reduce zombie's health when it takes damage
    // Takes the amount of damage
    // Returns nothing void type
    public void takeDamage(int damage) {
        this.hp -= damage;
        if ((this.name.equals("Cone") || this.name.equals("Bucket")) && (this.hp == 100 || this.hp == 50)) {
            this.extenstion += "d";
        }
    }

    // Getters & Setters Methods
    public int index() {
        return this.dyingIndex;
    }

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

    public int getX() {

        if (isDead) {
            return x - 16;
        }
        return x;
    }

    public int getY() {
        if (isDead && !this.name.equals("Giant")) {
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

    // Method to check if the zombie is dead
    //
    public void isDead() {
        // If the zombie health is currently below or equal to zero and the death
        // animation is not finish set the isDead to true and isWalking & is eating to
        // false
        if ((this.hp <= 0 && this.dyingIndex < 6) || this.hp <= 0 && this.dyingIndex < 59) {
            this.isDead = true;
            this.isWalking = false;
            this.isEating = false;
        }
    }

    public boolean getIsDead() {
        return this.isDead;
    }

    public boolean getIsWalking() {
        return this.isWalking;
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

    public String getName() {
        return this.name;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getHp() {
        return this.hp;
    }

    public void setRec() {
        this.hitbox = new Rectangle(this.hitX, this.hitY, this.width, this.height);
    }

    public Rectangle getRec() {
        return this.hitbox;
    }
}
