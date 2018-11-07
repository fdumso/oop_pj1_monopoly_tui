package spot;

import player.Player;
import util.Game;
import util.Position;

/**
 * Created by freemso on 2016/4/25.
 */
public class TicketSpot extends AbstractSpot {
    public TicketSpot(int id, String name, Position position) {
        super(id, name, SpotType.TICKET, position);
    }

    @Override
    public void stepIn(Game game, Player player) {
        addPlayer(player);
    }

    @Override
    public void stay(Game game, Player player) {
        game.getUI().showMessage("欢迎来到" + getName() + "，你有机会随机得到一些点券，现在开始抽奖");
        int number = (int) (Math.random() * 16);
        player.addTicket(number);
        game.getUI().showMessage("你获得了 " + number + " 张点券！");
    }
}
