package poon.ssc.zork.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CmdFactory {

    private static Map<CommandType, Command> commandMap = new HashMap<>();

    static{
        for (CommandType commandType: CommandType.values()){
            Command command = null;
            try {
                command = (Command) commandType.getCommandClass().getDeclaredConstructor().newInstance();
                commandMap.put(commandType, command);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static Command get(CommandType commandType){
        return commandMap.get(commandType);
    }

    public static Map<CommandType,Command>getCommandMap(){
        return commandMap;
    }
}
