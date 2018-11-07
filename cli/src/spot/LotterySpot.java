package spot;

import player.Player;
import util.Game;
import util.Position;

/**
 * Created by freemso on 2016/4/25.
 */
public class LotterySpot extends AbstractSpot {
    public LotterySpot(int id, String name, Position position) {
        super(id, name, SpotType.LOTTERY, position);
    }

    @Override
    public void stepIn(Game game, Player player) {
        addPlayer(player);
    }

    @Override
    public void stay(Game game, Player player) {
        if (game.getUI().confirm("你来到了" + getName() + "，是否花 100 元购买彩票？")) {
            do {
                int number = game.getUI().inputInt("请选择一个0到100的整数：", 0, 100);
                if (game.getLotterySystem().buyLottery(player, number)) {
                    game.getUI().showMessage("你买了" + number + "号，将在月底开奖");
                    break;
                } else {
                    game.getUI().showMessage("这个号码已经被买走，请重新选择一个");
                }
            } while (true);

        } else {
            game.getUI().showMessage("谢谢光临");
        }
    }
}
