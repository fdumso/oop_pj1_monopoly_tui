package kernel.util;

import kernel.Game;
import kernel.Player;
import kernel.spot.AbstractSpot;
import kernel.spot.HouseSpot;

/**
 * Created by freemso on 2016/4/30.
 */
public class SpotSystem {

    public enum Type {
        BANK, EMPTY, HOUSE, LOTTERY, NEWS, CARD, TICKET, STORE
    }

    public void showSpotInfo(Game game, Player player) {
        AbstractSpot spot = player.selectSpot(game);
        SpotSystem.Type type = spot.getType();
        switch (type) {
            case HOUSE: {
                game.getUI().popMessage("类型：房产\n名称：" + spot.getName() + "\n初始价格：" + ((HouseSpot) spot).getOriginalPrice()
                        + " 元\n等级：" + ((HouseSpot) spot).getLevel() + " 级\n当前价格：" + ((HouseSpot) spot).calcPrice() + " 元\n拥有者："
                        + ((HouseSpot) spot).getOwner().getName());
                break;
            }
            case BANK: {
                game.getUI().popMessage("类型：银行\n名称：" + spot.getName());
                break;
            }
            case EMPTY: {
                game.getUI().popMessage("类型：空地\n名称：" + spot.getName());
                break;
            }
            case CARD: {
                game.getUI().popMessage("类型：赠送卡片点\n名称：" + spot.getName());
                break;
            }
            case TICKET: {
                game.getUI().popMessage("类型：赠送点券点\n名称：" + spot.getName());
                break;
            }
            case LOTTERY: {
                game.getUI().popMessage("类型：彩票店\n名称：" + spot.getName());
                break;
            }
            case NEWS: {
                game.getUI().popMessage("类型：新闻点\n名称：" + spot.getName());
                break;
            }
            case STORE: {
                game.getUI().popMessage("类型：道具店\n名称：" + spot.getName());
                break;
            }
        }
    }

}
