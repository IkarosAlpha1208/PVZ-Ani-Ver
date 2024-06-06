package plants;
import projectiles.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class PeaShooter extends Plant{
    public PeaShooter(int x, int y){
        super(100, 10, 3, 10, 100, x, y, new Rectangle(x, y, 56, 68));
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/PeaShooter.png")));
            this.setImage(img);
        } catch (IOException e) {
            System.out.println("Cant find File");
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList){
        
    }

    @Override
    public Plant createPlant(int x, int y){
        return new PeaShooter(x, y);
    }
}