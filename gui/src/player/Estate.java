package player;

import house.House;

import java.util.ArrayList;

public class Estate {
    private ArrayList<House> houseList;

    Estate() {
        this.houseList = new ArrayList<>();
    }

    public int getHouseNum() {
        return houseList.size();
    }

    double getTotalValue() {
        double result = 0;
        for (House house: houseList) {
            result += house.getValue();
        }
        return result;
    }

    public void add(House house) {
        houseList.add(house);
    }

    void clearAll() {
        for (House house: houseList) {
            house.setOwner(null);
        }
        houseList.removeAll(houseList);
    }

    public void removeHouse(House house) {
        house.setOwner(null);
        houseList.remove(house);
    }
}
