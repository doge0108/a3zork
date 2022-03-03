package poon.ssc.zork.Command;

import org.apache.commons.lang3.StringUtils;

public class Cmdparser {
    public static CmdLine parseCommand(String rawInput){
        for(CommandType commandType: CommandType.values()){
            if(commandType.match(rawInput)){
                if(commandType.getCommandWord() != ""){
                    if (commandType.getCommandWord() == "play"|| commandType.getCommandWord() == "save" || commandType.getCommandWord() == "load"){
                        String argument = rawInput.substring(5);
                        return  new CmdLine(commandType,argument);
                    }
                    if(commandType.getCommandWord() == "autopilot"){
                        String argument = rawInput.substring(10);
                        return new CmdLine(commandType,argument);
                    }
                    String argument = rawInput.substring(3);
                    return new CmdLine(commandType,argument);
                }
                String argument = rawInput.replace(commandType.getCommandWord(), "").trim();
                argument = StringUtils.isBlank(argument)? null : argument;
                return new CmdLine(commandType, argument);
            }
        }
        return null;
    }
}
