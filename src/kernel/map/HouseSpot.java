package kernel.map;

import kernel.Game;
import kernel.Player;
import ui.icon.Icon;
import ui.icon.HouseIcon;

/**
 * Created by freemso on 2016/4/25.
 */
public class HouseSpot extends AbstractSpot {
    private double originalPrice;
    private int level;
    private Player owner;
    private Street street;

    public HouseSpot(int id, String name, double originalPrice, Street street) {
        super(id, name, Type.HOUSE);
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
        return calcPrice() * 0.3;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public void stepIn(Game game, Player player) {

    }

    @Override
    public void stepOut(Game game, Player player) {

    }

    @Override
    public void stay(Game game, Player player) {

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
