package util;

import player.Player;
import spot.HouseSpot;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/30.
 */
public class Street {
    private int id;
    private String name;
    private ArrayList<HouseSpot> houseList;

    Street(int id, String name) {
        this.id = id;
        this.name = name;
        this.houseList = new ArrayList<>();
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

    public double calcSurcharge(HouseSpot spot) {
        double surcharge = 0;
        for (HouseSpot house: houseList) {
            if (house.getOwner() != spot.getOwner()) {
                return 0;
            } else {
                surcharge += house.calcToll();
            }
        }
        return surcharge;
    }

    public void demolition() {
        houseList.stream().filter(house -> house.getOwner() != null).forEach(house -> {
            Player owner = house.getOwner();
            owner.addCash(house.calcPrice() * 1.5);
            house.setOwner(null);
            owner.removeHouse(house);
        });
    }
}
