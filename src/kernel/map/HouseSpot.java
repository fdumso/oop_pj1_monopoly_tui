package kernel.map;

import ui.SpotIcon;

/**
 * Created by freemso on 2016/4/25.
 */
public class HouseSpot extends AbstractSpot {
    private double originalPrice;
    private int level;
    private int ownerId;
    private int streetId;

    public HouseSpot(int id, String name, double originalPrice, int streetId) {
        super(id, name, Type.HOUSE);
        this.originalPrice = originalPrice;
        this.streetId = streetId;
        this.level = 1;
        this.ownerId = -1;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public int getLevel() {
        return level;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getStreetId() {
        return streetId;
    }

    public double calcPrice() {
        return originalPrice * level;
    }

    public double calcToll() {
        return calcPrice() * 0.3;
    }

}
