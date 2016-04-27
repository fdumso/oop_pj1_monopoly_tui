package kernel;

import kernel.map.AbstractSpot;
import kernel.map.Map;
import kernel.util.TimeSystem;
import ui.IGameUI;
import ui.TerminalUI;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/26.
 */
public class Game {
    private final double originalCash = 2000;
    private final double originalDeposit = 2000;
    private final int originalTicket = 10;

    private ArrayList<Player> playerList;
    private Map map;
    private TimeSystem timeSystem;
    private Dice dice;
    private int totalRoundsNum;
    private IGameUI gameUI;

    Game() {
        playerList = new ArrayList<>();
        map = new Map();
        timeSystem = new TimeSystem();
        gameUI = new TerminalUI(this);
        dice = new Dice();
    }

    void init() {
        gameUI.init();
        main();
    }

    /* Private Method*/
    private void main() {
        for (int round = 0; round < totalRoundsNum; round++) {
            gameUI.startRound();
            for (int playerId = 0; playerId < playerList.size(); playerId++) {
                gameUI.playerStart(playerId);
                gameUI.main(playerId);
                gameUI.playerEnd(playerId);
            }
            gameUI.endRound();
        }
        gameUI.gameOver();
    }


    /* Read Method*/

    public TimeSystem getTimeSystem() {
        return timeSystem;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public Map getMap() {
        return map;
    }

    public Dice getDice() {
        return dice;
    }
    /* Write Method*/

    public void setTotalRoundsNum(int totalRoundsNum) {
        this.totalRoundsNum = totalRoundsNum;
    }

    public void addPlayer(int playerId, String playerName) {
        Player player = new Player(playerId, playerName, 0, Player.Direction.CLOCKWISE,
                originalCash, originalDeposit, originalTicket);
        playerList.add(player);
    }

    public void move(int playerId, int steps) {
        Player player = playerList.get(playerId);
        for (int i = 0; i < steps; i++) {
            if (player.isTrapped()) {
                player.clearTrapped();
                break;
            } else {
                AbstractSpot outSpot = map.getSpot(player.getPosition());
                AbstractSpot inSpot = map.getSpot(player.getPosition() + player.getDirection().sign());
                outSpot.stepOut();
                player.setPosition(inSpot.getId());
                inSpot.stepIn();
            }
        }
        map.getSpot(player.getPosition()).stay();
    }

    public void concede(int playerId) {
        // TO DO

    }

    public int chooseASpot() {
        return gameUI.chooseASpot();
    }

    public class Dice {
        private int point;
        private boolean isControlled;

        public int roll() {
            if (!isControlled) {
                point = (int) (Math.random() * 6 + 1);
            }
            return point;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            isControlled = true;
            this.point = point;
        }
    }
}
