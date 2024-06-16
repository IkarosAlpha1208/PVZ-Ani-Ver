package plants;
import projectiles.*;
import zombies.Zombie;

import java.util.ArrayList;
import javax.imageio.ImageIO;

import player.Player;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

//generate sun
public class Yisang extends Plant {

    public Yisang(int x, int y) {
        super(100, 10, 1, 10, 150, x, y, new Rectangle(x, y, 36, 48));
        this.name = "Yi Sang";
        this.stat = "sun";
        this.id = 15;
        this.lastAttack = 0;
        this.describe = "Generate sun every second";
        try {
            BufferedImage img = ImageIO.read((new File("assets/plants/YiSang.png")));
            this.setImage(img);
        } catch (IOException e) {
            System.out.println("Cant find File");
        }
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastAttack)/1000.0 >= atkSpd) {
            lastAttack = currentTime;
            Player.changeSunlight(10);
        }
    }

    @Override
    public Plant createPlant(int x, int y, int yTi, String grid) {
        Plant p = new Yisang(x, y);
        p.yTile = yTi;
        p.grid = grid;
        return p;
    }

    public boolean checkRow(ArrayList<Zombie> li){
        for (int i = 0; i < li.size(); i++) {
            if (Integer.parseInt(this.grid) % 10 == li.get(i).getRow()) {
                return true;
            }

        }
        return false;
    }
}