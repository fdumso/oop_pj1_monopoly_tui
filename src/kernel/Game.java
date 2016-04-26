package kernel;

import kernel.map.Map;
import kernel.util.TimeSystem;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/26.
 */
public class Game {
    private ArrayList<Player> playerList;
    private Map map;
    private TimeSystem timeSystem;
    private int totalRoundsNum;

    public Game() {
        playerList = new ArrayList<Player>();
        map = new Map();
        timeSystem = new TimeSystem();
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public Map getMap() {
        return map;
    }

    public TimeSystem getTimeSystem() {
        return timeSystem;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public int getTotalRoundsNum() {
        return totalRoundsNum;
    }

    public void setTotalRoundsNum(int totalRoundsNum) {
        this.totalRoundsNum = totalRoundsNum;
    }
}
