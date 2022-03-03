package poon.ssc.zork.Command;

import poon.ssc.zork.Command.impl.*;

public enum CommandType {

    INFO(InfoCmd .class, "info"),
    EXIT(ExitCmd.class,"exit"),
    HELP(HelpCmd.class,"help"),
    GO(GoCmd.class,"go"),
    ATTACK(AttackCmd.class,"attack with"),
    TAKE(TakeCmd.class,"take"),
    USE(UseCmd.class,"use"),
    PLAY(PlayCmd.class,"play"),
    SAVE(SaveCmd.class,"save"),
    LOAD(LoadCmd.class,"load"),
    QUIT(QuitCmd.class,"quit"),
    DROP(DropCmd.class,"drop"),
    AUTOPILOT(autoPilotCmd.class,"autopilot");

    private Class<?extends Command>commandClass;

    private String commandWord;

    CommandType(Class<? extends Command> commandClass, String commandWord) {
        this.commandClass = commandClass;
        this.commandWord = commandWord;
    }

    public Class getCommandClass(){
        return commandClass;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public boolean match(String rawInput){
        return rawInput.startsWith(commandWord);
    }
}
