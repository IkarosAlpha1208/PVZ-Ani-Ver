package plants;
import projectiles.Projectile;

abstract class Plant {
    private int hp;
    private int atk;
    private int atkSpd;
    private int cooldown;
    private int cost;
    private int x, y;

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
