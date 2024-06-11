package plants;
import projectiles.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class PeaShooter extends Plant {

    public PeaShooter(int x, int y) {
        super(50, 10, 2, 10, 100, x, y, new Rectangle(x, y, 36, 48));
        this.name = "PeaShooter";
        this.stat = "pea";
        this.id = 1;
        this.lastAttack = 0;
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/PeaShooter.png")));
            this.setImage(img);
        } catch (IOException e) {
            System.out.println("Cant find File");
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastAttack) / 1000 >= atkSpd) {
            lastAttack = currentTime;
            projectileList.add(new Pea(5, atk, "none", this.x - 10, this.y + 10));
        }
    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new PeaShooter(x, y);
        p.yTile = yTi;
        p.grid = grid;
        return p;
    }
}