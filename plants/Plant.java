package plants;
import java.util.ArrayList;
import javax.swing.Icon;
import projectiles.Projectile;


public abstract class Plant {
    protected int hp;
    protected int atk;
    protected int atkSpd;
    protected int cooldown;
    protected int cost;
    protected int stat;
    protected int x, y;
    protected int hitX, hitY;
    protected Icon plantAsset;

    public Plant(int hp, int atk, int atkSpd, int cooldown, int cost, int x, int y, int hitX, int hitY, Icon plantAsset) {
        this.hp = hp;
        this.atk = atk;
        this.atkSpd = atkSpd;
        this.cooldown = cooldown;
        this.cost = cost;
        this.x = x;
        this.y = y;
        this.hitX = hitX;
        this.hitY = hitY;
        this.plantAsset = plantAsset;
    }

    public abstract void attack(ArrayList<Projectile> projectileList);

    public void takeDmg(int damage) {
        this.hp -= damage;
    }

    public boolean isDead(){
        return this.hp <= 0;
    }
}