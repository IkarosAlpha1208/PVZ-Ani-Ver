package plants;
import projectiles.*;
import zombies.Zombie;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class DawnClair extends Plant {

    public DawnClair(int x, int y) {
        super(300, 20, 0.75, 15, 350, x, y, new Rectangle(x, y, 36, 48));
        this.name = "Crying Child";
        this.stat = "";
        this.id = 11;
        this.lastAttack = 0;
        this.describe = "Shoot flame sword that goes through enemy";
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/DawnClairAttack.png")));
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
            projectileList.add(new flameSword(10, atk, "none", this.x - 10, this.y + 10));
        }
    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new DawnClair(x, y);
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