package player;

import stock.StockSystem;

public class StockAccount {
    private Player owner;
    private int[] stockNum = new int[10];

    StockAccount(Player owner) {
        this.owner = owner;
    }

    public boolean buy(int stockID, int num) {
        double cost = StockSystem.getInstance().get(stockID).getPrice()*num;
        if (owner.getBankAccount().subDeposit(cost)) {
            stockNum[stockID] += num;
            return true;
        } else {
            return false;
        }
    }

    public boolean sell(int stockID, int num) {
        if (stockNum[stockID] >= num) {
            double money = StockSystem.getInstance().get(stockID).getPrice()*num;
            owner.getBankAccount().addDeposit(money);
            stockNum[stockID] -= num;
            return true;
        } else {
            return false;
        }
    }

    public int[] getStockNum() {
        return stockNum;
    }
}
