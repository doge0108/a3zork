package poon.ssc.zork;

public class Item {
    protected String description = null;

    public Item(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
