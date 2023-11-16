import java.util.ArrayList;

class Explorer extends LivingThing{
    ArrayList<Treasure> treasures;

    public Explorer(String name, int health, String color){
        super(name,health,color);
        treasures = new ArrayList<>();
    }

    public ArrayList<Treasure> getTreasures(){
        return treasures;
    }

    public void addTreasure(Treasure treasure){
        treasures.add(treasure);
    }

    public int getTreasureValue(){
        int sum = treasures.size();
        return sum;
    }
}
