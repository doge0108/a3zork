package poon.ssc.zork;

public class AtkItem extends Item{
    protected int attackingPower = 0;

    public AtkItem(String description, int attackingPower) {
        super(description);
        this.attackingPower = attackingPower;
    }

    public int getAtkPower(){
        return attackingPower;
    }
}
