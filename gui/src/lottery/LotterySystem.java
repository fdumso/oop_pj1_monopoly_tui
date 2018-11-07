package lottery;

import gui.MainPage;
import player.Player;

import javax.swing.*;
import java.util.ArrayList;

public class LotterySystem {
    private static final LotterySystem lotterySystem = new LotterySystem();
    private LotterySystem() {
        lotteryList = new ArrayList<>();
    }
    public static LotterySystem getInstance() {
        return lotterySystem;
    }
    private ArrayList<Lottery> lotteryList;

    public void showHomepage(Player player) {
        Loop: while (true) {
            String numStr = (String) JOptionPane.showInputDialog(null, "欢迎来到彩票店，一张彩票200元\n请选择您想要购买的号码：\n",
                    "彩票", JOptionPane.PLAIN_MESSAGE, null, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, null);
            if (numStr != null) {
                int number = Integer.parseInt(numStr);
                for (Lottery lottery : lotteryList) {
                    if (lottery.getNumber() == number) {
                        JOptionPane.showMessageDialog(null, String.format("%d号已经被买走了",
                                number), "彩票", JOptionPane.INFORMATION_MESSAGE, null);
                        continue Loop;
                    }
                }
                if (player.getWallet().payCash(200)) {
                    Lottery lottery = new Lottery(number, player);
                    lotteryList.add(lottery);
                    MainPage.getInstance().repaint();
                    JOptionPane.showMessageDialog(null, String.format("您成功购买了%d号",
                            number), "彩票", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    JOptionPane.showInternalMessageDialog(null, "抱歉！您的钱不够！",
                            "购买彩票", JOptionPane.INFORMATION_MESSAGE, null);
                }
            } else {
                break;
            }
        }
    }

    public void open() {
        int number = (int) (Math.random() * 10) + 1;
        int prize = 1000 + lotteryList.size()*200;
        JOptionPane.showMessageDialog(null, String.format("彩票开奖！\n" + "本期中奖号码为%d",
                number), "彩票", JOptionPane.INFORMATION_MESSAGE, null);
        lotteryList.stream().filter(lottery -> lottery.getNumber() == number).forEach(lottery -> {
            lottery.getOwner().getWallet().addCash(prize);
            JOptionPane.showMessageDialog(null, String.format("中奖！\n" + "恭喜%s获得了%d元！",
                    lottery.getOwner().getName(), prize), "彩票", JOptionPane.INFORMATION_MESSAGE, null);
            MainPage.getInstance().repaint();
        });
        lotteryList.removeAll(lotteryList);
    }
}
