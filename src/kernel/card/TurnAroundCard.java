package kernel.card;

import kernel.Game;
import kernel.Player;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/25.
 */
public class TurnAroundCard extends AbstractCard {
    public TurnAroundCard() {
        super("转向卡", 4);
    }

    @Override
    public boolean effect(Game game, Player user) {
        ArrayList<Player> playerList = game.getPlayerList();
        int receiverId = game.getGameUI().getIntegerMessage("输入你想施用对象的ID：", 0, playerList.size());
        Player receiver = playerList.get(receiverId);

    }
}
