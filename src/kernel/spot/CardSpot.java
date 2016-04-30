package kernel.spot;

import kernel.Game;
import kernel.Player;
import kernel.Position;
import kernel.SpotType;
import kernel.card.AbstractCard;

/**
 * Created by freemso on 2016/4/25.
 */
public class CardSpot extends AbstractSpot {
    public CardSpot(int id, String name, Position position) {
        super(id, name, SpotType.CARD, position);
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
        game.getUI().showMessage("欢迎来到" + getName() + "，你有机会随机得到一张卡片，现在开始抽奖");
        AbstractCard card = game.getCardSystem().randomCard();
        player.addCard(card);
        game.getUI().showMessage("你获得了一张" + card.getName() + "！");
    }
}
