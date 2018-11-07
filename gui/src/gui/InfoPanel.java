package gui;

import util.io.FontIO;
import util.io.ImageIO;
import player.PlayerSystem;
import util.TimeSystem;

import javax.swing.*;
import java.awt.*;

class InfoPanel extends JPanel {

    InfoPanel() {
        this.setLayout(null);

        TimePanel timePanel = new TimePanel();
        timePanel.setFont(FontIO.getWD(45));
        this.add(timePanel);
        timePanel.setBounds(10, 10, 280, 100);

        PlayerPanel playerPanel = new PlayerPanel();
        playerPanel.setFont(FontIO.getWD(30));
        this.add(playerPanel);
        playerPanel.setBounds(10, 120, 280, 500);
    }

    private class TimePanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            FontMetrics fm = g.getFontMetrics();
            int stringWidth = fm.stringWidth(TimeSystem.getInstance().getDate());
            int stringAscent = fm.getAscent();
            int xCoordinate = getWidth() / 2 - stringWidth / 2;
            int yCoordinate = getHeight() / 2 + stringAscent / 2;
            g.drawString(TimeSystem.getInstance().getDate(), xCoordinate, yCoordinate);
        }
    }

    private class PlayerPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(ImageIO.getInstance().getCharAvatar(PlayerSystem.getInstance().getCurrentPlayer().getCharacter()).getImage(), 20, 30, 150, 160, this);
            g.drawString("玩家：", 180, 90);
            g.drawString(PlayerSystem.getInstance().getCurrentPlayer().getName(), 180, 140);
            g.drawString(
                    "方向 "
                            + String.format("%7s", PlayerSystem.getInstance().getCurrentPlayer().getDirection()), 20, 250);
            g.drawString(
                    "现金    "
                            + String.format("%7.2f", PlayerSystem.getInstance().getCurrentPlayer().getWallet().getCash()), 20, 300);
            g.drawString(
                    "银行存款"
                            + String.format("%7.2f", PlayerSystem.getInstance().getCurrentPlayer().getBankAccount().getDeposit()), 20, 350);
            g.drawString(
                    "点券    "
                            + String.format("%7d", PlayerSystem.getInstance().getCurrentPlayer().getWallet().getTicket()), 20, 400);
            g.drawString(
                    "房产数  "
                            + String.format("%7d", PlayerSystem.getInstance().getCurrentPlayer().getEstate().getHouseNum()), 20, 450);
            g.drawString(
                    "总资产  "
                            + String.format("%7.2f", PlayerSystem.getInstance().getCurrentPlayer().getCapital()), 20, 500);
        }
    }

}





