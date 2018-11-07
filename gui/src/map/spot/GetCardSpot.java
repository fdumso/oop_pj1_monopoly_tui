package map.spot;

import card.Card;
import gui.MainPage;
import player.Player;
import card.CardSystem;
import util.io.ImageIO;

import javax.swing.*;

public class GetCardSpot extends Spot {
    public GetCardSpot(String name, int x, int y, int h, int w) {
        super(name, Type.GET_CARD, x, y, h, w);
    }

    @Override
    public void stay(Player player) {
        Card card = CardSystem.getInstance().randomCard();
        player.getCardPacket().addCard(card);
        MainPage.getInstance().repaint();
        JOptionPane.showMessageDialog(null, "恭喜您！您获得了一张" + card.getName() + "！",
                "赠送卡片点：" + this.getName(), JOptionPane.INFORMATION_MESSAGE,
                ImageIO.getInstance().getCardIcon(card));
    }
}
