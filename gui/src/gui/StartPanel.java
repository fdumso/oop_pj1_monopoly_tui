package gui;

import action.InitGameAction;
import util.io.FontIO;
import util.io.ImageIO;

import javax.swing.*;

class StartPanel extends JPanel {
    StartPanel() {
        // set layout
        this.setLayout(null);
        JLabel backgroundLabel = new JLabel(ImageIO.getInstance().getBackground("start"));
        backgroundLabel.setLayout(null);
        // set font
        JButton startButton = new JButton(" START", ImageIO.getInstance().getButtonIcon("Start"));
        startButton.setFont(FontIO.getWD(50));
        JButton continueButton = new JButton(" CONTINUE",
                ImageIO.getInstance().getButtonIcon("Continue"));
        continueButton.setFont(FontIO.getWD(50));
        JButton helpButton = new JButton(" HELP", ImageIO.getInstance().getButtonIcon("Help"));
        helpButton.setFont(FontIO.getWD(50));
        // clear border
        startButton.setBorder(null);
        continueButton.setBorder(null);
        helpButton.setBorder(null);
        // set button background
        startButton.setContentAreaFilled(false);
        continueButton.setContentAreaFilled(false);
        helpButton.setContentAreaFilled(false);
        // add listener to buttons
        startButton.addActionListener(e -> new InitGameAction().trigger());
        continueButton.addActionListener(e -> {
            // TODO
        });
        helpButton.addActionListener(e -> {
            // TODO
        });
        // add button to label
        backgroundLabel.add(startButton);
        backgroundLabel.add(continueButton);
        backgroundLabel.add(helpButton);
        // set button location
        startButton.setBounds(650, 350, 285, 50);
        continueButton.setBounds(750, 420, 400, 50);
        helpButton.setBounds(850, 490, 280, 50);
        // add label to panel
        this.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 1300, 700);
    }

}
