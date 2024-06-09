package plants;

import projectiles.*;
import zombies.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class Sunflower extends Plant {
    public Sunflower(int x, int y) {
        super(100, 0, 8, 5, 50, x, y, new Rectangle(x, y, 56, 68));
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/Sunflower.png")));
            this.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList, ArrayList<Zombie> zList) {

    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Sunflower(x, y);
        p.yTile = yTi;
        p.grid = grid;

        return p;
    }
}