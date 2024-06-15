package projectiles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import zombies.Zombie;

public class Lawnmower {
    private int row;
    private BufferedImage image;
    private int[] randY = { 55, 130,
            200,
            275,
            340, };
    private int y;
    private Rectangle hitbox;
    private int x = 120;
    private boolean active = false;

    public Lawnmower(int row) {
        this.row = row;
        this.y = randY[row];

        try {
            this.image = ImageIO.read(new File("assets/projectiles/LawnMower.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("ERRROR reading");
        }

        this.hitbox = new Rectangle(this.x, this.y, this.image.getWidth(), this.image.getHeight());

    }

    public void intersection(ArrayList<Zombie> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRec().intersects(hitbox) && list.get(i).getRow() == this.row) {
                if (active)
                    list.get(i).takeDamage(list.get(i).getHp());
                else if (!active)
                    this.active = true;

            }

        }

    }

    public void move() {
        this.x += 4;
        setRec();
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
