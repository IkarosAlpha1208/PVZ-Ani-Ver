package zombies;

public class Normal extends Zombie {
    // BufferedImage walking = new BufferedImage

    public Normal(int hp, int atk, int atkspd, int speed, int x, int y) {
        super(hp, atk, atkspd, speed, x, y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;

    }

    @Override
    public void attack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
