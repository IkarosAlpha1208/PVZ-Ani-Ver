package zombie;

abstract class Zombie {
    protected int hp;
    protected int x, y;
    protected int speed;

    public Zombie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void attack();

    public abstract void move();

    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}