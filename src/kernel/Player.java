package kernel;

import kernel.card.AbstractCard;
import kernel.map.HouseSpot;
import ui.*;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/25.
 */
public class Player {
    private int id;
    private String name;
    private PlayerIcon icon;
    private double cash;
    private double deposit;
    private int ticket;
    private int position;
    private Direction direction;
    private boolean trapped;
    private ArrayList<HouseSpot> houseList;
    private ArrayList<AbstractCard> cardList;

    public Player(int id, String name, int position, Direction direction, double cash, double deposit, int ticket) {
        this.id = id;
        this.icon = new PlayerIcon(id);
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.cash = cash;
        this.deposit = deposit;
        this.ticket = ticket;
    }

    public enum Direction {
        CLOCKWISE, COUNTERCLOCKWISE;

        @Override
        public String toString() {
            switch (this) {
                case CLOCKWISE: return "顺时针";
                default: return "逆时针";
            }
        }

        public int sign() {
            switch (this) {
                case CLOCKWISE: return 1;
                default: return -1;
            }
        }

    }

    /* Read Method*/
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayerIcon getIcon() {
        return icon;
    }
    public double getCash() {
        return cash;
    }

    public double getDeposit() {
        return deposit;
    }

    public int getTicket() {
        return ticket;
    }

    public int getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getCapital() {
        return cash + deposit + getHouseValue();
    }

    public double getHouseValue() {
        double houseValue = 0;
        for (HouseSpot house: houseList) {
            houseValue += house.calcPrice();
        }
        return houseValue;
    }

    public ArrayList<HouseSpot> getHouseList() {
        return houseList;
    }

    public ArrayList<AbstractCard> getCardList() {
        return cardList;
    }

    boolean isTrapped() {
        return trapped;
    }

    /* Write Method*/

    public void setIcon(PlayerIcon icon) {
        this.icon = icon;
    }

    void setPosition(int position) {
        this.position = position;
    }

    public void reverseDirection() {
        if (direction == Direction.CLOCKWISE) {
            direction = Direction.COUNTERCLOCKWISE;
        } else {
            direction = Direction.CLOCKWISE;
        }
    }

    public void addCard(AbstractCard card) {
        cardList.add(card);
    }

    public void setTrapped() {
        this.trapped = true;
    }

    void clearTrapped() {
        this.trapped = false;
    }

    public void addDeposit(double amount) {
        deposit += amount;
    }

    public void subDeposit(double amount) {
        deposit -= amount;
    }

    public void addCash(double amount) {
        cash += amount;
    }

    public void subCash(double amount) {
        cash -= amount;
    }

    public void addTicket(int number) {
        ticket += number;
    }

    public void subTicket(int number) {
        ticket -= number;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}
