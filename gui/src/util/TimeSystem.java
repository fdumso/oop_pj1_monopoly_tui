package util;


import lottery.LotterySystem;
import player.Player;
import player.PlayerSystem;
import stock.StockSystem;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeSystem {
    private static final TimeSystem timeSystem = new TimeSystem();
    private TimeSystem() {}
    public static TimeSystem getInstance() {
        return timeSystem;
    }


    private int totalRounds;
    private int round = 1;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar calendar = new GregorianCalendar();

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

    public String getDate() {
        return format.format(calendar.getTime());
    }

    private boolean isTheEndOfTheMonth() {
        calendar.add(Calendar.DATE, 1);
        boolean isTheEndOfTheMonth = calendar.get(Calendar.DATE) == 1;
        calendar.add(Calendar.DATE, -1);
        return isTheEndOfTheMonth;
    }

    public void endDay() {
        StockSystem.getInstance().fluc();
        if (isTheEndOfTheMonth()) {
            // end of the month
            // 银行利息
            JOptionPane.showMessageDialog(null, "月底银行发利息！");
            for (Player p : PlayerSystem.getInstance().getPlayerList()) {
                p.getBankAccount().addInterest(0.1);
            }
            LotterySystem.getInstance().open();
        }
        calendar.add(Calendar.DATE, 1);
        round++;
    }

    public void startDay() {
        if (round > totalRounds) {
            // time is up
            Player winner = PlayerSystem.getInstance().maxCapitalPlayer();
            JOptionPane.showMessageDialog(null, "时间到，游戏结束，玩家"+winner.getName()+"获得了胜利！");
            System.exit(0);
        }
    }
}