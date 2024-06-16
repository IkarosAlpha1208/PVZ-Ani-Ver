package projectiles;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import zombies.*;

// projectile for cherry bomb
public class bomb extends Projectile{

    public bomb(int speed, int damage, String stat, int x, int y) {
        super(speed, damage, stat, x, y);
        try {
            this.img = ImageIO.read(new File("assets/projectiles/bomb.png"));
            this.hitbox = new Rectangle(this.x, this.y, this.img.getWidth(), this.img.getHeight());
            this.exist = System.currentTimeMillis();
        } catch (IOException e) {
            System.out.println("Cant find projectile");
        }
        AudioInputStream sound;

        try {
            sound = AudioSystem.getAudioInputStream(new File("assets/sound/cherrybomb.wav"));
            this.hitSound = AudioSystem.getClip();
            this.hitSound.open(sound);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

    }    @Override
    public boolean isHit(ArrayList<Zombie> zList, int x, int y) {
        try {
            this.hitSound.start();
            for (int i = 0; i < zList.size(); i++) {
                Zombie z = zList.get(i);
                if (this.hitbox.intersects(z.getRec())) {
                    System.out.println("Zombie Health: " + z.getHp());
                    hit(z);
                    z.isDead();
                    if(z.getIsDead()){
                        zList.remove(i);
                        i--;
                    }
                }
            }
            if((System.currentTimeMillis() - this.exist)/1000.0 > 0.5){
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public void hit(Zombie z) {
        z.takeDamage(this.damage);
    }
}
