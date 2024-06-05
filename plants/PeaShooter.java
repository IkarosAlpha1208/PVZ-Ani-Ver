package plants;
import projectiles.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class PeaShooter extends Plant{
    public PeaShooter(int x, int y){
        super(100, 10, 3, 10, 100, x, y, x+20, y+20, new ImageIcon("PVZ-Ani-Ver/assets/plants/PeaShootera.gif"));
    }

    @Override
    public void attack(ArrayList<Projectile> projectileList){
        
    }
}