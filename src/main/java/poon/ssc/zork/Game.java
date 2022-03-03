package poon.ssc.zork;

import poon.ssc.zork.Command.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Game {

    private int gameState = 0;

    private boolean exit = false;

    private String mapName;

    private ArrayList<String> saveFile;

    private Scanner scanner = new Scanner(System.in);

    public boolean isExit() {
        return exit;
    }

    private ArrayList<Room> rooms;

    Room currentRoom = new Room("dummy room");
    Room previousRoom = new Room("dummy previous room");

    Player player1 = new Player("Poon", "Player",100,100,10);

    public void exit() {
        this.exit = true;
        scanner.close();
    }

    public void start(){
        System.out.println("Game started");
        while(!isExit() && scanner.hasNextLine()){
            String rawInput = scanner.nextLine();
            System.out.println("You entered string " + rawInput);
            CmdLine commandLine = Cmdparser.parseCommand(rawInput);
            Command command = CmdFactory.get(commandLine.getCommandType());
            if(command == null){
                System.out.println("Try again");
            }else{
                if(gameState==1)saveFile.add(rawInput);
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
        if (gameState == 1) {
            if (direction.equals("north") && currentRoom.getNorthExit() != null) {
                Room nextRoom = currentRoom.getNorthExit();
                previousRoom = currentRoom;
                currentRoom = nextRoom;
            }
            if (direction.equals("south") && currentRoom.getSouthExit() != null) {
                Room nextRoom = currentRoom.getSouthExit();
                previousRoom = currentRoom;
                currentRoom = nextRoom;
            }
            if (direction.equals("east") && currentRoom.getEastExit() != null) {
                Room nextRoom = currentRoom.getEastExit();
                previousRoom = currentRoom;
                currentRoom = nextRoom;
            }
            if (direction.equals("west") && currentRoom.getWestExit() != null) {
                Room nextRoom = currentRoom.getWestExit();
                previousRoom = currentRoom;
                currentRoom = nextRoom;
            }
        }else{
            System.out.println("Currently Not in Game!");
        }
    }
    public void getInfo(){
        if (gameState == 1){
            System.out.println("Currently in the" + " " + currentRoom.getDescription());
            if (currentRoom.getMonster() != null){
                System.out.println(currentRoom.getMonster().getType() +" "+ currentRoom.getMonster().getName() + " " + "with a HP of" + " "+
                        currentRoom.getMonster().getHp() + "/" + currentRoom.getMonster().getMaxHp() + " " + "appeared!!");
            }
            if (currentRoom.getAtkItem() != null){
                System.out.println(currentRoom.getAtkItem().getDescription() + " " + "Dropped!" );
            }

            if (currentRoom.getUtil() != null){
                System.out.println(currentRoom.getUtil().getDescription() + " " + "Dropped!" );
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
        else {
            System.out.println("Currently Not in Game!");
        }
    }
    public void attack(){
        Random rn = new Random();
        if (currentRoom.getMonster() != null){
            System.out.println("Attacking" + " " + currentRoom.getMonster().getType() + " " + currentRoom.getMonster().getName() + "!!");
            if(player1.getItem() !=null) {
                currentRoom.getMonster().decreaseHp(player1.getAttackingPower()+player1.getItem().attackingPower);
            }else{
                currentRoom.getMonster().decreaseHp(player1.getAttackingPower());
            }
            int prob = rn.nextInt((10-1)+1)+1;
            if ( prob <= currentRoom.getMonster().getAttackingProb()){
                System.out.println(currentRoom.getMonster().getName() + " " + "is attacking back!");
                player1.decreaseHp(currentRoom.getMonster().attackingPower);
                System.out.println(player1.getName() + " " + "Current HP is" + " " + player1.getHp());
            }
            System.out.println(currentRoom.getMonster().getType() + " " + currentRoom.getMonster().getName() + " " + currentRoom.getMonster().getHp() + "/" + currentRoom.getMonster().getMaxHp());
            if (currentRoom.getMonster().getHp() <= 0){
                System.out.println(currentRoom.getMonster().getType() + " " + currentRoom.getMonster().getName() + " " + "IS DEAD!");
                currentRoom.setMonster(null);
                if(checkDefeatAllMonster()){
                    System.out.println("You win");
                    quit();
                    return;
                }
            }
        }else{
            System.out.println("No monster here can't attack!");
        }
    }

    public void take(){
        if (currentRoom.getAtkItem() != null){
            player1.setAtkItem(currentRoom.getAtkItem());
            System.out.println("Picked up" + " " + player1.getItem().getDescription() + "\n" + "Attacking Power increased to" + " " + (player1.getAttackingPower() +player1.getItem().getAtkPower()));
            currentRoom.setAtkItem(null);
        }
    }

    public void use() {
        if (currentRoom.getUtil() != null) {
            if (!((player1.getHp() + currentRoom.getUtil().getHp()) > 100)) {
                player1.increaseHp(currentRoom.getUtil().getHp());
                System.out.println("Player" + " " + player1.getName() + " " + "HP" + " " + "Increased to" + " " + player1.getHp());
            } else {
                System.out.println("Limit HP exceed !!");
            }
        }
    }
    public void loadMap(String mapName)  {
        if(gameState==1){
            System.out.println("You're playing game.");
            return;
        }
        System.out.println("Loading Map "+mapName);
        try {
            // Fix Path name
            File myObj = new File("F:\\hw\\a3zork\\"+mapName+".txt");
            Scanner myReader = new Scanner(myObj);
            int n = Integer.parseInt(myReader.nextLine());
            rooms = new ArrayList<Room>();
            //create room
            for(int i=0;i<n;i++){
                Room room = new Room(myReader.nextLine());
                rooms.add(room);
            }
            //set exits
            for(int i=0;i<n;i++){
                String ex = myReader.nextLine();
                String[] exits = ex.split(",");
                Room[] r = new Room[4];
                for(int j=0;j<r.length;j++){
                    if(exits[j].equals("null")){
                        r[j] = null;
                    }else{
                        r[j] = rooms.get(Integer.parseInt(exits[j]));
                    }
                }
                rooms.get(i).setExit(r[0],r[1],r[2],r[3]);
            }
            n = Integer.parseInt(myReader.nextLine());
            for(int i=0;i<n;i++){
                String line = myReader.nextLine();
                String[] data = line.split(",");
                Monster m = new Monster(data[0],data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]),Integer.parseInt(data[5]));
                rooms.get(Integer.parseInt(data[6])).setMonster(m);
            }
            n = Integer.parseInt(myReader.nextLine());
            for(int i=0;i<n;i++){
                String line = myReader.nextLine();
                String[] data = line.split(",");
                AtkItem atkItem = new AtkItem(data[0],Integer.parseInt(data[1]));;
                rooms.get(Integer.parseInt(data[2])).setAtkItem(atkItem);
            }
            myReader.close();
            this.mapName = mapName;
            currentRoom = rooms.get(0);
            player1 = new Player("Poon", "Player",100,100,10);;
            gameState = 1;
            System.out.println("Playing " + mapName);
            saveFile = new ArrayList<>();
            saveFile .add(mapName);
        }catch (FileNotFoundException e){
            //e.printStackTrace();
            System.out.println("Can't Load Map");;
        }
    }
    public void save(String saveName) {
        if(gameState==0){
            System.out.println("Not in game");
            return;
        }
        try{
            File myObj = new File(saveName+".txt");
            FileWriter fw = new FileWriter(myObj);
            for(int i=0;i<saveFile.size()-1;i++){
                fw.write(saveFile.get(i)+"\n");
            }
            fw.close();
            System.out.println("Save success "+saveName);
        }catch(IOException e){
            System.out.println("Cant save file");
        }
    }
    public void load(String loadName) {
        if(gameState==1){
            System.out.println("You are in game");
            return;
        }
        try {
            System.out.println(loadName);
            File myObj = new File(loadName+".txt");
            Scanner myReader = new Scanner(myObj);
            String s= myReader.nextLine();
            loadMap(s);
            while(myReader.hasNextLine()){
                s = myReader.nextLine();
                System.out.println(s);
                CmdLine commandLine = Cmdparser.parseCommand(s);
                Command command = CmdFactory.get(commandLine.getCommandType());
                command.execute(this,commandLine.getArgument());
            }
            myReader.close();
            System.out.println("Loading success "+loadName);
        }catch (FileNotFoundException e){
            //e.printStackTrace();
            System.out.println("Can't load save");
        }
    }
    private boolean checkDefeatAllMonster() {
        for(Room room : rooms) {
            if (room.getMonster() != null) {
                return false;
            }
        }
        return true;
    }
    public void quit(){
        if(gameState==1) {
            System.out.println("Quit map");
            gameState = 0;
        }else{
            System.out.println("Not in a game");
        }
    }
    public void drop() {
        if(gameState==0){
            System.out.println("Not in game");
            return;
        }
        if(player1.getItem()==null){
            System.out.println("There is nothing in hand");
        }else{
            if(currentRoom.getAtkItem()!=null){
                System.out.println("Cant drop");
            }else{
                currentRoom.setAtkItem(player1.getItem());
                player1.setAtkItem(null);
                System.out.println("Item dropped");
            }
        }
    }
    public void autoPilot(String loadName) {
        if(gameState==0){
            System.out.println("You are not in game");
            return;
        }
        try {
            System.out.println(loadName);
            File myObj = new File(loadName+".txt");
            Scanner myReader = new Scanner(myObj);
            String s;
            while(myReader.hasNextLine()){
                s = myReader.nextLine();
                System.out.println(s);
                CmdLine commandLine = Cmdparser.parseCommand(s);
                Command command = CmdFactory.get(commandLine.getCommandType());
                command.execute(this,commandLine.getArgument());
            }
            myReader.close();
            System.out.println("Auto pilot with"+ " " + loadName);
        }catch (FileNotFoundException e){
            //e.printStackTrace();
            System.out.println("No auto pilot file");
        }
    }
}
