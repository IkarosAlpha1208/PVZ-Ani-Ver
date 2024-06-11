package plants;

import projectiles.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class Wallnut extends Plant {
    public Wallnut(int x, int y) {
        super(200, 0, 0, 15, 50, x, y, new Rectangle(x, y, 36, 48));
        this.name = "Wallnut";
        this.stat = "nut";
        this.id = 2;
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/Wallnut.png")));
            this.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList) {}

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Wallnut(x, y);
        p.yTile = yTi;
        p.grid = grid;
        return p;
    }
}