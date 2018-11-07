package action;

import map.spot.Spot;

import javax.swing.*;

public class CheckSpotAction implements IAction {
    private Spot spot;

    public CheckSpotAction(Spot spot) {
        this.spot = spot;
    }

    @Override
    public void trigger() {
        JOptionPane.showMessageDialog(null, "名称："+spot.getName());
    }
}
