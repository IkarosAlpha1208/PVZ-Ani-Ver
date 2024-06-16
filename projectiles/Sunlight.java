package projectiles;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import zombies.*;
import player.*;

public class Sunlight extends Projectile{

    long existTime;

    public Sunlight(int speed, int damage, String stat, int x, int y) {
        super(speed, damage, stat, x, y);
        this.existTime = System.currentTimeMillis();
        try {
            this.img = ImageIO.read(new File("assets/projectiles/Sunlight.png"));
            this.hitbox = new Rectangle(this.x, this.y, this.img.getWidth(), this.img.getHeight());
        } catch (IOException e) {
            System.out.println("Cant find projectile");
        }
    }

    @Override
    public boolean isHit(ArrayList<Zombie> zList, int x, int y) {
        long currentTime = System.currentTimeMillis();
        Rectangle r = new Rectangle(x, y, 5, 5);
        if((currentTime - existTime)/1000.0 >= 8){
            return true;
        }
        
        if(this.hitbox.intersects(r)){
            System.out.println("Collected 25 sunlight");
            return true;
        }
        return false;
    }

    @Override
    public void hit(Zombie z) {
        Player.changeSunlight(this.damage);
    }
}
