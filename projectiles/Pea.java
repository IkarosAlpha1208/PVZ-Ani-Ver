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

public class Pea extends Projectile {

    public Pea(int speed, int damage, String stat, int x, int y) {
        super(speed, damage, stat, x, y);
        try {
            this.img = ImageIO.read(new File("assets/projectiles/ProjectilePea.png"));
            this.hitbox = new Rectangle(this.x, this.y, this.img.getWidth(), this.img.getHeight());
        } catch (IOException e) {
            System.out.println("Cant find projectile");
        }
        AudioInputStream sound;

        try {
            sound = AudioSystem.getAudioInputStream(new File("assets/sound/splat.wav"));
            this.hitSound = AudioSystem.getClip();
            this.hitSound.open(sound);

        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public boolean isHit(ArrayList<Zombie> zList, int x, int y) {
        if (this.x >= 720) {
            return true;
        }
        for (Zombie z : zList) {
            if (this.hitbox.intersects(z.getRec())) {
                System.out.println("Hit zombie");
                this.hitSound.start();
                hit(z);

                // z.isDead();
                // if (z.getIsDead()) {
                // zList.remove(z);
                // }
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
