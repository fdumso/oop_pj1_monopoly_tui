package kernel.spot;

import kernel.Game;
import kernel.Player;
import kernel.map.Position;
import kernel.util.SpotSystem;

/**
 * Created by freemso on 2016/4/25.
 */
public class BankSpot extends AbstractSpot {

    public BankSpot(int id, String name, Position position) {
        super(id, name, SpotSystem.Type.BANK, position);
    }

    public enum Operation {
        DOPOSIT, WITHDRAW, LEAVE
    }
    @Override
    public void stepIn(Game game, Player player) {
        addPlayer(player);
        Operation op = game.getUI().showBankPanel();
        double oriCash = player.getCash();
        double oriDeposit = player.getDeposit();
        switch (op) {
            case DOPOSIT: {
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
            case WITHDRAW: {
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
            case LEAVE: {
                game.getUI().showMessage("谢谢你的光临，下次再见！");
            }
        }
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
