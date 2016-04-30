package kernel;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by freemso on 2016/4/30.
 */
public class StockSystem {
    private ArrayList<Stock> stockList;

    public StockSystem() {
        this.stockList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Stock stock = new Stock(i, "第 " + i + "股");
            stock.setPrice(10 + Math.random()*5);
            stock.setRate(0.9 + Math.random()*0.2);
            stockList.add(stock);
        }
    }

    public void mainPanel(Game game, Player player) {
        game.getUI().showMessage("欢迎来到股市，有以下股票：\n");

        do {
            for (Stock stock: stockList) {
                System.out.println(stock.getId() + "\t" + stock.getName() + "\t单价：" + String.format("%.2f", stock.getPrice()) + "\t涨跌幅：" + (int) ((1-stock.getRate())*100) + "%" + "\t持有数：" + player.getStockList()[stock.getId()]);
            }
            String[] instruction = game.getUI().inputStr("请输入你要操作的指令：（输入q退出）", 0, 100).split(" ");
            if (instruction[0].equals("b")) {
                try {
                    int index = Integer.parseInt(instruction[1]);
                    int n = Integer.parseInt(instruction[2]);
                    if (index >= 0 && index < stockList.size() && n > 0) {
                        if (buy(player, index, n)) {
                            break;
                        } else {
                            game.getUI().showMessage("资金不足");
                        }
                    } else {
                        game.getUI().showMessage("输入不合法");
                    }
                } catch (NumberFormatException  e) {
                    game.getUI().showMessage("输入不合法");
                }
            } else if (instruction[0].equals("s")) {
                try {
                    int index = Integer.parseInt(instruction[1]);
                    int n = Integer.parseInt(instruction[2]);
                    if (index >= 0 && index < stockList.size() && n > 0) {
                        if (sell(player, index, n)) {
                            break;
                        } else {
                            game.getUI().showMessage("股票数不足");
                        }
                    } else {
                        game.getUI().showMessage("输入不合法");
                    }
                } catch (NumberFormatException  e) {
                    game.getUI().showMessage("输入不合法");
                }
            } else if (instruction[0].equals("q")) {
                break;
            } else {
                game.getUI().showMessage("输入不合法");
            }

        } while (true);
    }

    private boolean sell(Player player, int index, int n) {
        double price = stockList.get(index).getPrice() * n;
        if (player.getStockList()[index] >= n) {
            player.getStockList()[index] -= n;
            player.addDeposit(price);
            System.out.print("玩家" + player.getName() + "卖出了" + n + "股" + stockList.get(index).getName());
            return true;
        } else {
            return false;
        }
    }

    private boolean buy(Player player, int index, int n) {
        double price = stockList.get(index).getPrice() * n;
        if (player.getDeposit() >= price) {
            player.subDeposit(price);
            player.getStockList()[index] += n;
            System.out.print("玩家" + player.getName() + "购买了" + n + "股" + stockList.get(index).getName());
            return true;
        } else if (player.getDeposit() + player.getCash() >= price) {
            player.subCash(price - player.getDeposit());
            player.setDeposit(0);
            player.getStockList()[index] += n;
            System.out.print("玩家" + player.getName() + "购买了" + n + "股" + stockList.get(index).getName());
            return true;
        } else {
            return false;
        }
    }

    public void fluc() {
        for (Stock stock: stockList) {
            stock.fluc();
        }
    }
}
