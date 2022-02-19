package poon.ssc.zork;

import poon.ssc.zork.Command.CmdFactory;
import poon.ssc.zork.Command.Cmdparser;
import poon.ssc.zork.Command.Command;
import poon.ssc.zork.Command.CommandType;

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
            CommandType commandType = Cmdparser.parseCommand(rawInput);
            Command command = CmdFactory.get(commandType);
            if(command == null){
                System.out.println("Try again");
            }else{
                command.execute(this);
            }
        }
    }
}
