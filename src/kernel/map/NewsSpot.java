package kernel.map;

import kernel.Game;
import kernel.Player;

/**
 * Created by freemso on 2016/4/25.
 */
public class NewsSpot extends AbstractSpot {
    public NewsSpot(int id, String name) {
        super(id, name, Type.NEWS);
    }

    @Override
    public void stepIn(Game game, Player player) {

    }

    @Override
    public void stepOut(Game game, Player player) {

    }

    @Override
    public void stay(Game game, Player player) {

    }
}
