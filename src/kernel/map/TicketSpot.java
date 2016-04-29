package kernel.map;

import kernel.Game;
import kernel.Player;

/**
 * Created by freemso on 2016/4/25.
 */
public class TicketSpot extends AbstractSpot {
    public TicketSpot(int id, String name) {
        super(id, name, Type.TICKET);
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
