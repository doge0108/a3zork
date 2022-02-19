package poon.ssc.zork.Command;

public class CmdLine {

    private CommandType commandType;

    private String argument;

    public CmdLine(CommandType commandType, String argument) {
        this.commandType = commandType;
        this.argument = argument;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getArgument() {
        return argument;
    }

}
