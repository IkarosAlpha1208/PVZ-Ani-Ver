package zombies;

public abstract class Zombie {
    protected int hp;
    protected int x, y;
    protected int speed;
    protected int atk;
    // 0 is not being effected, 1 is being slowed, 2 is being stuned
    private int stat;

    public Zombie(int hp, int speed, int x, int y) {
        this.hp = hp;
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

    public void setX(int x){
        this.x = x;

    }


}


