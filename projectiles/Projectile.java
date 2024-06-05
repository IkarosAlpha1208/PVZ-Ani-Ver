package projectiles;
import zombies.*;

public class Projectile {
    private int speed;
    private int damage;
    // -1 is fire, 0 is no effect, 1 is frost, 2 is stun, 3 is penetrate
    private int stat;
    private int x, y;

    public Projectile(int speed, int damage, int stat, int x, int y){
        this.speed = speed;
        this.damage = damage;
        this.stat = stat;
        this.x = x;
        this.y = y;
    }

    public void isHit(Zombie z){
        
    }

    public void hit(){

    }

    public void move(){
        this.x += this.speed;
    }

    public int getStat(){
        return this.stat;
    }
}
