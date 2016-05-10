package spot;

import player.Player;
import util.Game;
import util.Position;

/**
 * Created by freemso on 2016/4/25.
 */
public class EmptySpot extends AbstractSpot {
    public EmptySpot(int id, String name, Position position) {
        super(id, name, SpotType.EMPTY, position);
    }

    @Override
    public void stepIn(Game game, Player player) {
        addPlayer(player);
    }

    @Override
    public void stepOut(Game game, Player player) {
        removePlayer(player);
    }

    @Override
    public void stay(Game game, Player player) {
        game.getUI().showMessage("你停留在了" + getName());
        game.getUI().showMessage("这是一个空地，什么都没有发生");
    }
}
