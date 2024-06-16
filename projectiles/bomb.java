package projectiles;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import zombies.*;

public class bomb extends Projectile{

    public bomb(int speed, int damage, String stat, int x, int y) {
        super(speed, damage, stat, x, y);
        try {
            this.img = ImageIO.read(new File("assets/projectiles/ProjectilePea.png"));
            this.hitbox = new Rectangle(this.x, this.y, this.img.getWidth(), this.img.getHeight());
        } catch (IOException e) {
            System.out.println("Cant find projectile");
        }
    }    @Override
    public boolean isHit(ArrayList<Zombie> zList, int x, int y) {
        return true;
    }

    @Override
    public void hit(Object o) {

    }
}
