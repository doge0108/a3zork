package poon.ssc.zork;

public class Util extends Item{
    int hp = 0;
    public Util(String description,int hp) {
        super(description);
        this.hp = hp;
    }

    public int getHp(){
        return hp;
    }
}
