package kernel.spot;

import kernel.Game;
import kernel.Player;

/**
 * Created by freemso on 2016/4/25.
 */
public class StoreSpot extends AbstractSpot {
    public StoreSpot(int id, String name) {
        super(id, name, Type.STORE);
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
