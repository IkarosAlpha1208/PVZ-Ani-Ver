package projectiles;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import zombies.Zombie;

public class Pea extends Projectile{

    public Pea(int speed, int damage, String stat, int x, int y) throws IOException{
        super(speed, damage, stat, x, y, ImageIO.read(new File("assets/projectiles/ProjectilePea.png")));
    }

    @Override
    public void isHit(ArrayList<Zombie> zList) {

    }

    @Override
    public void hit() {

    } 
}
