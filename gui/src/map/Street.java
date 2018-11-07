package map;

import map.spot.HouseSpot;
import player.Player;

import java.util.ArrayList;

public class Street {
    private int id;
    private String name;
    private ArrayList<HouseSpot> houseSpotList = new ArrayList<>();

    public Street(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addHouse(HouseSpot houseSpot) {
        houseSpotList.add(houseSpot);
    }

    public void demolition() {
        houseSpotList.stream().filter(houseSpot -> houseSpot.getHouse().getOwner() != null).forEach(houseSpot -> {
            Player owner = houseSpot.getHouse().getOwner();
            owner.getWallet().addCash(houseSpot.getHouse().getValue() * 1.5);
            houseSpot.getHouse().setOwner(null);
            owner.getEstate().removeHouse(houseSpot.getHouse());
        });
    }

    public String getName() {
        return name;
    }
}
