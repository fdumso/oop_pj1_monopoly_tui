package ui.tui;

import kernel.Player;
import kernel.card.AbstractCard;
import kernel.map.AbstractSpot;
import kernel.map.HouseSpot;
import ui.UI;

import java.util.ArrayList;


/**
 * Created by freemso on 2016/4/27.
 */
public class TUI implements UI {
    private InputReader inputReader;
    private Selection selection;

    public TUI() {
        this.selection = new
        this.inputReader = new InputReader();
    }

    @Override
    public void init() {
        // init player

    }

    @Override
    public void startRound() {
        System.out.println("今天是: " + game.getTimeSystem().printDate());
    }

    @Override
    public void endRound() {
        if (game.getTimeSystem().isEndOfTheMonth()) {
            System.out.println("月末，银行发利息");
            for (Player player: playerList) {
                double interest = player.getDeposit() * 0.1;
                player.addDeposit(interest);
                System.out.println("玩家 " + player.getName()
                        + "获得"
                        + interest
                        + "元储蓄利息！");
            }
        }
        inputReader.enter();
        game.getTimeSystem().addDay();
    }

    @Override
    public void playerStart(Player player) {
        System.out.print("现在是玩家："
                + player.getName()
                + " 的操作时间\n"
                + "现在的位置是： "
                + map.getSpot(player.getPosition()).getName()
                + " ，前进方向为："
                + player.getDirection().toString() + " 。\n");
    }

    @Override
    public void playerEnd(Player player) {

    }

    @Override
    public void main(Player player) {
        inputReader.enter();
        System.out.print("现在可以执行如下操作：\n" + "0 - 查看地图\n"
                + "1 - 查看原始地图\n" + "2 - 使用道具\n" + "3 - 前方十步内示警\n"
                + "4 - 查看前后指定步数的具体信息\n" + "5 - 查看玩家的资产信息\n"
                + "6 - 想看的都看了，心满意足地掷骰子\n" + "7 - 不玩了！认输！\n"
                + "8 - 存档\n" + "请选择：");
        int operationId = inputReader.readInt(0, 8);
        switch (operationId) {
            case 0: {
                // print current map
                printCurrentMap();
                break;
            }
            case 1: {
                // print original map
                printOriginalMap();
                break;
            }
            case 2: {
                // use cards
                useCard(player);
                break;
            }
            case 3: {
                // show warning
                warning(player);
                break;
            }
            case 4: {
                // show spot info
                showSpotInfo(player);
                break;
            }
            case 5: {
                // show player info
                showPlayerInfo();
                break;
            }
            case 6: {
                // roll the dice
                rollDice(player);
                break;
            }
            case 7: {
                // concede
                concede(player);
                break;
            }
        }
        main(player);
    }

    @Override
    public void useCard(Player player) {
        inputReader.enter();
        ArrayList<AbstractCard> cardList = player.getCardList();
        if (cardList.isEmpty()) {
            System.out.println("你没有任何卡片！");
            return;
        }
        int cardNum = cardList.size();
        System.out.println("你现在拥有的道具有：");
        for (int i = 0; i < cardNum; i++) {
            System.out.println(i + ". " + cardList.get(i).getName());
        }
        System.out.print("\n请输入你想使用的卡片编号<输入-1返回上一层>：");
        int cardId = inputReader.readInt(-1, cardNum - 1);
        if (cardId == -1) {
            System.out.println("返回上一级菜单");
        } else {
            boolean cardUsed = cardList.get(cardId).effect(game, player);
            if (cardUsed) {
                System.out.println("使用成功!");
                cardList.remove(cardId);
            }
        }
    }

    @Override
    public void warning(Player player) {
        int playerPosition = player.getPosition();
        boolean hasWarning = false;
        for (int i = 1; i <= 10; i++) {
            if (map.getSpot(playerPosition + i).hasBarricade()) {
                hasWarning = true;
                System.out.println("在你前方 " + i + " 步处有路障！");
            }
        }
        if (!hasWarning) {
            System.out.println("前方 10 步内未检测到路障");
        }
    }

    @Override
    public void showSpotInfo(Player player) {
        System.out.print("请输入你想查询的点与你相差的步数<顺时针方向为正，逆时针方向为负>：");
        int index = inputReader.readInt();
        int playerPosition = player.getPosition();
        AbstractSpot spot = map.getSpot(playerPosition + index);
        AbstractSpot.Type type = spot.getType();
        switch (type) {
            case HOUSE: {
                System.out.println("类型：房产\n名称：" + spot.getName() + "\n初始价格：" + ((HouseSpot) spot).getOriginalPrice()
                        + " 元\n等级：" + ((HouseSpot) spot).getLevel() + " 级\n当前价格：" + ((HouseSpot) spot).calcPrice() + " 元\n拥有者："
                        + ((HouseSpot) spot).getOwner().getName());
                break;
            }
            case BANK: {
                System.out.println("类型：银行\n名称：" + spot.getName());
                break;
            }
            case EMPTY: {
                System.out.println("类型：空地\n名称：" + spot.getName());
                break;
            }
            case CARD: {
                System.out.println("类型：赠送卡片点\n名称：" + spot.getName());
                break;
            }
            case TICKET: {
                System.out.println("类型：赠送点券点\n名称：" + spot.getName());
                break;
            }
            case LOTTERY: {
                System.out.println("类型：彩票店\n名称：" + spot.getName());
                break;
            }
            case NEWS: {
                System.out.println("类型：新闻点\n名称：" + spot.getName());
                break;
            }
            case STORE: {
                System.out.println("类型：道具店\n名称：" + spot.getName());
                break;
            }
        }
    }

    @Override
    public void showPlayerInfo() {
        System.out.println("玩家资产信息如下：");
        System.out.println("玩家名  点券  现金  存款  房产数  房产总价值  资产总额");
        for (Player player : playerList) {
            System.out.printf("%-8s%-6d%-6.2f%-6.2f%-8d%-12.2f%-10.2f\n",
                    player.getName(), player.getTicket(),
                    player.getCash(), player.getDeposit(),
                    player.getHouseList().size(), player.getHouseValue(),
                    player.getCapital());
        }
    }

    @Override
    public void rollDice(Player player) {
        printCurrentMap();
        inputReader.enter();
        int dicePoint = game.getDice().roll();
        System.out.println("你掷得的点数为：" + dicePoint);
        game.move(player, dicePoint);
    }

    @Override
    public void concede(Player player) {
        System.out.print("您确定要认输吗？<y/n>：");
        if (inputReader.confirm()) {
            game.concede(player);
        }
    }

    @Override
    public void gameOver() {

    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
        inputReader.enter();
    }

    @Override
    public AbstractSpot chooseASpotToSetBarricade(Player player) {
        System.out.print("请输入你想放置路障的点与你相差的步数（不超过8）<顺时针方向为正，逆时针方向为负>：");
        int index = inputReader.readInt(-8, 8);
        AbstractSpot spot = map.getSpot(player.getPosition() + index);
        if (spot.hasBarricade()) {
            System.out.print("此处已有路障，不能再次放置！");
            return chooseASpotToSetBarricade(player);
        } else {
            System.out.println("路障已成功放置！");
            return spot;
        }
    }

    @Override
    public boolean confirmMessage(String s) {
        System.out.print(s);
        return inputReader.confirm();
    }

    @Override
    public int getIntegerMessage(String s) {
        return 0;
    }

    @Override
    public int getIntegerMessage(String s, int l, int g) {
        return 0;
    }


    /* Private Method*/
    private void printOriginalMap() {
        System.out.print("               " + map.getSpot(0).getIcon() + " " + map.getSpot(1).getIcon() + " " + map.getSpot(2).getIcon() + " " + map.getSpot(3).getIcon() + " " + map.getSpot(4).getIcon() + " " + map.getSpot(5).getIcon() + " " + map.getSpot(6).getIcon() + "\n\n"
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
        System.out.print("               " + map.getSpot(0).printIcon() + " " + map.getSpot(1).printIcon() + " " + map.getSpot(2).printIcon() + " " + map.getSpot(3).printIcon() + " " + map.getSpot(4).printIcon() + " " + map.getSpot(5).printIcon() + " " + map.getSpot(6).printIcon() + "\n\n"
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
}