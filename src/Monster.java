class Monster extends LivingThing{
    private int damage;

    public Monster(String name, int health, String color, int damage){
        super(name,health,color);
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }

    public void Hurt(LivingThing lt){
        lt.setHealth(lt.getHealth() - damage);
    }
}