package kernel;

import kernel.util.*;
import kernel.util.MapSystem;
import ui.tui.TUI;

/**
 * Created by freemso on 2016/4/26.
 */
public class Game {
    private final double originalCash = 2000;
    private final double originalDeposit = 2000;
    private final int originalTicket = 10;


    private MapSystem mapSystem;
    private TimeSystem timeSystem;
    private CardSystem cardSystem;
    private SpotSystem spotSystem;
    private BankSystem bankSystem;
    private PlayerSystem playerSystem;

    private Dice dice;
    private TUI ui;

    private int totalRoundNum;

    Game() {
        playerSystem = new PlayerSystem();
        timeSystem = new TimeSystem();
        cardSystem = new CardSystem();
        spotSystem = new SpotSystem();
        bankSystem = new BankSystem();
        mapSystem = new MapSystem();
        dice = new Dice();
        ui = new TUI();
    }

    public enum Instruction {
        PRINT_CUR_MAP, PRINT_ORI_MAP, USE_CARD, SHOW_WARNING, SPOT_INFO, PLAYER_INFO, ROLL_DICE, CONCEDE, CHECK_STOCK
    }



    void play() {
        // init player
        int playerNumber = ui.inputInt("请输入玩家个数（ 2 - 4 ）：", 2, 4);
        for (int i = 0; i < playerNumber; i++) {
            String name = ui.inputStr("请输入玩家 " + (i+1) + " 的昵称：", 2, 10);
            Player player = new Player(i, name, originalCash, originalDeposit, originalTicket);
            playerList.add(player);
            mapSystem.addPlayer(player);
        }
        // init total rounds number
        totalRoundNum = ui.inputInt("请输入总回合数：", 0, 365);
        // start the game
        ui.start();
        for (int round = 0; round < totalRoundNum; round++) {
            ui.showMessage("今天是: " + timeSystem.printDate());
            for (Player player: playerList) {
                ui.showMessage("现在是玩家："
                        + player.getName()
                        + " 的操作时间\n"
                        + "现在的位置是： "
                        + player.getPosition().getName()
                        + " ，前进方向为："
                        + player.getDirection().toString() + " 。\n");
                operate(player);
            }
            if (timeSystem.isEndOfTheMonth()) {
                bankSystem.payInterest(this);
            }
            timeSystem.addDay();
        }
    }

    public void operate(Player player) {
        Game.Instruction instruction = ui.getInstruction();
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
                spotSystem.showSpotInfo(this, player);
                operate(player);
                break;
            }
            case PLAYER_INFO: {
                // show player info
                playerSystem.showPlayerInfo();
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
                checkStock(player);
            }
        }
    }


    public void rollDice(Player player) {
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

    public TUI getUI() {
        return ui;
    }

    public CardSystem getCardSystem() {
        return cardSystem;
    }

    public SpotSystem getSpotSystem() {
        return spotSystem;
    }


    /* Write Method*/


    public void gameOver() {

    }

    private void checkStock(Player player) {
    }




}
