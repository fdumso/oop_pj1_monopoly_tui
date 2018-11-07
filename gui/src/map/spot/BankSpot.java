package map.spot;

import gui.MainPage;
import player.Player;
import util.BankSystem;

public class BankSpot extends Spot {

    public BankSpot(String name, int x, int y, int h, int w) {
        super(name, Type.BANK, x, y, h, w);
    }

    @Override
    public void stepIn(Player player) {
        super.stepIn(player);
        MainPage.getInstance().repaint();
        BankSystem.getInstance().showHomepage(player);
    }

    @Override
    public void stay(Player player) {
    }
}
