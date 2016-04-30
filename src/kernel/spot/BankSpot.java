package kernel.spot;

import kernel.*;

/**
 * Created by freemso on 2016/4/25.
 */
public class BankSpot extends AbstractSpot {

    public BankSpot(int id, String name, Position position) {
        super(id, name, SpotType.BANK, position);
    }

    @Override
    public void stepIn(Game game, Player player) {
        addPlayer(player);
        game.getUI().showMessage("==========欢迎光临银行==========");
        loop: do {
            double oriCash = player.getCash();
            double oriDeposit = player.getDeposit();
            int operationId = game.getUI().inputInt("存款业务请输入 0，取款业务请输入 1，离开请输入 -1：", -1, 1);
            switch (operationId) {
                case 0: {
                    int amount = game.getUI().inputInt("输入你想要存入的现金数目<0-"
                            + player.getCash() + ">：", 0, (int) player.getCash());

                    player.subCash(amount);
                    player.addDeposit(amount);
                    game.getUI().showMessage("你的现金从" + oriCash
                            + "元减少到" + player.getCash()
                            + "元\n" + "您的存款从"
                            + oriDeposit + "元增加到"
                            + player.getDeposit()
                            + "元\n" + "存款业务已完成！");
                    stepIn(game, player);
                }
                case 1: {
                    int amount = game.getUI().inputInt("输入你想要取出的现金数目<0-"
                            + player.getDeposit() + ">：", 0, (int) player.getDeposit());
                    player.subDeposit(amount);
                    player.subCash(amount);
                    game.getUI().showMessage("你的现金从" + oriCash
                            + "元增加到" + player.getCash()
                            + "元\n" + "您的存款从"
                            + oriDeposit + "元减少到"
                            + player.getDeposit()
                            + "元\n" + "您的取款业务已经完成！");
                    stepIn(game, player);
                }
                default: {
                    game.getUI().showMessage("谢谢你的光临，下次再见！");
                    break loop;
                }
            }
        } while (true);
    }

    @Override
    public void stepOut(Game game, Player player) {
        removePlayer(player);
    }

    @Override
    public void stay(Game game, Player player) {
        // do nothing
    }


}
