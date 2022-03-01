package poon.ssc.zork;

public class Character {
    String name = null;
    int hp = 0;
    int maxHp = 0;
    String type = null;
    int attackingPower = 0;

    public Character(String name, String type, int hp, int maxHp, int attackingPower ){
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attackingPower = attackingPower;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttackingPower() {
        return attackingPower;
    }

    public String getType() {
        return type;
    }
}
