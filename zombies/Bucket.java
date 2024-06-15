package zombies;

// Bucket class for the bucket zombie, subclass of Zombie Class
public class Bucket extends Zombie {

    // Constructor, gets the hp,damage, x, y, row, and name and pass most of it to
    // the zombie Constructor

    public Bucket(int hp, int damage, int x, int y, int row, String name) {
        super(hp, damage, x, y, row, name);
        // Path to the cone zombie folder
        this.path = "assets/zombies/BucketheadZombie/buckethead";
    }

    @Override
    // Movement method, makes the zombie move 2 pixels
    // no return value or paramters
    // The hitbox moves along with the zombie
    public void move() {
        // TODO Auto-generated method stub
        this.x = this.x - 2;
        this.hitX = this.hitX - 2;
        setRec();

    }

}
