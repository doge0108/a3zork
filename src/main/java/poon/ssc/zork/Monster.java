package poon.ssc.zork;

public class Monster extends Character {


    public Monster(String name, String type, int hp, int maxHp, int attackingPower) {
        super(name, type, hp, maxHp, attackingPower);
    }

    public void decreaseHp(int attackingPower){
        this.hp -= attackingPower;
    }
}