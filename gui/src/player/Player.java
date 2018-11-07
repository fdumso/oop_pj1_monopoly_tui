package player;

import house.House;
import map.spot.Spot;
import map.MapSystem;

public class Player {
    private String name;
    private Wallet wallet;
    private BankAccount bankAccount;
    private Spot position;
    private Status status;
    private CardPacket cardPacket;
    private Estate estate;
    private Direction direction;
    private Character character;
    private int hospitalRound;
    private boolean isResident;
    private StockAccount stockAccount;

    public Player(String name, Character character) {
        this.name = name;
        this.wallet = new Wallet(this);
        this.bankAccount = new BankAccount(this);
        this.position = MapSystem.getInstance().getRandomSpot();
        this.position.addPlayer(this);
        this.status = Status.NORMAL;
        this.cardPacket = new CardPacket();
        this.estate = new Estate();
        this.direction = Direction.getRandomDirection();
        this.character = character;
        this.hospitalRound = 0;
        this.isResident = false;
        this.stockAccount = new StockAccount(this);
    }

    public boolean step() {
        boolean canStep = position.stepOut(this);
        if (canStep) {
            if (direction == Direction.CLOCKWISE) {
                position = position.getNext();
            } else {
                position = position.getPrev();
            }
            position.stepIn(this);
        }
        return canStep;
    }

    public void stay() {
        position.stay(this);
    }

    public boolean buy(House house) {
        boolean cashEnough = wallet.payCash(house.getValue());
        if (cashEnough) {
            estate.add(house);
            house.setOwner(this);
        }
        return cashEnough;
    }

    public void bankrupt() {
        position.stepOut(this);
        position = null;
        estate.clearAll();
        status = Status.BANKRUPTED;
        PlayerSystem.getInstance().remove(this);
    }

    public void setResident() {
        isResident = true;
    }

    public boolean isResident() {
        return isResident;
    }

    public void turnAround() {
        if (direction == Direction.CLOCKWISE) {
            direction = Direction.COUNTER_CLOCKWISE;
        } else {
            direction = Direction.CLOCKWISE;
        }
    }

    public void clearResidence() {
        isResident = false;
    }

    public enum Status {
        NORMAL, BANKRUPTED
    }

    private enum Direction {
        CLOCKWISE, COUNTER_CLOCKWISE;

        @Override
        public String toString() {
            switch (this) {
                case CLOCKWISE: return "顺时针";
                default: return "逆时针";
            }
        }

        public static Direction getRandomDirection() {
            double random = Math.random();
            if (random > 0.5) {
                return CLOCKWISE;
            } else {
                return COUNTER_CLOCKWISE;
            }
        }
    }

    public String getName() {
        return name;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public Spot getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    public CardPacket getCardPacket() {
        return cardPacket;
    }

    public Estate getEstate() {
        return estate;
    }

    public Direction getDirection() {
        return direction;
    }

    public Character getCharacter() {
        return character;
    }

    public double getCapital() {
        return wallet.getCash()+bankAccount.getDeposit()+estate.getTotalValue();
    }

    public boolean inHospital() {
        return hospitalRound!=0;
    }

    public void subHospital() {
        hospitalRound--;
    }

    public StockAccount getStockAccount() {
        return stockAccount;
    }

    public void goToHospital() {
        hospitalRound = 2;
        position.stepOut(this);
        position = MapSystem.getInstance().getHospitalSpot();
        position.stepIn(this);
        direction = Direction.COUNTER_CLOCKWISE;
    }
}
