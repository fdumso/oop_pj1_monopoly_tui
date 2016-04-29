package kernel.map;

import kernel.Game;
import kernel.Player;
import kernel.util.Option;
import ui.icon.Icon;
import ui.icon.SpotIcon;

import java.util.ArrayList;


/**
 * Created by freemso on 2016/4/12.
 */
public abstract class AbstractSpot implements Option {
    private int id;
    private String name;
    protected SpotIcon icon;
    private int barricadeNum;
    ArrayList<Player> containedPlayerList;
    private Type type;

    protected AbstractSpot(int id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.icon = new SpotIcon(type);
    }

    public enum Type {
        BANK, EMPTY, HOUSE, LOTTERY, NEWS, CARD, TICKET, STORE
    }

    public abstract void stepIn(Game game, Player player);

    public abstract void stepOut(Game game, Player player);

    public abstract void stay(Game game, Player player);

    /* Read Method */

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SpotIcon getIcon() {
        return icon;
    }

    public Type getType() {
        return type;
    }

    public boolean hasBarricade() {
        return barricadeNum != 0;
    }

    public Icon printIcon() {
        if (containedPlayerList.isEmpty()) {
            return icon;
        } else {
            return containedPlayerList.get(containedPlayerList.size() - 1).getIcon();
        }
    }

    /* Write Method */
    public void setIcon(SpotIcon icon) {
        this.icon = icon;
    }

    public void addBarricade() {
        barricadeNum++;
    }
}
