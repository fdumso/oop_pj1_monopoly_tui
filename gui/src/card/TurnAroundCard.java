package card;

import gui.MainPage;
import player.Player;
import util.io.ImageIO;
import player.PlayerSystem;

import javax.swing.*;
import java.util.ArrayList;

public class TurnAroundCard extends Card {
    public TurnAroundCard() {
        super(Type.TURN_AROUND, 4);
    }

    @Override
    public boolean use(Player user) {
        ArrayList<String> playerNameArrayList = new ArrayList<>();
        for (Player player : PlayerSystem.getInstance().getPlayerList()) {
            if (player.getStatus()== Player.Status.NORMAL) {
                playerNameArrayList.add(player.getName());
            }
        }
        Object[] optionList = playerNameArrayList.toArray();
        String targetName = (String) JOptionPane.showInputDialog(null,
                "请选择您想施用的目标", "使用转向卡",
                JOptionPane.PLAIN_MESSAGE, ImageIO.getInstance().getCardIcon(this), optionList, null);
        for (Player player : PlayerSystem.getInstance().getPlayerList()) {
            if (player.getName().equals(targetName)) {
                player.turnAround();
                MainPage.getInstance().repaint();
                JOptionPane.showMessageDialog(null, String.format("使用成功!%s的方向变为%s",
                        player.getName(), player.getDirection()));
                return true;
            }
        }
        return false;
    }
}
