package map.spot;

import player.Player;

public class HospitalSpot extends Spot {
    public HospitalSpot(String name, int x, int y, int h, int w) {
        super(name, Type.HOSPITAL, x, y, h, w);
    }

    @Override
    public void stay(Player player) {
        // nothing happened
    }
}
