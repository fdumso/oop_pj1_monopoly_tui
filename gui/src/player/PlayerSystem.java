package player;

import util.TimeSystem;

import javax.swing.*;
import java.util.ArrayList;

public class PlayerSystem {
    private static final PlayerSystem playerSystem = new PlayerSystem();
    private Player currentPlayer;

    private PlayerSystem() {}
    public static PlayerSystem getInstance() {return playerSystem;}

    private ArrayList<Player> playerList = new ArrayList<>();

    public int getPlayerNumber() {
        return playerList.size();
    }

    public void addPlayer(Player player) {
        if (playerList.size() == 0) {
            currentPlayer = player;
        }
        playerList.add(player);
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void clearPlayerList() {
        playerList.removeAll(playerList);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextPlayer() {
        int currentIndex = playerList.indexOf(currentPlayer);
        if (currentIndex == playerList.size()-1) {
            TimeSystem.getInstance().endDay();
            currentPlayer = playerList.get(0);
            TimeSystem.getInstance().startDay();
        } else {
            currentPlayer = playerList.get(currentIndex+1);
        }
        if (currentPlayer.inHospital()) {
            currentPlayer.subHospital();
            nextPlayer();
        }
    }

    public Player mostHousePlayer() {
        Player temp = playerList.get(0);
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getEstate().getHouseNum() > temp.getEstate().getHouseNum()) {
                temp = playerList.get(i);
            }
        }
        return temp;
    }

    public Player leastHousePlayer() {
        Player temp = playerList.get(0);
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getEstate().getHouseNum() < temp.getEstate().getHouseNum()) {
                temp = playerList.get(i);
            }
        }
        return temp;
    }

    public Player maxCapitalPlayer() {
        Player temp = playerList.get(0);
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getCapital() > temp.getCapital()) {
                temp = playerList.get(i);
            }
        }
        return temp;
    }

    public void remove(Player player) {
        playerList.remove(player);
        if (playerList.size() == 1) {
            // Game Over
            JOptionPane.showMessageDialog(null, "游戏结束，玩家"+playerList.get(0).getName()+"获得了胜利！");
            System.exit(0);
        }
    }
}
