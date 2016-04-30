package kernel.spot;

import kernel.Game;
import kernel.Player;

/**
 * Created by freemso on 2016/4/25.
 */
public class EmptySpot extends AbstractSpot {
    public EmptySpot(int id, String name) {
        super(id, name, Type.EMPTY);
    }

    @Override
    public void stepIn(Game game, Player player) {

    }

    @Override
    public void stepOut(Game game, Player player) {

    }

    @Override
    public void stay(Game game, Player player) {
        game.getUI().showMessage("这是一个空地，什么都没有发生");
    }
}
