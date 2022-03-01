package poon.ssc.zork;

public class Item {
    String description = null;
    int attackingPower = 0;

    public Item(String description, int attackingPower){
        this.description = description;
        this.attackingPower = attackingPower;
    }

    public int getAttackingPower() {
        return attackingPower;
    }
    public String getDescription(){
        return description;
    }
}
