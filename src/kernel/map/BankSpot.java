package kernel.map;

import kernel.Game;
import kernel.Player;

/**
 * Created by freemso on 2016/4/25.
 */
public class BankSpot extends AbstractSpot {

    public BankSpot(int id, String name) {
        super(id, name, Type.BANK);
    }

    @Override
    public void stepIn(Game game, Player player) {
        game.getUI().showMessage("==========欢迎光临" + getName() + "==========");

    }

    @Override
    public void stepOut(Game game, Player player) {
        // do nothing
    }

    @Override
    public void stay(Game game, Player player) {
        // do nothing
    }
}
