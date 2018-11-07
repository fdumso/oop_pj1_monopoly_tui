package map.spot;

import player.Player;
import lottery.LotterySystem;

public class LotterySpot extends Spot {
    public LotterySpot(String name, int x, int y, int h, int w) {
        super(name, Type.LOTTERY, x, y, h, w);
    }

    @Override
    public void stay(Player player) {
        LotterySystem.getInstance().showHomepage(player);
    }
}
