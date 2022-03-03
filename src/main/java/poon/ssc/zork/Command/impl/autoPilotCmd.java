package poon.ssc.zork.Command.impl;

import poon.ssc.zork.Command.Command;
import poon.ssc.zork.Game;

public class autoPilotCmd implements Command {

    @Override
    public void execute(Game game, String argument) {
        game.autoPilot(argument);
    }
}
