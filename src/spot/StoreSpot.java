package spot;

import card.AbstractCard;
import card.CardType;
import player.Player;
import util.Game;
import util.Position;

/**
 * Created by freemso on 2016/4/25.
 */
public class StoreSpot extends AbstractSpot {
    public StoreSpot(int id, String name, Position position) {
        super(id, name, SpotType.STORE, position);
    }

    @Override
    public void stepIn(Game game, Player player) {
        addPlayer(player);
    }

    @Override
    public void stay(Game game, Player player) {
        game.getUI().showMessage("==========欢迎光临道具店==========");
        loop: do {
            int cardIndex = game.getUI().inputInt("你目前的点券余额为" + player.getTicket()
                    + "点\n道具店现在提供如下道具购买：\n"
                    + "0.均富卡-4点券\n1.路障卡-6点券\n2.购房卡-10点券\n"
                    + "3.拆迁卡-6点券\n4.遥控骰子-4点券\n5.滞留卡-4点券\n"
                    + "6.转向卡-4点券\n"
                    + "请输入您想购买的卡片<0-6> 输入-1退出：", -1, 6);
            CardType type;
            switch (cardIndex) {
                case 0: {
                    type = CardType.AVERAGE_RICH;
                    break;
                }
                case 1: {
                    type = CardType.BARRICADE;
                    break;
                }
                case 2: {
                    type = CardType.BUY_HOUSE;
                    break;
                }
                case 3: {
                    type = CardType.DEMOLITION;
                    break;
                }
                case 4: {
                    type = CardType.CONTROL_DICE;
                    break;
                }
                case 5: {
                    type = CardType.RESIDENCE;
                    break;
                }
                case 6: {
                    type = CardType.TURN_AROUND;
                    break;
                }
                default: {
                    break loop;
                }
            }
            AbstractCard card = game.getCardSystem().getCard(type);
            if (card.getPrice() <= player.getTicket()) {
                player.subTicket(card.getPrice());
                player.addCard(card);
                game.getUI().showMessage("恭喜！你已成功购买一张" + card.getName() + "！");
            } else {
                game.getUI().showMessage("你的点券数不够购买这张卡！请重新选择一张卡片！");
            }
        } while (true);
    }
}
