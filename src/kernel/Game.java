package kernel;

import kernel.card.AbstractCard;
import kernel.util.CardSystem;
import kernel.spot.AbstractSpot;
import kernel.spot.HouseSpot;
import kernel.map.Map;
import kernel.util.TimeSystem;
import kernel.util.Option;
import ui.UI;
import ui.tui.TUI;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/26.
 */
public class Game {
    private final double originalCash = 2000;
    private final double originalDeposit = 2000;
    private final int originalTicket = 10;

    private ArrayList<Player> playerList;
    private Map map;
    private TimeSystem time;
    private CardSystem cardSystem;
    private Dice dice;
    private int totalRoundNum;
    private UI ui;

    Game() {
        playerList = new ArrayList<>();
        map = new Map();
        time = new TimeSystem();
        cardSystem = new CardSystem();
        dice = new Dice();
        ui = new TUI();
    }

    public class Dice {
        private int point;
        private boolean isControlled;

        public int roll() {
            if (!isControlled) {
                point = (int) (Math.random() * 6 + 1);
            }
            return point;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            isControlled = true;
            this.point = point;
        }
    }

    public enum Operation {
        PRINT_CUR_MAP, PRINT_ORI_MAP, USE_CARD, SHOW_WARNING, SPOT_INFO, PLAYER_INFO, ROLL_DICE, CONCEDE, CHECK_STOCK
    }



    void init() {
        // init player
        int playerNumber = ui.inputInt("请输入玩家个数（ 2 - 4 ）：", 2, 4);
        for (int i = 0; i < playerNumber; i++) {
            System.out.print("请输入玩家 " + i + " 的昵称：");
            String name = ui.inputStr("请输入玩家 " + i + " 的昵称：", 0, 10);
            addPlayer(i, name);
        }
        // init total rounds number
        totalRoundNum = ui.inputInt("请输入总回合数：", 0, 365);
        // start the game
        ui.start();
        main();
    }

    /* Private Method*/
    private void main() {
        for (int round = 0; round < totalRoundNum; round++) {
            ui.showMessage("今天是: " + time.printDate());
            for (Player player: playerList) {
                ui.showMessage("现在是玩家："
                        + player.getName()
                        + " 的操作时间\n"
                        + "现在的位置是： "
                        + map.getSpot(player.getPosition()).getName()
                        + " ，前进方向为："
                        + player.getDirection().toString() + " 。\n");

                Operation operation = ui.showMainPanel();
                switch (operation) {
                    case PRINT_CUR_MAP: {
                        // print current map
                        printCurrentMap();
                        break;
                    }
                    case PRINT_ORI_MAP: {
                        // print original map
                        printOriginalMap();
                        break;
                    }
                    case USE_CARD: {
                        // use cards
                        useCard(player);
                        break;
                    }
                    case SHOW_WARNING: {
                        // show warning
                        showWarning(player);
                        break;
                    }
                    case SPOT_INFO: {
                        // show spot info
                        showSpotInfo(player);
                        break;
                    }
                    case PLAYER_INFO: {
                        // show player info
                        ui.showMessage("玩家资产信息如下：\n玩家名  点券  现金  存款  房产数  房产总价值  资产总额");
                        for (Player p : playerList) {
                            System.out.printf("%-8s%-6d%-6.2f%-6.2f%-8d%-12.2f%-10.2f\n",
                                    p.getName(), p.getTicket(),
                                    p.getCash(), p.getDeposit(),
                                    p.getHouseList().size(), p.getHouseValue(),
                                    p.getCapital());
                        }
                        break;
                    }
                    case ROLL_DICE: {
                        // roll the dice
                        printCurrentMap();
                        rollDice(player);
                        break;
                    }
                    case CONCEDE: {
                        // concede
                        concede(player);
                        break;
                    }
                    case CHECK_STOCK: {
                        // stock
                        checkStock(player);
                    }
                }
            }
            if (time.isEndOfTheMonth()) {
                ui.popMessage("月末，银行发利息");
                for (Player player: playerList) {
                    double interest = player.getDeposit() * 0.1;
                    player.addDeposit(interest);
                    ui.popMessage("玩家 " + player.getName()
                            + "获得"
                            + interest
                            + "元储蓄利息！");
                }
            }
            time.addDay();
        }
    }


    /* Read Method*/

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public Map getMap() {
        return map;
    }

    public Dice getDice() {
        return dice;
    }

    public UI getUI() {
        return ui;
    }

    public CardSystem getCardSystem() {
        return cardSystem;
    }

    /* Write Method*/

    private void addPlayer(int playerId, String playerName) {
        Player player = new Player(playerId, playerName, 0, Player.Direction.CLOCKWISE,
                originalCash, originalDeposit, originalTicket);
        playerList.add(player);
    }

    public void move(Player player, int steps) {
        for (int i = 0; i < steps; i++) {
            if (player.isTrapped()) {
                player.clearTrapped();
                break;
            } else {
                AbstractSpot outSpot = map.getSpot(player.getPosition());
                AbstractSpot inSpot = map.getSpot(player.getPosition() + player.getDirection().sign());
                outSpot.stepOut(this, player);
                player.setPosition(inSpot.getId());
                inSpot.stepIn(this, player);
            }
        }
        map.getSpot(player.getPosition()).stay(this, player);
    }

    public void rollDice(Player player) {
        int dicePoint = dice.roll();
        ui.showMessage("你掷得的点数为：" + dicePoint);
        move(player, dicePoint);
    }





    public void showWarning(Player player) {
        int playerPosition = player.getPosition();
        boolean hasWarning = false;
        for (int i = 1; i <= 10; i++) {
            if (map.getSpot(playerPosition + i).hasBarricade()) {
                hasWarning = true;
                ui.popMessage("在你前方 " + i + " 步处有路障！");
            }
        }
        if (!hasWarning) {
            ui.popMessage("前方 10 步内未检测到路障");
        }
    }

    public void showSpotInfo(Player player) {
        AbstractSpot spot = selectSpot(player);
        AbstractSpot.Type type = spot.getType();
        switch (type) {
            case HOUSE: {
                ui.popMessage("类型：房产\n名称：" + spot.getName() + "\n初始价格：" + ((HouseSpot) spot).getOriginalPrice()
                        + " 元\n等级：" + ((HouseSpot) spot).getLevel() + " 级\n当前价格：" + ((HouseSpot) spot).calcPrice() + " 元\n拥有者："
                        + ((HouseSpot) spot).getOwner().getName());
                break;
            }
            case BANK: {
                ui.popMessage("类型：银行\n名称：" + spot.getName());
                break;
            }
            case EMPTY: {
                ui.popMessage("类型：空地\n名称：" + spot.getName());
                break;
            }
            case CARD: {
                ui.popMessage("类型：赠送卡片点\n名称：" + spot.getName());
                break;
            }
            case TICKET: {
                ui.popMessage("类型：赠送点券点\n名称：" + spot.getName());
                break;
            }
            case LOTTERY: {
                ui.popMessage("类型：彩票店\n名称：" + spot.getName());
                break;
            }
            case NEWS: {
                ui.popMessage("类型：新闻点\n名称：" + spot.getName());
                break;
            }
            case STORE: {
                ui.popMessage("类型：道具店\n名称：" + spot.getName());
                break;
            }
        }
    }

    public void useCard(Player player) {
        ArrayList<AbstractCard> cardList = player.getCardList();
        if (cardList.isEmpty()) {
            ui.popMessage("你没有任何卡片！");
            return;
        }
        int cardNum = cardList.size();
        ArrayList<Option> optionList = new ArrayList<>();
        for (int i = 0; i < cardNum; i++) {
            optionList.add(cardList.get(i));
        }
        int optionIndex = ui.select("请选择你想使用的卡片", optionList);
        if (optionIndex == -1) {
            ui.showMessage("放弃使用");
            return;
        } else {
            boolean cardUsed = cardList.get(optionIndex).effect(this, player);
            if (cardUsed) {
                ui.showMessage("使用成功!");
                cardList.remove(optionIndex);
            }
        }
    }

    private void printOriginalMap() {
        ui.showMessage("               " + map.getSpot(0).getIcon() + " " + map.getSpot(1).getIcon() + " " + map.getSpot(2).getIcon() + " " + map.getSpot(3).getIcon() + " " + map.getSpot(4).getIcon() + " " + map.getSpot(5).getIcon() + " " + map.getSpot(6).getIcon() + "\n\n"
                + "               " + map.getSpot(61).getIcon() + " " + "               " + map.getSpot(7).getIcon() + " " + "\n\n"
                + "               " + map.getSpot(60).getIcon() + " " + "               " + map.getSpot(8).getIcon() + " " + "\n\n"
                + "               " + map.getSpot(59).getIcon() + " " + "               " + map.getSpot(9).getIcon() + " " + "\n\n"
                + "               " + map.getSpot(58).getIcon() + " " + "               " + map.getSpot(10).getIcon() + " " + "\n\n"
                + map.getSpot(52).getIcon() + " " + map.getSpot(53).getIcon() + " " + map.getSpot(54).getIcon() + " " + map.getSpot(55).getIcon() + " " + map.getSpot(56).getIcon() + " " + map.getSpot(57).getIcon() + " " + "               " + map.getSpot(11).getIcon() + " " + map.getSpot(12).getIcon() + " " + map.getSpot(13).getIcon() + " " + map.getSpot(14).getIcon() + " " + map.getSpot(15).getIcon() + " " + map.getSpot(16).getIcon() + " " + "\n\n"
                + map.getSpot(51).getIcon() + " " + "              " + "                " + "               " + map.getSpot(17).getIcon() + " " + "\n\n"
                + map.getSpot(50).getIcon() + " " + "              " + "                " + "               " + map.getSpot(18).getIcon() + " " + "\n\n"
                + map.getSpot(49).getIcon() + " " + "              " + "                " + "               " + map.getSpot(19).getIcon() + " " + "\n\n"
                + map.getSpot(48).getIcon() + " " + "              " + "                " + "               " + map.getSpot(20).getIcon() + " " + "\n\n"
                + map.getSpot(47).getIcon() + " " + map.getSpot(46).getIcon() + " " + map.getSpot(45).getIcon() + " " + map.getSpot(44).getIcon() + " " + map.getSpot(43).getIcon() + " " + map.getSpot(42).getIcon() + " " + "               " + map.getSpot(26).getIcon() + " " + map.getSpot(25).getIcon() + " " + map.getSpot(24).getIcon() + " " + map.getSpot(23).getIcon() + " " + map.getSpot(22).getIcon() + " " + map.getSpot(21).getIcon() + " " + "\n\n"
                + "               " + map.getSpot(41).getIcon() + " " + "               " + map.getSpot(27).getIcon() + " " + "\n\n"
                + "               " + map.getSpot(40).getIcon() + " " + "               " + map.getSpot(28).getIcon() + " " + "\n\n"
                + "               " + map.getSpot(39).getIcon() + " " + "               " + map.getSpot(29).getIcon() + " " + "\n\n"
                + "               " + map.getSpot(38).getIcon() + " " + "               " + map.getSpot(30).getIcon() + " " + "\n\n"
                + "               " + map.getSpot(37).getIcon() + " " + map.getSpot(36).getIcon() + " " + map.getSpot(35).getIcon() + " " + map.getSpot(34).getIcon() + " " + map.getSpot(33).getIcon() + " " + map.getSpot(32).getIcon() + " " + map.getSpot(31).getIcon() + " " + "\n"
        );
    }

    private void printCurrentMap() {
        ui.showMessage("               " + map.getSpot(0).printIcon() + " " + map.getSpot(1).printIcon() + " " + map.getSpot(2).printIcon() + " " + map.getSpot(3).printIcon() + " " + map.getSpot(4).printIcon() + " " + map.getSpot(5).printIcon() + " " + map.getSpot(6).printIcon() + "\n\n"
                + "               " + map.getSpot(61).printIcon() + " " + "               " + map.getSpot(7).printIcon() + " " + "\n\n"
                + "               " + map.getSpot(60).printIcon() + " " + "               " + map.getSpot(8).printIcon() + " " + "\n\n"
                + "               " + map.getSpot(59).printIcon() + " " + "               " + map.getSpot(9).printIcon() + " " + "\n\n"
                + "               " + map.getSpot(58).printIcon() + " " + "               " + map.getSpot(10).printIcon() + " " + "\n\n"
                + map.getSpot(52).printIcon() + " " + map.getSpot(53).printIcon() + " " + map.getSpot(54).printIcon() + " " + map.getSpot(55).printIcon() + " " + map.getSpot(56).printIcon() + " " + map.getSpot(57).printIcon() + " " + "               " + map.getSpot(11).printIcon() + " " + map.getSpot(12).printIcon() + " " + map.getSpot(13).printIcon() + " " + map.getSpot(14).printIcon() + " " + map.getSpot(15).printIcon() + " " + map.getSpot(16).printIcon() + " " + "\n\n"
                + map.getSpot(51).printIcon() + " " + "              " + "                " + "               " + map.getSpot(17).printIcon() + " " + "\n\n"
                + map.getSpot(50).printIcon() + " " + "              " + "                " + "               " + map.getSpot(18).printIcon() + " " + "\n\n"
                + map.getSpot(49).printIcon() + " " + "              " + "                " + "               " + map.getSpot(19).printIcon() + " " + "\n\n"
                + map.getSpot(48).printIcon() + " " + "              " + "                " + "               " + map.getSpot(20).printIcon() + " " + "\n\n"
                + map.getSpot(47).printIcon() + " " + map.getSpot(46).printIcon() + " " + map.getSpot(45).printIcon() + " " + map.getSpot(44).printIcon() + " " + map.getSpot(43).printIcon() + " " + map.getSpot(42).printIcon() + " " + "               " + map.getSpot(26).printIcon() + " " + map.getSpot(25).printIcon() + " " + map.getSpot(24).printIcon() + " " + map.getSpot(23).printIcon() + " " + map.getSpot(22).printIcon() + " " + map.getSpot(21).printIcon() + " " + "\n\n"
                + "               " + map.getSpot(41).printIcon() + " " + "               " + map.getSpot(27).printIcon() + " " + "\n\n"
                + "               " + map.getSpot(40).printIcon() + " " + "               " + map.getSpot(28).printIcon() + " " + "\n\n"
                + "               " + map.getSpot(39).printIcon() + " " + "               " + map.getSpot(29).printIcon() + " " + "\n\n"
                + "               " + map.getSpot(38).printIcon() + " " + "               " + map.getSpot(30).printIcon() + " " + "\n\n"
                + "               " + map.getSpot(37).printIcon() + " " + map.getSpot(36).printIcon() + " " + map.getSpot(35).printIcon() + " " + map.getSpot(34).printIcon() + " " + map.getSpot(33).printIcon() + " " + map.getSpot(32).printIcon() + " " + map.getSpot(31).printIcon() + " " + "\n"
        );
    }

    /* TOOOOOOOOOOO    DOOOOOOOOOOOOOOOOOO*/
    public AbstractSpot selectSpot(Player player) {
        return null;
    }


    public void gameOver() {

    }

    private void checkStock(Player player) {
    }


    public void concede(Player player) {
        if (ui.confirm("您确定要认输吗？")) {
            // TO-DO
        }
    }

    /* TOOOOOOOOOOO    DOOOOOOOOOOOOOOOOOO*/


}
