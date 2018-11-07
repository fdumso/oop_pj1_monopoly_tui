package action;

import gui.MainPage;
import util.TimeSystem;

import javax.swing.*;

public class InitGameAction implements IAction {
    @Override
    public void trigger() {
        // ask user to set total rounds
        Object[] roundsObj = { "10", "30", "50", "100", "365", "1000000" };
        String roundsStr = (String) JOptionPane.showInputDialog(
                null, "Please select total rounds: ", "Set Total Rounds", JOptionPane.PLAIN_MESSAGE,
                null, roundsObj ,null);
        if (roundsStr != null) {
            int totalRounds = Integer.parseInt(roundsStr);
            TimeSystem.getInstance().setTotalRounds(totalRounds);
        }
        // jump to choose character panel
        MainPage.getInstance().showPanel("init");
    }
}
