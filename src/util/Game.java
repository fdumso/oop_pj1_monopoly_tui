package util;

import card.CardSystem;
import lottery.LotterySystem;
import player.Player;
import player.PlayerSystem;
import stock.StockSystem;

/**
 * Created by freemso on 2016/4/26.
 */
public class Game {


    private LotterySystem lotterySystem;
    private StockSystem stockSystem;
    private MapSystem mapSystem;
    private TimeSystem timeSystem;
    private CardSystem cardSystem;
    private BankSystem bankSystem;
    private PlayerSystem playerSystem;

    private Dice dice;
    private UI ui;

    private int totalRoundNum;

    public Game() {
        playerSystem = new PlayerSystem();
        timeSystem = new TimeSystem();
        cardSystem = new CardSystem();
        bankSystem = new BankSystem();
        lotterySystem = new LotterySystem();
        stockSystem = new StockSystem();
        mapSystem = new MapSystem();
        dice = new Dice();
        ui = new UI();
    }





    public void play() {
        playerSystem.initPlayer(this);
        // init total rounds number
        totalRoundNum = ui.inputInt("请输入总回合数：", 0, 365);
        // startGame the game
        ui.startGame();
        for (int round = 0; round < totalRoundNum; round++) {
            ui.showMessage("今天是: " + timeSystem.printDate());
            mapSystem.printCurMap(this);
            for (Player player: playerSystem.getPlayerList()) {
                ui.showMessage("现在是玩家："
                        + player.getName()
                        + " 的操作时间\n"
                        + "现在的位置是： "
                        + player.getPosition().getName()
                        + " ，前进方向为："
                        + player.getDirection().toString() + " 。\n");
                operate(player);
            }
            for (Player player: playerSystem.getPlayerList()) {
                if (player.isBankrupt()) {
                    playerSystem.removePlayer(player);
                }
            }
            if (playerSystem.getPlayerList().size() == 1) {
                ui.showMessage("游戏结束！\n" + playerSystem.getPlayerList().get(0).getName() + "获得胜利");
                break;
            }
            if (timeSystem.isEndOfTheMonth()) {
                bankSystem.payInterest(this);
                lotterySystem.open(this);
            }
            stockSystem.fluc();
            timeSystem.addDay();
        }
        timeup();
    }

    private void timeup() {
        ui.showMessage("时间到，游戏结束");
        Player winner = playerSystem.maxCapitalPlayer();
        ui.showMessage(winner.getName() + "获得胜利");
    }

    public void operate(Player player) {
        Instruction instruction = ui.getInstruction();
        switch (instruction) {
            case PRINT_CUR_MAP: {
                // print current map
                mapSystem.printCurMap(this);
                operate(player);
                break;
            }
            case PRINT_ORI_MAP: {
                // print original map
                mapSystem.printOriMap(this);
                operate(player);
                break;
            }
            case USE_CARD: {
                // use cards
                player.useCard(this);
                operate(player);
                break;
            }
            case SHOW_WARNING: {
                // show warning
                mapSystem.showWarning(this, player);
                operate(player);
                break;
            }
            case SPOT_INFO: {
                // show spot info
                mapSystem.showSpotInfo(this, player);
                operate(player);
                break;
            }
            case PLAYER_INFO: {
                // show player info
                playerSystem.showPlayerInfo(this);
                operate(player);
                break;
            }
            case ROLL_DICE: {
                // roll the dice
                rollDice(player);
                break;
            }
            case CONCEDE: {
                // concede
                player.concede(this);
                break;
            }
            case CHECK_STOCK: {
                // stock
                stockSystem.mainPanel(this, player);
                operate(player);
            }
        }
    }


    public void rollDice(Player player) {
        mapSystem.printCurMap(this);
        int dicePoint = dice.roll();
        ui.showMessage("你掷得的点数为：" + dicePoint);
        player.move(this, dicePoint);
    }


    /* Read Method*/

    public MapSystem getMapSystem() {
        return mapSystem;
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

    public PlayerSystem getPlayerSystem() {
        return playerSystem;
    }

    public TimeSystem getTimeSystem() {
        return timeSystem;
    }

    public BankSystem getBankSystem() {
        return bankSystem;
    }

    public LotterySystem getLotterySystem() {
        return lotterySystem;
    }

}
