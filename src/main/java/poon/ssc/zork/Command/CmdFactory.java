package poon.ssc.zork.Command;

import java.util.HashMap;
import java.util.Map;

public class CmdFactory {

    private static Map<String, Command> commandMap = new HashMap<>();

    static{
        commandMap.put("exit", new ExitCmd());
        commandMap.put("info", new InfoCmd());
    }
    
    public static Command get(String command){
        return commandMap.get(command);
    }
}
