package kernel.card;

import kernel.Game;
import kernel.Player;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/25.
 */
public class BarricadeCard extends AbstractCard {
    public BarricadeCard() {
        super("路障卡", 6);
    }

    @Override
    public boolean effect(Game game, int playerId) {
        int spotId = game.chooseASpot();

    }
}
