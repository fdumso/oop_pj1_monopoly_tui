package kernel.card;

import kernel.Game;
import kernel.Player;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/25.
 */
public class TurnAroundCard extends AbstractCard {
    public TurnAroundCard() {
        super("转向卡", 4);
    }

    @Override
    public boolean effect(Game game, Player user) {
        ArrayList<Player> playerList = game.getPlayerSystem().getPlayerList();
        do {
            int targetId = game.getUI().inputInt("输入你想施用对象的ID（输入-1退出）：", -1, playerList.size()-1);
            if (targetId == -1) {
                return false;
            }
            Player target = playerList.get(targetId);
            if (game.getMapSystem().calcDistance(user.getPosition().getId(), target.getPosition().getId()) <= 5) {
                target.reverseDirection();
                game.getUI().showMessage("使用成功，" + target.getName() + "的方向变为" + target.getDirection().toString());
                return true;
            } else {
                game.getUI().showMessage("使用失败！对象不在范围（五步）之内！");
            }
        } while (true);
    }
}
