package plants;

import projectiles.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class Sunflower extends Plant {
    public Sunflower(int x, int y) {
        super(50, 25, 12.5, 7.5, 50, x, y, new Rectangle(x, y, 36, 48));
        this.name = "Sunflower";
        this.stat = "sun";
        this.id = 0;
        this.lastAttack = System.currentTimeMillis();
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/Sunflower.png")));
            this.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastAttack)/1000.0 >= atkSpd) {
            lastAttack = currentTime;
            projectileList.add(new Sunlight(0, atk, stat, this.x - 10, this.y + 10));
        }
    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Sunflower(x, y);
        p.yTile = yTi;
        p.grid = grid;

        return p;
    }
}