package spot;

import icon.HouseIcon;
import icon.Icon;
import player.Player;
import util.Game;
import util.Position;
import util.Street;

/**
 * Created by freemso on 2016/4/25.
 */
public class HouseSpot extends AbstractSpot {
    private double originalPrice;
    private int level;
    private Player owner;
    private Street street;

    public HouseSpot(int id, String name, double originalPrice, Street street, Position position) {
        super(id, name, SpotType.HOUSE, position);
        this.originalPrice = originalPrice;
        this.street = street;
        this.level = 1;
        this.owner = null;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public int getLevel() {
        return level;
    }

    public Player getOwner() {
        return owner;
    }

    public Street getStreet() {
        return street;
    }

    public double calcPrice() {
        return originalPrice * level;
    }

    public double calcToll() {
        return calcPrice() * 0.3 + street.calcSurcharge(this);
    }

    public double calcUpgradePrice() {
        return calcPrice() * 0.5;
    }




    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void upgrade() {
        this.level++;
    }

    @Override
    public void stepIn(Game game, Player player) {
        addPlayer(player);
    }

    @Override
    public void stay(Game game, Player player) {
        game.getUI().showMessage("你停留在了" + getName());
        if (getOwner() == null) {
            double price = calcPrice();
            boolean buy = game.getUI().confirm("这是一个可出售的房屋，价格为 "
                    + price + " 元，是否选择购买？");
            if (buy) {
                if (player.getCash() >= price) {
                    player.subCash(price);
                    player.addHouse(this);
                    setOwner(player);
                    game.getUI().showMessage("你购买了" + getName());
                } else {
                    game.getUI().showMessage("你的现金不够！");
                }
            } else {
                game.getUI().showMessage("你放弃了购买" + getName());
            }
        } else if (getOwner() == player) {
            double upgradePrice = calcUpgradePrice();
            boolean upgrade = game.getUI().confirm("这是你的房屋，目前为 " + getLevel()
                    + " 级，升级需要花费 " + upgradePrice + " 元，是否升级？");
            if (upgrade) {
                if (player.getCash() >= upgradePrice) {
                    player.subCash(upgradePrice);
                    upgrade();
                    game.getUI().showMessage("你已成功将" + getName()
                            + "升至" + getLevel() + "级！");
                } else {
                    game.getUI().showMessage("你的现金不够！");
                }
            } else {
                game.getUI().showMessage("你放弃了升级" + getName());
            }
        } else {
            double toll = calcToll();
            Player owner = getOwner();
            game.getUI().showMessage("这是" + owner.getName() + "的房屋，你需要缴纳过路费 "
                    + toll + " 元！");
            if (player.getCash() >= toll) {
                player.subCash(toll);
                owner.addCash(toll);
                game.getUI().showMessage("你用现金支付了 " + toll + " 元过路费给" + owner.getName());
            } else {
                toll = toll - player.getCash();
                player.setCash(0);
                if (player.getDeposit() >= toll) {
                    player.subDeposit(toll);
                    owner.addCash(toll);
                    game.getUI().showMessage("现金不够！已用银行存款支付超出部分的 " + toll + " 元！");
                } else {
                    toll = toll - player.getDeposit();
                    player.setDeposit(0);
                    while (!player.getHouseList().isEmpty()) {
                        HouseSpot houseToSell = player.getHouseList().get(0);
                        double gain = houseToSell.calcPrice();
                        player.removeHouse(houseToSell);
                        if (toll <= gain) {
                            player.addCash(gain - toll);
                            toll = 0;
                            break;
                        } else {
                            toll -= gain;
                        }
                    }
                    if (toll == 0) {
                        game.getUI().showMessage("你的现金和存款已不足支付过路费！通过变卖房产，你勉强活了下来");
                    } else {
                        game.getPlayerSystem().bankrupt(game, player);
                    }
                }
            }
        }

    }

    @Override
    public Icon printIcon() {
        if (containedPlayerList.isEmpty()) {
            if (owner != null) {
                return new HouseIcon(owner.getId());
            } else {
                return icon;
            }
        } else {
            return containedPlayerList.get(containedPlayerList.size() - 1).getIcon();
        }
    }
}
