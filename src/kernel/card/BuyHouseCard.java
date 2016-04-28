package kernel.card;

import kernel.Game;
import kernel.Player;
import kernel.map.AbstractSpot;
import kernel.map.HouseSpot;

/**
 * Created by freemso on 2016/4/25.
 */
public class BuyHouseCard extends AbstractCard {
    public BuyHouseCard() {
        super("购房卡", 10);
    }

    @Override
    public boolean effect(Game game, Player user) {
        AbstractSpot spot = game.getMap().getSpot(user.getPosition());
        // check whether the spot that the user is standing on is a house
        if (spot.getType() != AbstractSpot.Type.HOUSE) {
            // it is not a building
            game.getGameUI().showMessage("这不是一个房屋，你不能使用购房卡！");
            return false;
        }
        // check the owner of the house
        if (((HouseSpot) spot).getOwner() == user) {
            // this is the user's house
            game.getGameUI().showMessage("这是你自己的房屋，无法再次购买！");
            return false;
        }
        double price = ((HouseSpot) spot).calcPrice();
        if (game.getGameUI().confirmMessage("这个房屋的价格为 " + price + " 元，是否使用购房卡购买？<y/n>：")) {
            if (user.getCash() >= price) {
                user.subCash(price);
                user.addHouse((HouseSpot) spot);
                ((HouseSpot) spot).setOwner(user);
                if (((HouseSpot) spot).getOwner() != null) {
                    Player owner = ((HouseSpot) spot).getOwner();
                    owner.addCash(price);
                    owner.removeHouse((HouseSpot) spot);
                }
                game.getGameUI().showMessage("你已成功使用购房卡购买" + spot.getName() + "！");
                return true;
            }
        }
        return false;
    }
}
