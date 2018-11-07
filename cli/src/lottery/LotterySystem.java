package lottery;

import player.Player;
import util.Game;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/30.
 */
public class LotterySystem {
    private ArrayList<Lottery> lotteryList;

    public LotterySystem() {
        this.lotteryList = new ArrayList<>();
    }

    public boolean buyLottery(Player player, int number) {
        for (Lottery lottery: lotteryList) {
            if (lottery.getNumber() == number) {
                return false;
            }
        }
        Lottery lottery = new Lottery(number, player);
        lotteryList.add(lottery);
        return true;
    }

    public void open(Game game) {
        int number = (int) (Math.random() * 101);
        game.getUI().showMessage("彩票开奖！中奖号码为 " + number);
        lotteryList.stream().filter(lottery -> lottery.getNumber() == number).forEach(lottery -> {
            game.getUI().showMessage("有人中奖！玩家" + lottery.getOwner().getName() + "中奖啦！奖金 10000 元！");
            lottery.getOwner().addCash(10000);
        });
    }
}
