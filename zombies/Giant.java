package zombies;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// Giant class for the giant Zombie, is the subclass of Zombie Class
public class Giant extends Zombie {
    // Constructor, gets the hp,damage, x, y, row, and name and pass most of it to
    // the zombie Constructor
    public Giant(int hp, int damage, int x, int y, int row, String name) {
        super(hp, damage, x, y, row, name);
        // Path to the giant zombie folder
        this.path = "assets/zombies/Giant";
    }

    // Movement method, makes the zombie move 2 pixels
    // no return value or paramters
    // The hitbox moves along with the zombie
    @Override
    public void move() {
        this.x = this.x - 2;
        this.hitX = this.hitX - 2;
        setRec();
    }

    // Method to handle zombie's animations
    // No return type or parameters
    // Has its own animation method cause the giant sprite came from a different
    // pack
    @Override
    public void animation() {

        isDead();

        if (isWalking) {
            if (this.walkingIndex == 49) {
                this.walkingIndex = 1;
            }

            try {
                zombieImage = ImageIO
                        .read(new File(this.path + "/walking/walking" + this.walkingIndex + ".png"));
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

            if (this.dyingIndex == 59) {
                this.isDead = false;
                this.remove = true;

            }
            try {
                zombieImage = ImageIO
                        .read(new File(
                                this.path + "/dying/dying" + this.dyingIndex + ".png"));
                this.currentAnimation = zombieImage;
                this.height = this.currentAnimation.getHeight() - 60;
                this.width = this.currentAnimation.getWidth() - 15;

            } catch (IOException e) {
                System.out.println("----ERROR-----" + this.dyingIndex);

            }

            this.dyingIndex++;

        } else if (isEating) {

            if (this.eatingIndex == 34) {
                this.eatingIndex = 1;

            }
            try {
                zombieImage = ImageIO
                        .read(new File(this.path + "/attacking/attacking" + this.eatingIndex + ".png"));
                this.currentAnimation = zombieImage;
                this.height = this.currentAnimation.getHeight() - 60;
                this.width = this.currentAnimation.getWidth() - 15;

            } catch (IOException e) {
                System.out.println("----ERROR-----" + this.eatingIndex);

            }

            this.eatingIndex++;

        }

    }

    // Getters & Setters methods
    @Override
    public void setRec() {
        this.hitbox = new Rectangle(this.hitX + 50, this.hitY, this.width, this.height);

    }

}
