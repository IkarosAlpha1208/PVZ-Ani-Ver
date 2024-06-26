package plants;
import projectiles.*;
import zombies.Zombie;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

// repeater shoot out peat
public class Repeater extends Plant {

    public Repeater(int x, int y) {
        super(50, 10, 0.75, 10, 200, x, y, new Rectangle(x, y, 36, 48));
        this.name = "PeaShooter";
        this.stat = "pea";
        this.id = 10;
        this.lastAttack = 0;
        this.describe = "Shoot 2x faster than pea shooter";
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/Repeater.png")));
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
            projectileList.add(new Pea(5, atk, "none", this.x - 10, this.y + 10));
        }
    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Repeater(x, y);
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