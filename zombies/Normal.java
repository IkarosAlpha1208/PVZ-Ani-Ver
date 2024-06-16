package zombies;
import java.awt.*;

// Class for the normal zombie, is a subclass of the Zombie Class 
public class Normal extends Zombie {

    public Normal(int hp, int damage, int x, int y, int row, String name) {
        super(hp, damage, x, y, row, name);

        this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
        this.path = "assets/zombies/NormalZombie/zombie";
    }

    // Setter & Getter methods
    public void setX(int x) {
        this.x = x;

    }

    // Movement method, makes the zombie move 2 pixels
    // no return value or paramters
    // The hitbox moves along with the zombie
    @Override
    public void move() {
        this.x = this.x - 2;
        this.hitX = this.hitX - 2;
        setRec();

        // throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
