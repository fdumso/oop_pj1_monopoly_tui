package map;

import map.spot.BankSpot;
import map.spot.HospitalSpot;
import map.spot.Spot;

import java.util.ArrayList;

public class MapSystem {
    private static final MapSystem mapSystem = new MapSystem();
    private MapSystem() {
    }
    public static MapSystem getInstance() {
        return mapSystem;
    }

    private BankSpot bankSpot;
    private HospitalSpot hospitalSpot;
    private ArrayList<Street> streetList = new ArrayList<>();

    public void addStreet(Street street) {
        streetList.add(street);
    }

    public Street getStreetWithID(int streetId) {
        return streetList.get(streetId);
    }

    public void setBankSpot(BankSpot bankSpot) {
        this.bankSpot = bankSpot;
    }

    public void setHospitalSpot(HospitalSpot hospitalSpot) {
        this.hospitalSpot = hospitalSpot;
    }

    public BankSpot getBankSpot() {
        return bankSpot;
    }

    public HospitalSpot getHospitalSpot() {
        return hospitalSpot;
    }

    public Spot getRandomSpot() {
        Spot spot = bankSpot;
        int random = (int) (Math.random() * 50);
        for (int i = 0; i < random; i++) {
            spot = spot.getNext();
        }
        return spot;
    }
}
