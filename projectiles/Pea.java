package projectiles;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import zombies.*;

public class Pea extends Projectile{

    public Pea(int speed, int damage, String stat, int x, int y){
        super(speed, damage, stat, x, y);
        try {
            this.img = ImageIO.read(new File("assets/projectiles/ProjectilePea.png"));
            this.hitbox = new Rectangle(this.x, this.y, this.img.getWidth(), this.img.getHeight());
        } catch (IOException e) {
            System.out.println("Cant find projectile");
        }
    }

    @Override
    public boolean isHit(ArrayList<Zombie> zList){
        if(this.x >= 720){
            System.out.println("Hit wall");
            return true;
        }
        for(Zombie z : zList){
            // z.setRec();
            if(this.hitbox.intersects(z.getRec())){
                System.out.println("Hit zombie");
                hit(z);
                return true;
            }
        }
        return false;
    }
    @Override
    public void hit(Object o) {
        Zombie z = (Zombie) o;
        z.takeDamage(this.damage);
    } 
}
