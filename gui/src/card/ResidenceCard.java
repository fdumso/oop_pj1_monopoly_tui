package card;

import gui.MainPage;
import player.Player;

import javax.swing.*;

public class ResidenceCard extends Card {
    public ResidenceCard() {
        super(Type.RESIDENCE, 4);
    }

    @Override
    public boolean use(Player user) {
        MainPage.getInstance().repaint();
        JOptionPane.showMessageDialog(null, "您使用了滞留卡，将原地滞留1回合");
        user.setResident();
        return true;
    }
}
