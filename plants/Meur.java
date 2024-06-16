package plants;
import projectiles.*;
import zombies.Zombie;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class Meur extends Plant {

    public Meur(int x, int y) {
        super(50, 10, 1.5, 7.5, 325, x, y, new Rectangle(x, y, 36, 48));
        this.name = "Kim Meursault";
        this.stat = "penetrate";
        this.id = 18;
        this.lastAttack = 0;
        this.describe = "Can penetrate enemy";
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/Meur.png")));
            this.setImage(img);
        } catch (IOException e) {
            System.out.println("Cant find File");
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastAttack)/1000.0 >= atkSpd) {
            lastAttack = currentTime;
            projectileList.add(new slashP(10, atk, "none", this.x - 10, this.y + 10));
        }
    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Meur(x, y);
        p.yTile = yTi;
        p.grid = grid;
        return p;
    }

    public boolean checkRow(ArrayList<Zombie> li){
        for (int i = 0; i < li.size(); i++) {
            if (Integer.parseInt(this.grid) % 10 == li.get(i).getRow()) {
                return true;
            }

        }
        return false;
    }
}