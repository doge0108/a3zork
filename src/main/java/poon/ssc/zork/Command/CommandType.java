package poon.ssc.zork.Command;

import poon.ssc.zork.Command.impl.ExitCmd;
import poon.ssc.zork.Command.impl.InfoCmd;

public enum CommandType {

    INFO(InfoCmd .class),
    EXIT(ExitCmd.class);

    private Class<?extends Command>commandClass;

    CommandType(Class<? extends Command> commandClass) {
        this.commandClass = commandClass;
    }
    public Class getCommandClass(){
        return commandClass;
    }
}
