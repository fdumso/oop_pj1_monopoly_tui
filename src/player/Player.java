package player;

import card.AbstractCard;
import icon.PlayerIcon;
import spot.AbstractSpot;
import spot.HouseSpot;
import util.Game;

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
    private AbstractSpot position;
    private Direction direction;
    private boolean bankrupt;
    private boolean resident;
    private ArrayList<HouseSpot> houseList;
    private ArrayList<AbstractCard> cardList;
    private int[] stockList;

    public Player(int id, String name, double cash, double deposit, int ticket) {
        this.id = id;
        this.icon = new PlayerIcon(id);
        this.name = name;
        this.cash = cash;
        this.deposit = deposit;
        this.ticket = ticket;
        this.houseList = new ArrayList<>();
        this.cardList = new ArrayList<>();
        this.bankrupt = false;
        this.stockList = new int[10];
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
        return ((int) cash * 100) / 100;
    }

    public double getDeposit() {
        return ((int) deposit * 100) / 100;
    }

    public int getTicket() {
        return ticket;
    }

    public AbstractSpot getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getCapital() {
        return ((int) (cash + deposit + getHouseValue()) * 100) / 100;
    }

    public double getHouseValue() {
        double houseValue = 0;
        for (HouseSpot house: houseList) {
            houseValue += house.calcPrice();
        }
        return ((int) houseValue * 100) / 100;
    }

    public ArrayList<HouseSpot> getHouseList() {
        return houseList;
    }

    public ArrayList<AbstractCard> getCardList() {
        return cardList;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public int[] getStockList() {
        return stockList;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    /* Write Method*/

    public void setPosition(AbstractSpot position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public void addHouse(HouseSpot spot) {
        this.houseList.add(spot);
        spot.setOwner(this);
    }

    public void removeHouse(HouseSpot spot) {
        houseList.remove(spot);
        spot.setOwner(null);
    }

    public void bankrupt() {
        bankrupt = true;
        for (HouseSpot house: houseList) {
            house.setOwner(null);
        }

    }

    public void useCard(Game game) {
        if (cardList.isEmpty()) {
            game.getUI().showMessage("你没有任何卡片！");
            return;
        }
        ArrayList<AbstractCard> optionList = new ArrayList<>();
        optionList.addAll(cardList);
        int optionIndex = game.getUI().select("请选择你想使用的卡片", optionList);
        if (optionIndex == -1) {
            game.getUI().showMessage("放弃使用");
        } else {
            boolean cardUsed = cardList.get(optionIndex).effect(game, this);
            if (cardUsed) {
                game.getUI().showMessage("使用成功!");
                cardList.remove(optionIndex);
            }
        }
    }

    public AbstractSpot selectSpot(Game game) {
        int index = game.getUI().inputInt("输入你选择的位置与你相差的距离<顺时针方向为正，逆时针方向为负>：");
        return game.getMapSystem().getSpot(getPosition().getId() + index);
    }



    public void move(Game game, int steps) {
        if (isResident()) {
            game.getUI().showMessage("你滞留原地一回合");
            setResident(false);
        } else {
            for (int i = 0; i < steps; i++) {
                AbstractSpot outSpot = position;
                AbstractSpot inSpot = game.getMapSystem().getSpot(position.getId() + direction.sign());
                if (outSpot.stepOut(game, this)) {
                    position = inSpot;
                    inSpot.stepIn(game, this);
                } else {
                    break;
                }
            }
        }
        position.stay(game, this);
    }

    public void concede(Game game) {
        if (game.getUI().confirm("你确定要认输吗？")) {
            game.getUI().showMessage("玩家" + name + "认输！");
            game.getPlayerSystem().bankrupt(game, this);
        }
    }
}
