package plants;
import projectiles.*;
import zombies.Zombie;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

//cherry bomb
public class Cherry extends Plant {

    public Cherry(int x, int y) {
        super(1000, 900, 2, 25, 150, x, y, new Rectangle(x, y, 36, 48));
        this.name = "Cherrybomb";
        this.stat = "bomb";
        this.id = 5;
        this.lastAttack = System.currentTimeMillis();
        this.describe = "Deal damage to zombie around";
        this.exist = true;
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/Cherry.png")));
            this.setImage(img);
        } catch (IOException e) {
            System.out.println("Cant find File");
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastAttack)/1000.0 >= atkSpd) {
            projectileList.add(new bomb(0, atk, "none", this.x - 40, this.y - 15));
            this.exist = false;
        }
    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Cherry(x, y);
        p.yTile = yTi;
        p.grid = grid;
        return p;
    }

    public boolean checkRow(ArrayList<Zombie> li){
        return true;
    }
}