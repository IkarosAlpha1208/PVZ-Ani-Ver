package projectiles;

public class Projectile {
    protected int speed;
    protected int damage;
    // -1 is fire, 0 is no effect, 1 is frost, 2 is stun, 3 is penetrate
    protected int stat;
    protected int x, y;

    public Projectile(int speed, int damage, int stat, int x, int y){
        this.speed = speed;
        this.damage = damage;
        this.stat = stat;
        this.x = x;
        this.y = y;
    }

    public boolean hit(){
        return true;
    }

    public void move(){
        this.x += this.speed;
    }

    public int getStat(){
        return this.stat;
    }
}
