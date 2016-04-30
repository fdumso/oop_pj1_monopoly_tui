package kernel.spot;

import kernel.Game;
import kernel.Player;
import kernel.card.AbstractCard;

/**
 * Created by freemso on 2016/4/25.
 */
public class CardSpot extends AbstractSpot {
    public CardSpot(int id, String name) {
        super(id, name, Type.CARD);
    }

    @Override
    public void stepIn(Game game, Player player) {

    }

    @Override
    public void stepOut(Game game, Player player) {

    }

    @Override
    public void stay(Game game, Player player) {
        game.getUI().popMessage("欢迎来到" + getName() + "，你有机会随机得到一张卡片，现在开始抽奖");
        AbstractCard card = game.getCardSystem().randomCard();
        player.addCard(card);
        game.getUI().popMessage("你获得了一张" + card.getName() + "！");
    }
}
