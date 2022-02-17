package poon.ssc.zork.Command;

import poon.ssc.zork.Game;

public class ExitCmd implements Command{

    @Override
    public void execute(Game game){
        game.exit();

    }
}
