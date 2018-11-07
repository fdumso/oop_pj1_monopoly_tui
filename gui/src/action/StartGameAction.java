package action;

import gui.MainPage;
import player.PlayerSystem;

import javax.swing.*;

public class StartGameAction implements IAction {
    @Override
    public void trigger() {
        // check player number
        if (PlayerSystem.getInstance().getPlayerNumber() < 2) {
            JOptionPane.showMessageDialog(null, "There must be at least TWO players", "Init Players",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            MainPage.getInstance().showPanel("play");
        }
    }
}
