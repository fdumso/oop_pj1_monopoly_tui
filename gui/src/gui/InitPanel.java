package gui;

import action.AddPlayerAction;
import action.CancelInitAction;
import action.StartGameAction;
import player.Character;
import util.io.FontIO;
import util.io.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class InitPanel extends JPanel {
    private ArrayList<CharacterButton> characterButtonList = new ArrayList<>();

    InitPanel() {
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Please choose a character for a new player");
        label.setFont(FontIO.getWD(30));
        label.setForeground(Color.WHITE);

        JButton startButton = new JButton("Start Game");
        startButton.setContentAreaFilled(false);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(FontIO.getWD(40));
        startButton.addActionListener(e -> new StartGameAction().trigger());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setContentAreaFilled(false);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(FontIO.getWD(40));
        cancelButton.addActionListener(e -> {
            for (CharacterButton characterButton: characterButtonList) {
                characterButton.setEnabled(true);
            }
            new CancelInitAction().trigger();
        });

        JPanel opPanel = new JPanel();
        opPanel.add(label);
        opPanel.add(startButton);
        opPanel.add(cancelButton);
        opPanel.setOpaque(false);

        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridLayout(1, 9, 10, 10));
        charPanel.setOpaque(false);

        characterButtonList.add(new CharacterButton(Character.BROOK));
        characterButtonList.add(new CharacterButton(Character.ZORO));
        characterButtonList.add(new CharacterButton(Character.CHOPPER));
        characterButtonList.add(new CharacterButton(Character.ROBIN));
        characterButtonList.add(new CharacterButton(Character.LUFFY));
        characterButtonList.add(new CharacterButton(Character.NAMI));
        characterButtonList.add(new CharacterButton(Character.SOGEKING));
        characterButtonList.add(new CharacterButton(Character.SANJI));
        characterButtonList.add(new CharacterButton(Character.FRANKY));
        characterButtonList.forEach(charPanel::add);


        this.add(charPanel, BorderLayout.CENTER);
        this.add(opPanel, BorderLayout.NORTH);
        this.setBackground(Color.BLACK);
    }

    private class CharacterButton extends JButton {
        public CharacterButton(Character character) {
            super(ImageIO.getInstance().getCharPortrait(character));
            this.setBorder(null);
            this.setContentAreaFilled(false);

            this.addActionListener(e -> {
                String name1 = JOptionPane.showInputDialog("Please input player's name: ", character.toString());
                if (name1 ==null) {
                    JOptionPane.showMessageDialog(null, "Name can not be empty!", "Name null",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    new AddPlayerAction(character, name1).trigger();
                    CharacterButton.super.setEnabled(false);
                }
            });
        }
    }
}
