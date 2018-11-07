package map.spot;

import card.*;
import gui.MainPage;
import player.Player;
import util.io.ImageIO;

import javax.swing.*;

public class PropsMartSpot extends Spot {
    public PropsMartSpot(String name, int x, int y, int h, int w) {
        super(name, Type.PROPS_MART, x, y, h, w);
    }

    @Override
    public void stay(Player player) {
        Object[] cardsObjects = { "0.均富卡-4点券", "1.路障卡-6点券", "2.购房卡-10点券",
                "3.拆迁卡-6点券", "4.遥控骰子-4点券", "5.滞留卡-4点券", "6.转向卡-4点券" };
        while (true) {
            String cardStr = (String) JOptionPane.showInputDialog(null,
                    "欢 迎 光 临 道 具 店 ！\n" + "您目前的点券余额为"
                            + player.getWallet().getTicket()
                            + "点\n请选择你要购买的道具：\n", "道具店：" + this.getName(),
                    JOptionPane.PLAIN_MESSAGE, null, cardsObjects,
                    null);
            Card cardToBuy;
            if (cardStr == null) {
                return;
            } else {
                switch (cardStr.charAt(0)) {
                    case '0':
                        cardToBuy = new AverageRichCard();
                        break;
                    case '1':
                        cardToBuy = new BarricadeCard();
                        break;
                    case '2':
                        cardToBuy = new BuyHouseCard();
                        break;
                    case '3':
                        cardToBuy = new DemolitionCard();
                        break;
                    case '4':
                        cardToBuy = new ControlDiceCard();
                        break;
                    case '5':
                        cardToBuy = new ResidenceCard();
                        break;
                    case '6':
                        cardToBuy = new TurnAroundCard();
                        break;
                    default:
                        return;
                }
            }
            // 购买卡片
            // 判断玩家点券数是否够
            if (player.getWallet().payTicket(cardToBuy.getPrice())) {
                player.getCardPacket().addCard(cardToBuy);
                MainPage.getInstance().repaint();
                JOptionPane.showMessageDialog(null, "恭喜您！您已成功购买" + cardToBuy.getName() + "！", "购买道具",
                        JOptionPane.INFORMATION_MESSAGE, ImageIO.getInstance().getCardIcon(cardToBuy));
            } else {
                JOptionPane.showMessageDialog(null, "您的点券数不够购买这张卡！请重新选择一张卡片！",
                        "购买道具", JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
    }
}
