package poon.ssc.zork.Command.impl;

import poon.ssc.zork.Command.CmdFactory;
import poon.ssc.zork.Command.Command;
import poon.ssc.zork.Command.CommandType;
import poon.ssc.zork.Game;

public class HelpCmd implements Command{
    public CmdFactory cmf = new CmdFactory();

    public void execute(Game game, String argument){
        for(CommandType c:cmf.getAllCommandList()){
            System.out.println(c.getCommandWord());
        }
    }
}
