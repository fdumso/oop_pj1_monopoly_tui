package card;

import gui.MainPage;
import player.Player;
import util.Dice;
import util.io.ImageIO;

import javax.swing.*;

public class ControlDiceCard extends Card {
    public ControlDiceCard() {
        super(Type.CONTROL_DICE, 4);
    }

    @Override
    public boolean use(Player user) {
        Object[] inputObj = { "1", "2", "3", "4", "5", "6"};
        String inputStr = (String) JOptionPane.showInputDialog(null,
                "请选择您想掷得的点数：", "使用遥控骰子",
                JOptionPane.PLAIN_MESSAGE, ImageIO.getInstance().getCardIcon(this), inputObj, null);
        if (inputStr == null) {
            return false;
        } else {
            int num = Integer.parseInt(inputStr);
            Dice.getInstance().cheat(num);
            MainPage.getInstance().repaint();
            JOptionPane.showMessageDialog(null, "遥控骰子使用成功，下次掷得的点数将为" + num);
            return true;
        }
    }
}
