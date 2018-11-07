package card;

import player.Player;
import util.Game;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/25.
 */
public class AverageRichCard extends AbstractCard {
    public AverageRichCard() {
        super("均富卡", 4);
    }

    @Override
    public boolean effect(Game game, Player user) {
        ArrayList<Player> playerList = game.getPlayerSystem().getPlayerList();
        double sumCash = 0;
        for (Player player : playerList) {
            sumCash += player.getCash();
        }
        double average = sumCash / playerList.size();
        for (Player player :
                playerList) {
            player.setCash(average);
        }
        return true;
    }
}
