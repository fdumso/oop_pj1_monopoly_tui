package kernel.spot;

import kernel.Game;
import kernel.Player;
import kernel.map.Position;
import kernel.util.SpotSystem;

/**
 * Created by freemso on 2016/4/25.
 */
public class NewsSpot extends AbstractSpot {
    public NewsSpot(int id, String name, Position position) {
        super(id, name, SpotSystem.Type.NEWS, position);
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

    }
}
