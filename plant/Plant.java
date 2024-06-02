package plant;

abstract class Plant {
    private int hp;
    private int atk;
    private int atkSpd;
    private int cooldown;
    private int cost;
    private int x, y;

    public Plant(int hp, int atk, int atkSpd, int cooldown, int cost){
        this.hp = hp;
        this.atk = atk;
        this.atkSpd = atkSpd;
        this.cooldown = cooldown;
        this.cost = cost;
    }

    abstract void attack();

    public void takeDmg(int damage){
        this.hp -= damage;
    }
}
