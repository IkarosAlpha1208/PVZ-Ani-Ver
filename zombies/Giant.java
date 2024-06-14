package zombies;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Giant extends Zombie {

    public Giant(int hp, int damage, int x, int y, int row, String name) {
        super(hp, damage, x, y, row, name);
        // TODO Auto-generated constructor stub
        this.path = "assets/zombies/Giant";
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        this.x = this.x - 2;
        this.hitX = this.hitX - 2;
        setRec();
    }

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

            animateHead();

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

    @Override
    public void setRec() {
        this.hitbox = new Rectangle(this.hitX + 50, this.hitY, this.width, this.height);

    }

}
