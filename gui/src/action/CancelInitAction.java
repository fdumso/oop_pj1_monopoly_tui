package action;

import gui.MainPage;
import player.PlayerSystem;

public class CancelInitAction implements IAction {
    @Override
    public void trigger() {
        // clear player list
        PlayerSystem.getInstance().clearPlayerList();
        MainPage.getInstance().showPanel("start");
    }
}
