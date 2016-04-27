package kernel.card;

import kernel.Game;
import kernel.Player;

/**
 * Created by freemso on 2016/4/25.
 */
public class ControlDiceCard extends AbstractCard {
    public ControlDiceCard() {
        super("遥控骰子卡", 4);
    }

    @Override
    public boolean effect(Game game, Player user) {
        int point = game.getGameUI().getIntegerMessage("请输入您想掷到的点数<1 ~ 6>：", 1, 6);
        game.getDice().setPoint(point);
        game.getGameUI().showMessage("遥控骰子使用成功！下次掷得点数将为 " + point);
        return true;
    }
}
