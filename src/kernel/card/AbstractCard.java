package kernel.card;

import kernel.Game;
import kernel.Player;
import ui.icon.Icon;
import kernel.util.Option;

/**
 * Created by freemso on 2016/4/25.
 */
public abstract class AbstractCard implements Option {
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

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public abstract boolean effect(Game game, Player user);
}
