package card;

import gui.MainPage;
import map.spot.Spot;
import player.Player;
import util.io.ImageIO;

import javax.swing.*;

public class BarricadeCard extends Card {
    public BarricadeCard() {
        super(Type.BARRICADE, 6);
    }

    @Override
    public boolean use(Player user) {
        Object[] inputObj = { "1", "2", "3", "4", "5", "6", "7", "8", "-1",
                "-2", "-3", "-4", "-5", "-6", "-7", "-8", "0" };
        String inputStr = (String) JOptionPane.showInputDialog(null,
                "请选择您想放置路障的点与您相差的步数<顺时针方向为正，逆时针方向为负>：", "使用路障卡",
                JOptionPane.PLAIN_MESSAGE, ImageIO.getInstance().getCardIcon(this), inputObj, null);
        if (inputStr == null) {
            return false;
        } else {
            int input = Integer.parseInt(inputStr);
            // 确定放置路障的点
            Spot spot = user.getPosition();
            if (input >= 0) {
                for (int i = 0; i < input; i++) {
                    spot = spot.getNext();
                }
            } else {
                for (int i = 0; i < -input; i++) {
                    spot = spot.getPrev();
                }
            }
            // 判断是否已有路障
            if (spot.hasBarricade()) {
                JOptionPane.showMessageDialog(null, "此处已有路障，不能再次放置！", "使用路障卡",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else {
                spot.setBarricade();
                MainPage.getInstance().repaint();
                JOptionPane.showMessageDialog(null, "路障已成功放置！", "使用路障卡",
                        JOptionPane.INFORMATION_MESSAGE, null);
                return true;
            }
        }
    }
}
