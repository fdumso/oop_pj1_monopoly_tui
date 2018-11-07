package action;

import gui.MainPage;
import player.PlayerSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerMoveAction implements IAction {
    private int steps;
    private Timer timer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (steps > 0) {
                steps--;
                timer.stop();
                if (!PlayerSystem.getInstance().getCurrentPlayer().step()) {
                    steps = 0;
                }
                timer.start();
                MainPage.getInstance().repaint();
            } else {
                timer.stop();
                PlayerSystem.getInstance().getCurrentPlayer().stay();
                PlayerSystem.getInstance().nextPlayer();
                MainPage.getInstance().repaint();
            }
        }
    });

    public PlayerMoveAction(int steps) {
        this.steps = steps;
    }

    @Override
    public void trigger() {
        timer.start();
    }
}
