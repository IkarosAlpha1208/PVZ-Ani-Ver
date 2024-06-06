package plants;
import projectiles.*;
import zombies.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class PeaShooter extends Plant{
    public PeaShooter(int x, int y){
        super(100, 10, 2, 10, 100, x, y, new Rectangle(x, y, 56, 68));
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/PeaShooter.png")));
            this.setImage(img);
        } catch (IOException e) {
            System.out.println("Cant find File");
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList, ArrayList<Zombie> zList){
        long currentTime = System.currentTimeMillis();
        for(Zombie z : zList){
            if ((currentTime - lastAttack)/1000 >= atkSpd) {
                lastAttack = currentTime;
                System.out.println("Peashooter attacking at (" + yTile() + " toward " + z.yTile() + ")");
                projectileList.add(new Pea(5, atk, "none", this.x - 10, this.y + 10));
                break;
            }
        }
    }

    @Override
    public Plant createPlant(int x, int y, int yTi){
        Plant p = new PeaShooter(x, y);
        p.yTile = yTi;
        return p;
    }
}