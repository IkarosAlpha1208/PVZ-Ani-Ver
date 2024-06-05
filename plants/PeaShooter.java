import projectiles.*;
public class Shooter extends Plant{
    public Shooter(int hp, int atk, int atkSpd, int cooldown, int cost, int x, int y){
        super(hp, atk, atkSpd, cooldown, cost, x, y);
    }

    @Override
    Projectile attack(){
        System.err.println(hp);
    }
}