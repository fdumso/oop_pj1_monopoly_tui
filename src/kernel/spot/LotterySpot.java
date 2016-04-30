package kernel.spot;

import kernel.Game;
import kernel.Player;
import kernel.map.Position;
import kernel.util.SpotSystem;

/**
 * Created by freemso on 2016/4/25.
 */
public class LotterySpot extends AbstractSpot {
    public LotterySpot(int id, String name, Position position) {
        super(id, name, SpotSystem.Type.LOTTERY, position);
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