package spot;

import card.AbstractCard;
import player.Player;
import util.Game;
import util.Position;

/**
 * Created by freemso on 2016/4/25.
 */
public class NewsSpot extends AbstractSpot {
    public NewsSpot(int id, String name, Position position) {
        super(id, name, SpotType.NEWS, position);
    }

    @Override
    public void stepIn(Game game, Player player) {
        addPlayer(player);
    }

    @Override
    public void stepOut(Game game, Player player) {
        removePlayer(player);
    }

    @Override
    public void stay(Game game, Player player) {
        int newsId = (int) (Math.random() * 5);
        switch (newsId) {
            case 0: {
                int money = (int) (Math.random() * 11 + 10) * 100;
                Player landlord = game.getPlayerSystem().mostHousePlayer();
                landlord.addCash(money);
                game.getUI().showMessage("大新闻：公开表扬第一地主" + landlord.getName()
                        + "，奖励 " + money + " 元！");
            }
            case 1: {
                int money = (int) (Math.random() * 11 + 10) * 100;
                Player landlord = game.getPlayerSystem().leastHousePlayer();
                landlord.addCash(money);
                game.getUI().showMessage("大新闻：补助土地最少者" + landlord.getName()
                        + "，补助 " + money + " 元！");

            }
            case 2: {
                game.getUI().showMessage("大新闻：银行加发储金红利每个人得到存款10%！");
                for (Player p: game.getPlayerSystem().getPlayerList()) {
                    double money = p.getDeposit() * 0.1;
                    game.getUI().showMessage("玩家" + p.getName() + "获得 " + money + "元");
                    p.addDeposit(money);
                }
            }
            case 3: {
                game.getUI().showMessage("大新闻：所有人缴纳财产税10%！");
                for (Player p: game.getPlayerSystem().getPlayerList()) {
                    double money = p.getCapital() * 0.1;
                    game.getUI().showMessage("玩家" + p.getName() + "缴纳 " + money + "元");
                    p.subDeposit(money);
                }
            }
            case 4: {
                game.getUI().showMessage("大新闻：每个玩家得到一张卡片！");
                for (Player p: game.getPlayerSystem().getPlayerList()) {
                    AbstractCard card = game.getCardSystem().randomCard();
                    p.addCard(card);
                    game.getUI().showMessage("恭喜玩家" + p.getName() + "获得"
                            + card.getName() + "一张！");
                }
            }
        }
    }
}
