package plants;
import projectiles.*;
import zombies.Zombie;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
public class Mahou extends Plant{

    public Mahou(int x, int y) {
        super(2000, 0, 0, 10, 250, x, y, new Rectangle(x, y, 36, 48));
        this.name = "Sad Heathcliff";
        this.stat = "nut";
        this.id = 12;
        this.lastAttack = 0;
        this.describe = "Tank af";
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/Mahou.png")));
            this.setImage(img);
        } catch (IOException e) {
            System.out.println("Cant find File");
        }
    }
    @Override
    public void attack(ArrayList<Projectile> projectileList) {
    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Mahou(x, y);
        p.yTile = yTi;
        p.grid = grid;
        return p;
    }

    public boolean checkRow(ArrayList<Zombie> li){
        return false;
    }
}
