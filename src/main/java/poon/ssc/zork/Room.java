package poon.ssc.zork;

public class Room {

    private Monster monster;
    private Item item;

    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private String Description;

    public Room(String description) {
        this.Description = description;
    }

    public void setExit(Room northExit, Room southExit, Room eastExit, Room westExit){
        this.northExit = northExit;
        this.southExit = southExit;
        this.eastExit = eastExit;
        this.westExit = westExit;
    }

    public Room getNorthExit() {
        return northExit;
    }

    public Room getSouthExit() {return southExit;}

    public Room getEastExit() {
        return eastExit;
    }

    public Room getWestExit() {
        return westExit;
    }

    public String getDescription() {
        return Description;
    }
    public void setMonster(Monster monster){
        this.monster = monster;
    }

    public Monster getMonster(){
        return monster;
    }

    public void setItem(Item item){ this.item = item;}

    public Item getItem(){
        return item;
    }
}
