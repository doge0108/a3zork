package poon.ssc.zork.Command;

public class Cmdparser {
    public static CommandType parseCommand(String rawInput){
        switch(rawInput){
            case "exit": return CommandType.EXIT;
            case "info": return CommandType.INFO;
            default:
                return null;
        }
    }
}
