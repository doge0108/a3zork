package poon.ssc.zork;

public class Monster extends Character {
    private int attackingProb = 0;

    public Monster(String name, String type, int hp, int maxHp, int attackingPower, int attackingProb) {
        super(name, type, hp, maxHp, attackingPower);
        this.attackingProb = attackingProb;
    }

    public void decreaseHp(int attackingPower){
        this.hp -= attackingPower;
    }

    public int getAttackingProb() {
        return attackingProb;
    }
}