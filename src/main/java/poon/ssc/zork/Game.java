package poon.ssc.zork;

import poon.ssc.zork.Command.*;

import java.util.Scanner;

public class Game {

    private boolean exit = false;

    private Scanner scanner = new Scanner(System.in);

    public boolean isExit() {
        return exit;
    }

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
                command.execute(this,commandLine.getArgument());
            }
        }
    }
}
