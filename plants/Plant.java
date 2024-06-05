package plants;
import projectiles.Projectile;

abstract class Plant {
    protected int hp;
    protected int atk;
    protected int atkSpd;
    protected int cooldown;
    protected int cost;
    protected int x, y;

    public Plant(int hp, int atk, int atkSpd, int cooldown, int cost, int x, int y) {
        this.hp = hp;
        this.atk = atk;
        this.atkSpd = atkSpd;
        this.cooldown = cooldown;
        this.cost = cost;
        this.x = x;
        this.y = y;
    }

    abstract Projectile attack();

    public void takeDmg(int damage) {
        this.hp -= damage;
    }

    public boolean isDead(){
        return this.hp <= 0;
    }
}