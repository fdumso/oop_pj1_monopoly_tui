package kernel;

import ui.*;

/**
 * Created by freemso on 2016/4/12.
 */
public abstract class Spot {
    private int id;
    private String name;
    private Icon icon;
    private int barricadeNum;

    public Spot(int id, String name, Icon icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public boolean hasBarricade() {
        return barricadeNum != 0;
    }

    public void addBarricade() {
        barricadeNum++;
    }
}
