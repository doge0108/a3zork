package poon.ssc.zork;

public class Player extends Character {

    public Player(String name, String type, int hp, int maxHp, int attackingPower) {
        super(name, type, hp, maxHp, attackingPower);
    }
    public String getName(){
        return this.name;
    }
    public void increaseAttackingPower(int attackingPower){
        this.attackingPower += attackingPower;
    }
    public void increaseHp(int Hp){
        this.hp += Hp;
    }
    public void decreaseHp(int Hp) {
        this.hp -= Hp;
    }
}

