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
        System.out.println("Currently in the" + " " + currentRoom.getDescription());
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
        Room oneRoom = new Room("one Room");

        currentRoom = mainHall;

        mainHall.setExit(oneRoom,null,null,null);
        oneRoom.setExit(null,mainHall,null,null);

    }
}
