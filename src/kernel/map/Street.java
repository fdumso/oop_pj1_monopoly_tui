package kernel.map;

import kernel.Player;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/27.
 */

public class Street {
    private int id;
    private String name;
    private ArrayList<HouseSpot> houseList;

    Street(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    void addSpot(HouseSpot spot) {
        houseList.add(spot);
    }

    public double calcSurcharge(Player player) {
        double surcharge = 0;
        for (HouseSpot house: houseList) {
            if (house.getOwner() != player) {
                return 0;
            } else {
                surcharge += house.calcToll();
            }
        }
        return surcharge;
    }

    public void demolition() {
        for (HouseSpot house :
                houseList) {
            if (house.getOwner() != null) {
                Player owner = house.getOwner();
                owner.addCash(house.calcPrice() * 1.5);
                house.setOwner(null);
                owner.removeHouse(house);
            }
        }
    }
}
