package poon.ssc.zork;

import poon.ssc.zork.Command.*;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private boolean exit = false;

    private Scanner scanner = new Scanner(System.in);

    public boolean isExit() {
        return exit;
    }

    Room currentRoom = new Room("dummy room");
    Room previousRoom = new Room("dummy previous room");

    Player player1 = new Player("Poon", "Player",50,100,10);

    public void exit() {
        this.exit = true;
        scanner.close();
    }

    public void start(){
        System.out.println("Game started");
        createGame();
        while(!isExit() && scanner.hasNextLine()){
            String rawInput = scanner.nextLine();
            System.out.println("You entered string " + rawInput);
            CmdLine commandLine = Cmdparser.parseCommand(rawInput);
            Command command = CmdFactory.get(commandLine.getCommandType());
            if(command == null){
                System.out.println("Try again");
            }else{
                command.execute(this,commandLine.getArgument());
            }
        }
    }
    public static void help(){
        Map<CommandType,Command> commandMap = CmdFactory.getCommandMap();
        for(Map.Entry<CommandType,Command>entry : commandMap.entrySet()){
            System.out.println(entry.getKey());
        }
    }
    public void go(String direction){
        if (direction.equals("north")&& currentRoom.getNorthExit() != null){
            Room nextRoom = currentRoom.getNorthExit();
            previousRoom = currentRoom;
            currentRoom = nextRoom;
        }
        if (direction.equals("south") && currentRoom.getSouthExit() != null){
            Room nextRoom = currentRoom.getSouthExit();
            previousRoom = currentRoom;
            currentRoom = nextRoom;
        }
        if (direction.equals("east") && currentRoom.getEastExit() != null){
            Room nextRoom = currentRoom.getEastExit();
            previousRoom = currentRoom;
            currentRoom = nextRoom;
        }
        if (direction.equals("west") && currentRoom.getWestExit() != null){
            Room nextRoom = currentRoom.getWestExit();
            previousRoom = currentRoom;
            currentRoom = nextRoom;
        }
    }
    public void getInfo(){
        System.out.println("Currently in Room" + " " + currentRoom.getDescription());
        if (currentRoom.getMonster() != null){
            System.out.println(currentRoom.getMonster().getType() +" "+ currentRoom.getMonster().getName() + " " + "with a HP of" + " "+
                    currentRoom.getMonster().getHp() + "/" + currentRoom.getMonster().getMaxHp() + " " + "appeared!!");
        }
        if (currentRoom.getAtkItem() != null){
            System.out.println(currentRoom.getAtkItem().getDescription() + " " + "Dropped!" );
        }
        System.out.println("The exits are: ");
        if (currentRoom.getNorthExit() != null){
            System.out.println("north");
        }if (currentRoom.getSouthExit() != null){
            System.out.println("south");
        }if (currentRoom.getEastExit() != null){
            System.out.println("east");
        }if (currentRoom.getWestExit() != null){
            System.out.println("west");
        }
    }
    public void createGame(){

        Room mainHall = new Room("Main Hall");
        Room Yoriichi = new Room("Yoriichi");
        Room Tanjiro = new Room("Tanjiro");
        Room Zenitsu = new Room("Zenitsu");
        Room Inosuke = new Room("Inosuke");
        Room Pikachu = new Room("Pikachu");

        currentRoom = mainHall;

        mainHall.setExit(Tanjiro,Yoriichi,Zenitsu,Inosuke);
        Tanjiro.setExit(null,mainHall,null,Pikachu);
        Yoriichi.setExit(mainHall,null,null,null);
        Zenitsu.setExit(null,null,null,mainHall);
        Inosuke.setExit(null,null,mainHall,null);
        Pikachu.setExit(null,null,Tanjiro,null);

        Monster akaza = new Monster("akaza","Monster",100,100,10,6);
        Monster muzan = new Monster("Muzan","Monster",200,200,15,4);
        Monster charmander = new Monster("Charmander","Monster",75,75,20,2);

        Tanjiro.setMonster(akaza);
        Yoriichi.setMonster(muzan);
        Pikachu.setMonster(charmander);

        AtkItem sword = new AtkItem("sword",10);
        AtkItem dagger = new AtkItem("Dagger",5);
        AtkItem rock = new AtkItem("rock",100);

        Inosuke.setAtkItem(sword);
        Zenitsu.setAtkItem(dagger);
        Pikachu.setAtkItem(rock);

        Util firstAid = new Util("firstAid",10);

        Tanjiro.setUtil(firstAid);
    }

    public void attack(){
        Random rn = new Random();
        if (currentRoom.getMonster() != null){
            System.out.println("Attacking" + " " + currentRoom.getMonster().getType() + " " + currentRoom.getMonster().getName() + "!!");
            currentRoom.getMonster().decreaseHp(player1.getAttackingPower());
            int prob = rn.nextInt((10-1)+1)+1;
            if ( prob <= currentRoom.getMonster().getAttackingProb()){
                System.out.println(currentRoom.getMonster().getName() + " " + "is attacking back!");
                player1.decreaseHp(currentRoom.getMonster().attackingPower);
                System.out.println(player1.getName() + " " + "Current HP is" + " " + player1.getHp());
            }
            System.out.println(currentRoom.getMonster().getType() + " " + currentRoom.getMonster().getName() + " " + currentRoom.getMonster().getHp() + "/" + currentRoom.getMonster().getMaxHp());
            if (currentRoom.getMonster().getHp() == 0){
                System.out.println(currentRoom.getMonster().getType() + " " + currentRoom.getMonster().getName() + " " + "IS DEAD!");
            }
        }else{
            System.out.println("No monster here can't attack!");
        }
    }

    public void take(){
        if (currentRoom.getAtkItem() != null){
            player1.increaseAttackingPower(currentRoom.getAtkItem().getAtkPower());
            System.out.println("Picked up" + " " + currentRoom.getAtkItem().getDescription() + "\n" + "Attacking Power increased to" + " " + player1.getAttackingPower() );
        }
    }

    public void use(){
        if (currentRoom.getUtil() != null ){
            if (!((player1.getHp() + currentRoom.getUtil().getHp())> 100)){
                player1.increaseHp(currentRoom.getUtil().getHp());
                System.out.println("Player" + " " + player1.getName() + " "+ "HP" + " " + "Increased to" + " " + player1.getHp());
            }else{
                System.out.println("Limit HP exceed !!");
            }

        }
    }
}
