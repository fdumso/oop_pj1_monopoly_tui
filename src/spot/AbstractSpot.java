package spot;

import player.Player;
import util.Game;
import util.Position;
import icon.Icon;
import icon.SpotIcon;

import java.util.ArrayList;


/**
 * Created by freemso on 2016/4/12.
 */
public abstract class AbstractSpot {
    private int id;
    private String name;
    protected SpotIcon icon;
    private int barricadeNum;
    ArrayList<Player> containedPlayerList;
    private SpotType type;
    private Position position;

    protected AbstractSpot(int id, String name, SpotType type, Position position) {
        this.position = position;
        this.id = id;
        this.name = name;
        this.type = type;
        this.icon = new SpotIcon(type);
        this.containedPlayerList = new ArrayList<>();
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

    public SpotType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
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

    public void addBarricade() {
        barricadeNum++;
    }

    public void addPlayer(Player player) {
        containedPlayerList.add(player);
    }

    public void removePlayer(Player player) {
        containedPlayerList.remove(player);
    }
}
