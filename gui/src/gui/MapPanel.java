package gui;

import action.*;
import map.spot.Spot;
import util.Dice;
import util.io.ImageIO;
import map.MapSystem;
import player.PlayerSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MapPanel extends JPanel {

    MapPanel() {
        this.setLayout(null);

        // card packet button
        JLabel cardPacketLabel = new JLabel(ImageIO.getInstance().getButtonIcon("Card Packet"));
        this.add(cardPacketLabel);
        cardPacketLabel.setBounds(160, 280, 150, 230);
        cardPacketLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new CheckCardPacketAction().trigger();
            }
        });

        // dice button
        DicePanel dicePanel = new DicePanel();
        this.add(dicePanel);
        dicePanel.setBounds(660, 130, 120, 120);

        // stock mart button
        JLabel stockMartLabel = new JLabel(ImageIO.getInstance().getButtonIcon("Stock Mart"));
        this.add(stockMartLabel);
        stockMartLabel.setBounds(600, 380, 170, 170);
        stockMartLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CheckStockMartAction().trigger();
            }
        });

        // players info button
        JLabel playersInfoLabel = new JLabel(ImageIO.getInstance().getButtonIcon("Players Info"));
        this.add(playersInfoLabel);
        playersInfoLabel.setBounds(0, 50, 480, 100);
        playersInfoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CheckPlayersInfoAction().trigger();
            }
        });

        // spots
        Spot spot = MapSystem.getInstance().getBankSpot();
        SpotPanel spotPanel;
        do {
            spotPanel = new SpotPanel(spot);
            this.add(spotPanel);
            spotPanel.setBounds(spot.getX(), spot.getY(), spot.getH(), spot.getW());
        } while ((spot = spot.getNext()) != MapSystem.getInstance().getBankSpot());

    }

    private class SpotPanel extends JPanel {
        private Spot spot;

        SpotPanel(Spot spot) {
            this.spot = spot;
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new CheckSpotAction(spot).trigger();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (spot.hasPlayerOn()) {
                g.drawImage(ImageIO.getInstance().getCharIcon(spot.getTopPlayer().getCharacter()).getImage(),
                        0, 0, getWidth(), getHeight(), this);
            } else {
                if (spot.hasBarricade()) {
                    g.drawImage(ImageIO.getInstance().getButtonIcon("Barricade").getImage(),
                            0, 0, getWidth(), getHeight(), this);
                } else {
                    g.drawImage(ImageIO.getInstance().getSpotIcon(spot).getImage(),
                            0, 0, getWidth(), getHeight(), this);
                }
            }
        }

        public Spot getSpot() {
            return spot;
        }
    }

    private class DicePanel extends JPanel {
        private final Timer TIMER = new Timer(10, e -> repaint());

        DicePanel() {
            this.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (!TIMER.isRunning()) {
                        if (PlayerSystem.getInstance().getCurrentPlayer().isResident()) {
                            new PlayerMoveAction(0).trigger();
                        } else if (Dice.getInstance().isCheated()) {
                            new PlayerMoveAction(Dice.getInstance().getCheatNum()).trigger();
                            repaint();
                        } else {
                            TIMER.start();
                        }
                        PlayerSystem.getInstance().getCurrentPlayer().clearResidence();
                        Dice.getInstance().clearCheat();
                    } else {
                        TIMER.stop();
                        new PlayerMoveAction(Dice.getInstance().getPoint()).trigger();
                    }
                }
            });
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (TIMER.isRunning()) {
                g.drawImage(ImageIO.getInstance().getDiceIcon(Dice.getInstance().randomPoint()).getImage(), 0, 0, this);
            } else {
                g.drawImage(ImageIO.getInstance().getDiceIcon(Dice.getInstance().getPoint()).getImage(), 0, 0, this);
            }
        }
    }

}
