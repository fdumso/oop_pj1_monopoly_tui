package stock;

public class StockSystem {
    private Stock[] stockList = new Stock[10];

    private static final StockSystem stockSystem = new StockSystem();
    public static StockSystem getInstance() {
        return stockSystem;
    }
    private StockSystem() {
        stockList[0] = new Stock(0, "壳牌石油", 40);
        stockList[1] = new Stock(1, "诺贝尔", 140);
        stockList[2] = new Stock(2, "宝丽金", 25);
        stockList[3] = new Stock(3, "联合利华", 17);
        stockList[4] = new Stock(4, "亨氏", 100);
        stockList[5] = new Stock(5, "哈雷戴维森", 56);
        stockList[6] = new Stock(6, "IBM公司", 87);
        stockList[7] = new Stock(7, "瞻博网络", 34);
        stockList[8] = new Stock(8, "摩根大通", 28);
        stockList[9] = new Stock(9, "美赞臣", 112);
    }

    public void fluc() {
        for (Stock aStockList : stockList) {
            aStockList.fluc();
        }
    }

    public Stock get(int id) {
        return stockList[id];
    }
}
