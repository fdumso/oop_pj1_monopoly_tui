package kernel.spot;

import kernel.Game;
import kernel.Player;
import kernel.Position;
import kernel.SpotType;

/**
 * Created by freemso on 2016/4/25.
 */
public class LotterySpot extends AbstractSpot {
    public LotterySpot(int id, String name, Position position) {
        super(id, name, SpotType.LOTTERY, position);
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
