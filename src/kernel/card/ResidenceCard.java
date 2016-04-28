package kernel.card;

import kernel.Game;
import kernel.Player;

/**
 * Created by freemso on 2016/4/25.
 */
public class ResidenceCard extends AbstractCard {
    public ResidenceCard() {
        super("滞留卡", 4);
    }

    @Override
    public boolean effect(Game game, Player user) {
        game.getGameUI().showMessage("你使用了滞留卡，停留原地一回合");
        game.getDice().setPoint(0);
        return true;
    }
}
