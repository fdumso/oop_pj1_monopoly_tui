package card;

import gui.MainPage;
import map.Street;
import map.spot.HouseSpot;
import map.spot.Spot;
import player.Player;

import javax.swing.*;

public class DemolitionCard extends Card {
    public DemolitionCard() {
        super(Type.DEMOLITION, 6);
    }

    @Override
    public boolean use(Player user) {
        if(user.getPosition().getType() != Spot.Type.HOUSE) {
            JOptionPane.showMessageDialog(null, "你所在的位置不属于任何街道，无法使用拆迁卡！");
            return false;
        } else {
            Street street = ((HouseSpot) user.getPosition()).getHouse().getStreet();
            street.demolition();
            MainPage.getInstance().repaint();
            JOptionPane.showMessageDialog(null, "已经成功使用拆迁卡，"
                    + street.getName() + "上所有的房屋全部被拆除，补偿款已发给各个房主");
            return true;
        }
    }
}
