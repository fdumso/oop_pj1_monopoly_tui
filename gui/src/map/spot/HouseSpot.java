package map.spot;

import gui.MainPage;
import house.House;
import map.Street;
import player.Player;

import javax.swing.*;

public class HouseSpot extends Spot {
    private House house;

    public HouseSpot(String name, double initValue, Street street, int x, int y, int h, int w) {
        super(name, Type.HOUSE, x, y, h, w);
        house = new House(name, initValue, street);
    }

    public House getHouse() {
        return house;
    }

    @Override
    public void stay(Player player) {
        if (house.getOwner() == null) {
            int buyOrNot = JOptionPane.showConfirmDialog(null,
                    "这是一个未出售的岛屿，是否购买？", "购买岛屿", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null);
            // 判断点击的为yes还是no
            if (buyOrNot == JOptionPane.YES_OPTION) {
                // 玩家选择购买
                if (player.buy(house)) {
                    MainPage.getInstance().repaint();
                    JOptionPane.showMessageDialog(null, "恭喜！您已成功购买"+ house.getName() + "！",
                            "购买岛屿", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    JOptionPane.showMessageDialog(null, "抱歉！您的钱不够！", "购买岛屿",
                            JOptionPane.INFORMATION_MESSAGE, null);
                }
            } else if (buyOrNot == JOptionPane.NO_OPTION) {
                // 玩家选择放弃购买
                JOptionPane.showMessageDialog(null, "您已放弃购买"+ house.getName() + "！",
                        "购买岛屿", JOptionPane.INFORMATION_MESSAGE, null);
            }
        } else {
            if (house.getOwner() == player) {
                int upgradeOrNot = JOptionPane
                        .showConfirmDialog(null, String.format("这是你的房屋，目前为%d级，升级需要花费%.2f元，是否升级？",
                                house.getLevel(), house.upgradeCost()), "购买岛屿",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                // 判断点击的为yes还是no
                if (upgradeOrNot == JOptionPane.YES_OPTION) {
                    // 玩家选择了升级
                    if (house.getLevel() == 5) {
                        JOptionPane.showMessageDialog(null, "这块岛屿已经到顶级，无法继续升级了",
                                "升级岛屿", JOptionPane.INFORMATION_MESSAGE, null);
                    } else {
                        if (house.upgrade()) {
                            JOptionPane.showMessageDialog(null, String.format("恭喜！您已成功将%s升至%d级！",
                                    house.getName(), house.getLevel()), "升级岛屿",
                                    JOptionPane.INFORMATION_MESSAGE, null);
                        } else {
                            JOptionPane.showInternalMessageDialog(null, "抱歉！您的钱不够！",
                                    "升级岛屿", JOptionPane.INFORMATION_MESSAGE, null);
                        }
                    }
                } else if (upgradeOrNot == JOptionPane.NO_OPTION) {
                    // 玩家选择放弃升级
                    JOptionPane.showMessageDialog(null, "您已放弃升级"+ house.getName() + "！",
                            "升级岛屿", JOptionPane.INFORMATION_MESSAGE, null);
                }
            } else {
                JOptionPane.showMessageDialog(null, String.format("这是%s的岛屿，您需要缴纳过路费%.2f元！",
                        house.getOwner().getName(), house.getToll()), "缴纳过路费",
                        JOptionPane.INFORMATION_MESSAGE, null);
                if (player.getWallet().payCash(house.getToll())) {
                    JOptionPane.showMessageDialog(null, String.format("您已用现金支付过路费%.2f元！\n欢迎下次光临~",
                            house.getToll()), "缴纳过路费", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    double leftToll = house.getToll() - player.getWallet().getCash();
                    player.getWallet().payCash(player.getWallet().getCash());
                    if (player.getBankAccount().subDeposit(leftToll)) {
                        JOptionPane.showMessageDialog(null, String.format("您的现金不够！已用银行存款支付超出部分%.2f元！\n欢迎下次光临~",
                                leftToll), "缴纳过路费", JOptionPane.INFORMATION_MESSAGE, null);
                    } else {
                        JOptionPane.showMessageDialog(null, "您的现金和存款已不足支付过路费！",
                                "缴纳过路费", JOptionPane.INFORMATION_MESSAGE, null);
                        player.bankrupt();
                    }
                }
            }
        }
    }
}
