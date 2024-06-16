package plants;
import projectiles.*;
import zombies.Zombie;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

// this is useless i just kept it for holder
public class Coffee extends Plant {

    public Coffee(int x, int y) {
        super(1, 0, 0, 0, 0, x, y, new Rectangle(x, y, 36, 48));
        this.name = "Coffee";
        this.stat = "";
        this.id = 8;
        this.lastAttack = 0;
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/Coffee.png")));
            this.setImage(img);
        } catch (IOException e) {
            System.out.println("Cant find File");
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList) {}

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Coffee(x, y);
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