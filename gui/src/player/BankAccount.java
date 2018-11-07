package player;

public class BankAccount {
    private Player owner;
    private double deposit;

    public BankAccount(Player owner) {
        this.owner = owner;
        this.deposit = 5000;
    }

    public Player getOwner() {
        return owner;
    }

    public double getDeposit() {
        return deposit;
    }

    public void addDeposit(double money) {
        deposit += money;
    }

    public boolean subDeposit(double money) {
        if (deposit >= money) {
            deposit -= money;
            return true;
        } else {
            return false;
        }
    }

    public void addInterest(double rate) {
        deposit += deposit*rate;
    }

    public void payTax(double rate) {
        deposit -= deposit*rate;
    }
}
