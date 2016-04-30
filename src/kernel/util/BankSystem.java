package kernel.util;

import kernel.Game;
import kernel.Player;

/**
 * Created by freemso on 2016/4/30.
 */
public class BankSystem {


    public void payInterest(Game game) {
        game.getUI().popMessage("月末，银行发利息");
        for (Player player: playerList) {
            double interest = player.getDeposit() * 0.1;
            player.addDeposit(interest);
            ui.popMessage("玩家 " + player.getName()
                    + "获得"
                    + interest
                    + "元储蓄利息！");
        }
    }
}
