package poon.ssc.zork.Command.impl;

import poon.ssc.zork.Command.Command;
import poon.ssc.zork.Game;

public class InfoCmd implements Command {

    @Override
    public void execute(Game game) {
        System.out.println("Print info");
    }
}
