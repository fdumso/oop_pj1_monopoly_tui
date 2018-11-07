package player;

public class Wallet {
    private Player owner;
    private double cash;
    private int ticket;

    public Wallet(Player owner) {
        this.owner = owner;
        this.cash = 5000;
        this.ticket = 20;
    }

    public Player getOwner() {
        return owner;
    }

    public double getCash() {
        return cash;
    }

    public int getTicket() {
        return ticket;
    }

    public boolean payCash(double money) {
        if (cash >= money) {
            cash -= money;
            return true;
        } else {
            return false;
        }
    }

    public boolean payTicket(int num) {
        if (ticket >= num) {
            ticket -= num;
            return true;
        } else {
            return false;
        }
    }

    public void addCash(double money) {
        cash += money;
    }

    public void addTicket(int ticketNum) {
        ticket += ticketNum;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}
