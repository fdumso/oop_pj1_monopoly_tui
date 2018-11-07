package card;

import icon.Icon;
import player.Player;
import util.Game;

/**
 * Created by freemso on 2016/4/25.
 */
public abstract class AbstractCard {
    private String name;
    private int price;
    private Icon icon;

    protected AbstractCard(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }


    public abstract boolean effect(Game game, Player user);
}
