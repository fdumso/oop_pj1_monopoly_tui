package card;

import player.Player;
import util.Game;

/**
 * Created by freemso on 2016/4/25.
 */
public class ControlDiceCard extends AbstractCard {
    public ControlDiceCard() {
        super("遥控骰子卡", 4);
    }

    @Override
    public boolean effect(Game game, Player user) {
        int point = game.getUI().inputInt("请输入您想掷到的点数<1 ~ 6>：", 1, 6);
        game.getDice().setPoint(point);
        game.getUI().showMessage("遥控骰子使用成功！下次掷得点数将为 " + point);
        return true;
    }
}
