package kernel;

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
    private int totalRoundsNum;
    private IGameUI gameUI;

    Game() {
        playerList = new ArrayList<>();
        map = new Map();
        timeSystem = new TimeSystem();
        gameUI = new TerminalUI(this);
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

    /* Write Method*/

    public void setTotalRoundsNum(int totalRoundsNum) {
        this.totalRoundsNum = totalRoundsNum;
    }

    public void addPlayer(int playerId, String playerName) {
        Player player = new Player(playerId, playerName, 0, Player.Direction.CLOCKWISE,
                originalCash, originalDeposit, originalTicket);
        playerList.add(player);
    }

    class Dice {
        private int point;
        private boolean isControlled;

        int roll() {
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
