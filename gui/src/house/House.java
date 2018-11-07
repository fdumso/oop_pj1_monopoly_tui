package house;

import map.Street;
import player.Player;

public class House {
    private Player owner;
    private String name;
    private int level;
    private double initValue;
    private Street street;

    public House(String name, double initValue, Street street) {
        this.owner = null;
        this.name = name;
        this.level = 0;
        this.initValue = initValue;
        this.street = street;

    }

    public double getValue() {
        return initValue + (level*initValue*0.5);
    }

    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Street getStreet() {
        return street;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public double upgradeCost() {
        return initValue*0.5;
    }

    public boolean upgrade() {
        boolean canPay = owner.getWallet().payCash(upgradeCost());
        if (canPay) {
            level++;
        }
        return canPay;
    }

    public double getToll() {
        return getValue()*0.3;
    }
}
