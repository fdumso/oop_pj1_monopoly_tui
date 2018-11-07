package card;

import gui.MainPage;
import player.Player;
import player.PlayerSystem;

import javax.swing.*;
import java.util.ArrayList;

public class AverageRichCard extends Card {
    public AverageRichCard() {
        super(Type.AVERAGE_RICH, 4);
    }

    @Override
    public boolean use(Player user) {
        ArrayList<Player> playerList = PlayerSystem.getInstance().getPlayerList();
        double sumCash = 0;
        for (Player player : playerList) {
            sumCash += player.getWallet().getCash();
        }
        double average = sumCash / playerList.size();
        for (Player player :
                playerList) {
            player.getWallet().setCash(average);
        }
        MainPage.getInstance().repaint();
        JOptionPane.showMessageDialog(null, "使用成功！所有玩家的现金均富了！");
        return true;
    }
}
