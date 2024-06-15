package projectiles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import zombies.Zombie;

public class Lawnmower {
    // Fields to store various attributes of the lawnmower
    private int row; // Row position of the lawnmower
    private BufferedImage image; // Image of the lawnmower
    private int[] randY = { 55, 130, 200, 275, 340 }; // Y coordinates for different rows
    private int y; // Y coordinate of the lawnmower
    private Rectangle hitbox; // Hitbox for collision detection
    private int x = 120; // Initial X coordinate of the lawnmower
    private boolean active = false; // Whether the lawnmower is active

    // Constructor to initialize the lawnmower's attributes
    public Lawnmower(int row) {
        this.row = row;
        this.y = randY[row];

        // Load the lawnmower image
        try {
            this.image = ImageIO.read(new File("assets/projectiles/LawnMower.png"));
        } catch (IOException e) {
            System.out.println("ERROR reading");
        }

        // Initialize the hitbox based on the image dimensions
        this.hitbox = new Rectangle(this.x, this.y, this.image.getWidth(), this.image.getHeight());
    }

    // Method to check and handle intersections with zombies
    // Gets a Arraylist of all of the zombies on the screen
    // Returns nothing
    public void intersection(ArrayList<Zombie> list) {
        for (int i = 0; i < list.size(); i++) {
            // Check if the lawnmower's hitbox intersects with the zombie's hitbox in the
            // same row
            if (list.get(i).getRec().intersects(hitbox) && list.get(i).getRow() == this.row) {
                if (active) {
                    // If the lawnmower is active, deal damage to the zombie
                    list.get(i).takeDamage(list.get(i).getHp());
                } else if (!active) {
                    // If the lawnmower is not active, activate it
                    this.active = true;
                }
            }
        }
    }

    // Method to move the lawnmower
    // No return type or paramters
    public void move() {
        this.x += 4; // Increase the X coordinate to move the lawnmower to the right
        setRec(); // Update the hitbox position
    }

    // Getters & Setters
    public boolean getActive() {
        return this.active;
    }

    public void setRec() {
        hitbox = new Rectangle(this.x, this.y, this.image.getWidth(), this.image.getHeight());
    }

    public int getRow() {
        return this.row;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public BufferedImage getImage() {
        return this.image;
    }
}