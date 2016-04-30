package kernel;

import javafx.scene.chart.ScatterChart;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by freemso on 2016/4/30.
 */
public class PlayerSystem {
    private final double originalCash = 2000;
    private final double originalDeposit = 2000;
    private final int originalTicket = 10;

    private ArrayList<Player> playerList;

    public PlayerSystem() {
        this.playerList = new ArrayList<>();
    }

    public void showPlayerInfo(Game game) {
        game.getUI().showMessage("玩家资产信息如下：\n");
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n", "玩家名", "点券", "现金", "存款", "房产数", "房产总价值", "资产总额");
        for (Player p : playerList) {
            System.out.printf("%-10s%-10d%-10.2f%-10.2f%-10d%-10.2f%-10.2f\n",
                    p.getName(), p.getTicket(),
                    p.getCash(), p.getDeposit(),
                    p.getHouseList().size(), p.getHouseValue(),
                    p.getCapital());
        }
    }

    public void initPlayer(Game game) {
        int playerNumber = game.getUI().inputInt("请输入玩家个数（ 2 - 4 ）：", 2, 4);
        new Scanner(System.in).nextLine();
        for (int i = 0; i < playerNumber; i++) {
            String name = game.getUI().inputStr("请输入玩家 " + (i+1) + " 的昵称：", 2, 10);
            Player player = new Player(i, name, originalCash, originalDeposit, originalTicket);
            playerList.add(player);
            game.getMapSystem().addPlayer(player);
        }
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public Player mostHousePlayer() {
        Player temp = playerList.get(0);
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getHouseList().size() > temp.getHouseList().size()) {
                temp = playerList.get(i);
            }
        }
        return temp;
    }

    public Player leastHousePlayer() {
        Player temp = playerList.get(0);
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getHouseList().size() < temp.getHouseList().size()) {
                temp = playerList.get(i);
            }
        }
        return temp;
    }
}
