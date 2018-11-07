package map.spot;

import card.Card;
import gui.MainPage;
import player.Player;
import card.CardSystem;
import player.PlayerSystem;

import javax.swing.*;

public class NewsSpot extends Spot {
    public NewsSpot(String name, int x, int y, int h, int w) {
        super(name, Type.NEWS, x, y, h, w);
    }

    @Override
    public void stay(Player player) {
        // 先随机得到一个新闻的序号
        int newsIndex = (int) (Math.random()*6);
        // 再执行这个新闻
        switch (newsIndex) {
            case 0: {
                // 新闻，受伤了
                JOptionPane.showMessageDialog(null, String.format("大新闻：玩家%s受伤，被送往医院救治2回合！", player.getName()),
                        "新闻点：" + this.getName(), JOptionPane.INFORMATION_MESSAGE, null);
                player.goToHospital();
                MainPage.getInstance().repaint();
                break;
            }
            case 1: {
                // 新闻，奖励第一地主
                // 先确定一个奖励的金额随机数（1000-2000分度值为100）
                int encouragement = (int) (Math.random() * 11 + 10) * 100;
                // 再确定谁是第一地主
                Player mostHousePlayer = PlayerSystem.getInstance().mostHousePlayer();
                mostHousePlayer.getWallet().addCash(encouragement);
                JOptionPane.showMessageDialog(null, String.format("大新闻：公开表扬第一地主%s，奖励%d元！",
                        mostHousePlayer.getName(), encouragement), "新闻点：" + this.getName(),
                        JOptionPane.INFORMATION_MESSAGE, null);
                MainPage.getInstance().repaint();
                break;
            }
            case 2: {
                // 新闻，补助土地最少者
                // 先确定一个补助的金额随机数（1000-2000分度值为100）
                int allowance = (int) (Math.random() * 11 + 10) * 100;
                // 再确定谁是土地最少者
                Player leastHousePlayer = PlayerSystem.getInstance().leastHousePlayer();
                leastHousePlayer.getWallet().addCash(allowance);
                JOptionPane.showMessageDialog(null, String.format("大新闻：公开补助土地最少者%s，补助%d元！",
                        leastHousePlayer.getName(), allowance), "新闻点：" + this.getName(),
                        JOptionPane.INFORMATION_MESSAGE, null);
                MainPage.getInstance().repaint();
                break;
            }
            case 3: {
                // 新闻，银行发利息
                JOptionPane.showMessageDialog(null, "大新闻：银行加发储金红利每个人得到存款10%！",
                        "新闻点：" + this.getName(), JOptionPane.INFORMATION_MESSAGE, null);
                for (Player p : PlayerSystem.getInstance().getPlayerList()) {
                    p.getBankAccount().addInterest(0.1);
                }
                MainPage.getInstance().repaint();
                break;
            }
            case 4: {
                // 新闻，缴税
                JOptionPane.showMessageDialog(null, "大新闻：所有人缴纳财产税10%！", "新闻点："
                        + this.getName(), JOptionPane.INFORMATION_MESSAGE, null);
                for (Player p : PlayerSystem.getInstance().getPlayerList()) {
                    p.getBankAccount().payTax(0.1);
                }
                MainPage.getInstance().repaint();
                break;
            }
            case 5: {
                // 新闻，每个人得到一张卡片
                JOptionPane.showMessageDialog(null, "大新闻：每个玩家得到一张卡片！", "新闻点："
                        + this.getName(), JOptionPane.INFORMATION_MESSAGE, null);
                for (Player p : PlayerSystem.getInstance().getPlayerList()) {
                    Card card = CardSystem.getInstance().randomCard();
                    p.getCardPacket().addCard(card);
                }
                MainPage.getInstance().repaint();
                break;
            }
        }
    }
}
