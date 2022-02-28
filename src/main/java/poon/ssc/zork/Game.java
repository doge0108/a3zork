package poon.ssc.zork;

import poon.ssc.zork.Command.*;

import java.util.Map;
import java.util.Scanner;

public class Game {

    private boolean exit = false;

    private Scanner scanner = new Scanner(System.in);

    public boolean isExit() {
        return exit;
    }

    Room currentRoom = new Room("dummy room");
    Room previousRoom = new Room("dummy previous room");

    public void exit() {
        this.exit = true;
        scanner.close();
    }

    public void start(){
        System.out.println("Game started");
        createRoom();
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
    public void createRoom(){

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

        Monster akaza = new Monster("akaza","Monster",100,100,1);
        Monster muzan = new Monster("Muzan","Monster",200,200,1);

        Tanjiro.setMonster(akaza);
        Pikachu.setMonster(muzan);
    }
}
