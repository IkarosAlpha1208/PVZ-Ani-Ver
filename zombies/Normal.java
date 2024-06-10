package zombies;

import plants.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public class Normal extends Zombie {

    public Normal(int hp, int speed, int x, int y, int row) {
        super(hp, speed, x, y, row);

        this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
        // TODO Auto-generated constructor stub
    }

    public void setX(int x) {
        this.x = x;

    }

    @Override
    public void attack(HashMap<String, Plant> pList) {

        // / TODO Auto-generated method stub
        Set<String> keyList = pList.keySet();
        Iterator iter = keyList.iterator();

        // System.out.println(this.isEating);
        // System.out.println("IM BEGGING PLS WORK");

        while (iter.hasNext()) {
            String currentKey = (String) iter.next();
            Plant p = pList.get(currentKey);
            long currentTime = System.currentTimeMillis();
            // System.out.println(this.isEating);
            // System.out.println("IM BEGGING PLS WORK");

            if (p.getRec().intersects(this.hitbox) && this.isDead == false
                    && (currentTime - lastAttack) / 1000 >= atkSpd && !p.isDead()) {
                this.lastAttack = currentTime;
                this.isEating = true;
                this.isWalking = false;
                p.takeDmg(10);
                this.currentEating = p;
                System.out.println("PLANTTTTT HEALLTTTHHH " + p.getHealth());

                System.out.println("Hellloliii");

            }
        


            if (p.isDead()) {
                this.isEating = false;
                this.isWalking = true;

                iter.remove();

            }
            if(!pList.containsValue(this.currentEating)){
                this.isEating = false;
                this.isWalking = true;
            }
           
        }

        
        if(pList.size()==0){
            this.isEating = false;
            this.isWalking = true;

        }

        // throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void move() {
        this.x = this.x - 3;
        this.hitX = this.hitX - 3;
        setRec();

        // throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
