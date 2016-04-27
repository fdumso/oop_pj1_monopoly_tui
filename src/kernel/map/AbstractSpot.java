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

    protected AbstractSpot(int id, String name, SpotIcon icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public enum Type {
        BANK, GROUND, HOUSE, LOTTERY, NEWS, CARD, TICKET, STORE
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SpotIcon getIcon() {
        return icon;
    }

    public void setIcon(SpotIcon icon) {
        this.icon = icon;
    }

    public boolean hasBarricade() {
        return barricadeNum != 0;
    }

    public void addBarricade() {
        barricadeNum++;
    }

    public AbstractIcon printIcon() {
        if (containedPlayerList.isEmpty()) {
            return icon;
        } else {
            return containedPlayerList.get(containedPlayerList.size() - 1).getIcon();
        }
    }
}