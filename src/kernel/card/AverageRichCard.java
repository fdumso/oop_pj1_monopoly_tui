package kernel.card;

import kernel.Game;
import kernel.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by freemso on 2016/4/25.
 */
public class AverageRichCard extends AbstractCard {
    public AverageRichCard() {
        super("均富卡", 4);
    }

    @Override
    public boolean effect(Game game, int playerId) {
        ArrayList<Player> playerList = game.getPlayerList();
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
