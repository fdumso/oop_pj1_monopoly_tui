package gui;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {
    private static final MainPage MAIN_PAGE = new MainPage();
    private MainPanel mainPanel;

    public static MainPage getInstance() {
        return MAIN_PAGE;
    }

    private MainPage() {
        mainPanel = new MainPanel();
        this.add(mainPanel);

        this.setTitle("Monopoly");
        this.setSize(1300, 730);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void showPanel(String name) {
        mainPanel.showCard(name);
    }

    public void repaint() {
        mainPanel.repaint();
    }

    private class MainPanel extends JPanel {
        private CardLayout layout = new CardLayout();
        private JPanel startPanel = new StartPanel();
        private JPanel initPanel = new InitPanel();
        private JPanel playPanel = new PlayPanel();

        private MainPanel() {
            this.setLayout(layout);
            this.add("start", startPanel);
            this.add("init", initPanel);
            this.add("play", playPanel);
        }

        public void showCard(String cardName) {
            layout.show(this, cardName);
        }
    }
}
