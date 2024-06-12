package zombies;

public class Bucket extends Zombie {

    public Bucket(int hp, int damage, int x, int y, int row, String name) {
        super(hp, damage, x, y, row, name);
        // TODO Auto-generated constructor stub
        this.path = "assets/zombies/BucketheadZombie/buckethead";
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        this.x = this.x - 3;
        this.hitX = this.hitX - 3;
        setRec();

    }

}
