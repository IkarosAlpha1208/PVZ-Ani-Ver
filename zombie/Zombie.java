package zombie;

abstract class Zombie {
    protected int hp;
    protected int x, y;
    protected int speed;
    private String stat;

    public Zombie(int x, int y) {
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

    public void setStat(String stat) {
        this.stat = stat;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}