package gui;

import javax.swing.*;

class PlayPanel extends JPanel {
    PlayPanel() {
        this.setLayout(null);
        MapPanel mapPanel = new MapPanel();
        this.add(mapPanel);
        mapPanel.setBounds(20, 20, 1000, 610);
        InfoPanel infoPanel = new InfoPanel();
        this.add(infoPanel);
        infoPanel.setBounds(1000, 0, 300, 630);
    }
}
