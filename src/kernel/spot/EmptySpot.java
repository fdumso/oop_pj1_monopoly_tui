package kernel.spot;

import kernel.Game;
import kernel.Player;
import kernel.map.Position;
import kernel.util.SpotSystem;

/**
 * Created by freemso on 2016/4/25.
 */
public class EmptySpot extends AbstractSpot {
    public EmptySpot(int id, String name, Position position) {
        super(id, name, SpotSystem.Type.EMPTY, position);
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
