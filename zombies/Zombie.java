package zombies;

public abstract class Zombie {
    protected int hp;
    protected int x, y;
    protected int speed;
    protected int atk;
    protected int atkspd;
    // 0 is not being effected, 1 is being slowed, 2 is being stuned
    protected int stat;

    public Zombie(int hp, int atk, int atkspd, int speed, int x, int y) {
        this.hp = hp;
        this.atk = atk;
        this.atkspd = atkspd;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public abstract void attack();

    public abstract void move();

    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}