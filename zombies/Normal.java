package zombies;

public class Normal extends Zombie {
    // BufferedImage walking = new BufferedImage

    public Normal(int hp, int speed, int x, int y) {
        super(hp, speed, x, y);
        // TODO Auto-generated constructor stub
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;

    }

    public void setX(int x){
        this.x = x;


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
