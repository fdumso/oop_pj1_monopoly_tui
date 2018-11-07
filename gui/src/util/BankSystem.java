package util;

import gui.MainPage;
import player.Player;

import javax.swing.*;

public class BankSystem {
    private BankSystem() {}
    private static final BankSystem BANK_SYSTEM = new BankSystem();
    public static BankSystem getInstance() {
        return BANK_SYSTEM;
    }

    private static final Object[] MONEY_OPTIONS = { "100", "200", "500", "1000", "2000", "5000",
            "10000" };

    public void showHomepage(Player player) {
        while (true) {
            int bankServiceIndex = JOptionPane.showOptionDialog(null,
                    "欢 迎 光 临 海 贼 大 银 行 ！", "银行",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, new Object[] {
                            "存款", "取款", "退出" }, null);
            // 判断要进行什么业务
            if (bankServiceIndex == 0) {
                // 玩家选择存款
                store(player);
            } else if (bankServiceIndex == 1) {
                // 玩家选择取款
                withdraw(player);
            } else if (bankServiceIndex == 2) {
                // 玩家选择退出
                JOptionPane.showMessageDialog(null, "谢谢您的光临，下次再见！", "银行", JOptionPane.INFORMATION_MESSAGE, null);
                break;
            }
        }
    }

    private void store(Player player) {
        double cashBefore = player.getWallet().getCash();
        double depositsBefore = player.getBankAccount().getDeposit();
        String moneyStr = (String) JOptionPane.showInputDialog(null,
                String.format("请选择您想要存入的现金数目<0-%.2f>：\n", cashBefore), "存款", JOptionPane.PLAIN_MESSAGE,
                null, MONEY_OPTIONS, null);
        if (moneyStr != null) {
            int money = Integer.parseInt(moneyStr);
            if (money >= 0 && money <= cashBefore) {
                // 输入在范围内，则开始业务办理
                if (player.getWallet().payCash(money)) {
                    player.getBankAccount().addDeposit(money);
                }
                MainPage.getInstance().repaint();
                JOptionPane.showMessageDialog(null, String.format("您的现金从%.2f元减少到%.2f元\n"
                                + "您的存款从%.2f元增加到%.2f元\n"
                                + "您的存款业务已经完成！",
                        cashBefore, player.getWallet().getCash(),
                        depositsBefore, player.getBankAccount().getDeposit()),
                        "存款", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                // 输入不在范围内
                JOptionPane.showMessageDialog(null, "您还没有那么多现金可存！\n请重新选择！",
                        "存款", JOptionPane.WARNING_MESSAGE, null);
            }
        }
    }

    private void withdraw(Player player) {
        double cashBefore = player.getWallet().getCash();
        double depositsBefore = player.getBankAccount().getDeposit();
        String moneyStr = (String) JOptionPane.showInputDialog(null,
                String.format("请选择您想要取出的现金数目<0-%.2f>：\n", depositsBefore), "取款", JOptionPane.PLAIN_MESSAGE,
                null, MONEY_OPTIONS, null);
        if (moneyStr != null) {
            int money = Integer.parseInt(moneyStr);
            if (money >= 0 && money <= depositsBefore) {
                // 输入在范围内，则开始业务办理
                if (player.getBankAccount().subDeposit(money)) {
                    player.getWallet().addCash(money);
                }
                MainPage.getInstance().repaint();
                JOptionPane.showMessageDialog(null, String.format("您的现金从%.2f元增加到%.2f元\n"
                                + "您的存款从%.2f元减少到%.2f元\n"
                                + "您的取款业务已经完成！",
                        cashBefore, player.getWallet().getCash(),
                        depositsBefore, player.getBankAccount().getDeposit()),
                        "取款", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                // 输入不在范围内
                JOptionPane.showMessageDialog(null, "您还没有那么多存款可取！\n请重新选择！",
                        "取款", JOptionPane.WARNING_MESSAGE, null);
            }
        }
    }
}
