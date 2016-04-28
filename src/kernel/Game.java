package kernel;

import kernel.map.AbstractSpot;
import kernel.map.Map;
import kernel.util.TimeSystem;
import ui.Option;
import ui.UI;
import ui.tui.TUI;

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
    private UI ui;

    Game() {
        playerList = new ArrayList<>();
        map = new Map();
        timeSystem = new TimeSystem();
        dice = new Dice();
        ui = new TUI();
    }

    void init() {
        Option one = ;
        System.out.print("请选择玩家个数：（ 2 - 4 ）");
        int playerNumber = inputReader.readInt(2, 4);
        for (int i = 0; i < playerNumber; i++) {
            System.out.print("请输入玩家 " + i + " 的昵称：");
            String playerName = inputReader.readString(0, 10);
            game.addPlayer(i, playerName);
        }
        // init total rounds number
        System.out.print("请输入总回合数：");
        int totalRoundsNum = inputReader.readInt(0, 365);
        game.setTotalRoundsNum(totalRoundsNum);
        // announce the game
        System.out.println("==========游戏开始==========");
        inputReader.enter();
        main();
    }

    /* Private Method*/
    private void main() {
        for (int round = 0; round < totalRoundsNum; round++) {
            gameUI.startRound();
            for (Player player: playerList) {
                gameUI.playerStart(player);
                gameUI.main(player);
                gameUI.playerEnd(player);
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

    public UI getGameUI() {
        return gameUI;
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

    public void move(Player player, int steps) {
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

    public void concede(Player player) {
        // TO DO

    }

    public AbstractSpot chooseASpotToSetBarricade(Player player) {
        return gameUI.chooseASpotToSetBarricade(player);
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
