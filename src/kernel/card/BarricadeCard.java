package kernel.card;

import kernel.Game;
import kernel.Player;
import kernel.map.AbstractSpot;

/**
 * Created by freemso on 2016/4/25.
 */
public class BarricadeCard extends AbstractCard {
    public BarricadeCard() {
        super("路障卡", 6);
    }

    @Override
    public boolean effect(Game game, Player user) {
        AbstractSpot spot = game.selectSpot(user);
        if (game.getMap().calcDistance(spot.getId(), user.getPosition()) > 8) {
            game.getUI().popMessage("不在范围内！请重新选择");
            return effect(game, user);
        }
        if (spot.hasBarricade()) {
            game.getUI().popMessage("此处已有路障，不能再次放置！");
            return effect(game, user);
        }
        game.getUI().popMessage("路障已成功放置！");
        spot.addBarricade();
        return true;
    }
}
