package kernel.map;

import kernel.Player;
import ui.*;

import java.util.ArrayList;


/**
 * Created by freemso on 2016/4/12.
 */
public abstract class AbstractSpot {
    private int id;
    private String name;
    private SpotIcon icon;
    private int barricadeNum;
    private ArrayList<Player> containedPlayerList;
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

    public AbstractIcon printIcon() {
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
