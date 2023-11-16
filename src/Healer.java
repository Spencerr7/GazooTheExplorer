class Healer extends LivingThing{
    private int healValue;

    public Healer(String name, int health, String color, int heal){
        super(name,health,color);
        this.healValue = heal;
    }

    public int getHealthValue(){
        return healValue;
    }

    public void Heal(LivingThing lt){
        lt.setHealth(lt.getHealth() + healValue);
    }
}