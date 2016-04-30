package kernel.util;

import kernel.Game;
import kernel.Player;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/30.
 */
public class PlayerSystem {
    private ArrayList<Player> playerList;

    public PlayerSystem() {
        this.playerList = new ArrayList<>();
    }

    public void showPlayerInfo(Game game) {
        game.getUI().showMessage("玩家资产信息如下：\n玩家名  点券  现金  存款  房产数  房产总价值  资产总额");
        for (Player p : playerList) {
            System.out.printf("%-8s%-6d%-6.2f%-6.2f%-8d%-12.2f%-10.2f\n",
                    p.getName(), p.getTicket(),
                    p.getCash(), p.getDeposit(),
                    p.getHouseList().size(), p.getHouseValue(),
                    p.getCapital());
        }
    }
}
