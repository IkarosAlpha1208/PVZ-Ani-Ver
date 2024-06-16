package plants;
import projectiles.*;
import zombies.Zombie;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class Jalepeno extends Plant {

    public Jalepeno(int x, int y) {
        super(1000, 900, 2, 25, 125, x, y, new Rectangle(x, y, 36, 48));
        this.name = "Pepper";
        this.stat = "bomb";
        this.id = 4;
        this.lastAttack = System.currentTimeMillis();
        this.describe = "Hit all zombie on a line";
        this.exist = true;
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/Jalepno.png")));
            this.setImage(img);
        } catch (IOException e) {
            System.out.println("Cant find File");
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastAttack)/1000.0 >= atkSpd) {
            projectileList.add(new fire(0, atk, "none", 188, this.y + 10));
            this.exist = false;
        }
    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Jalepeno(x, y);
        p.yTile = yTi;
        p.grid = grid;
        return p;
    }

    public boolean checkRow(ArrayList<Zombie> li){
        return true;
    }
}