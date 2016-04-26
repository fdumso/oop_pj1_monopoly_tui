package kernel.map;

import ui.SpotIcon;

/**
 * Created by freemso on 2016/4/25.
 */
public class HouseSpot extends AbstractSpot {
    private double price;
    private int level;
    private int ownerId;
    private int streetId;

    public HouseSpot(int id, String name, double price, int streetId) {
        super(id, name, new SpotIcon(Type.HOUSE));
        this.price = price;
        this.streetId = streetId;
    }
}
