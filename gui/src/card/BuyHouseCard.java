package card;

import gui.MainPage;
import house.House;
import map.spot.HouseSpot;
import map.spot.Spot;
import player.Player;

import javax.swing.*;

public class BuyHouseCard extends Card {
    public BuyHouseCard() {
        super(Type.BUY_HOUSE, 10);
    }

    @Override
    public boolean use(Player user) {
        Spot spot = user.getPosition();
        // check whether the spot that the user is standing on is a house
        if (spot.getType() != Spot.Type.HOUSE) {
            // it is not a building
            JOptionPane.showMessageDialog(null, "这不是一个房屋，你不能使用购房卡！");
            return false;
        }
        // check the owner of the house
        House house = ((HouseSpot) spot).getHouse();
        if (house.getOwner() == user) {
            // this is the user's house
            JOptionPane.showMessageDialog(null, "这是你自己的房屋，无法再次购买！");
            return false;
        }
        double price = house.getValue();
        int buyOrNot = JOptionPane.showConfirmDialog(null,
                String.format("这个房屋的价格为%.2f元，是否使用购房卡购买？", price), "购买岛屿", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null);
        // 判断点击的为yes还是no
        if (buyOrNot == JOptionPane.YES_OPTION) {
            // 玩家选择购买
            if (user.buy(house)) {
                MainPage.getInstance().repaint();
                JOptionPane.showMessageDialog(null, "恭喜！您已成功购买"+ house.getName() + "！",
                        "购买岛屿", JOptionPane.INFORMATION_MESSAGE, null);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "抱歉！您的钱不够！", "购买岛屿",
                        JOptionPane.INFORMATION_MESSAGE, null);
                return false;
            }
        } else {
            // 玩家选择放弃购买
            JOptionPane.showMessageDialog(null, "您已放弃购买"+ house.getName() + "！",
                    "购买岛屿", JOptionPane.INFORMATION_MESSAGE, null);
            return false;
        }
    }
}
