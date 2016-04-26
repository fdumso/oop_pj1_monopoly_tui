package ui;

import kernel.Game;
import kernel.Player;

/**
 * Created by freemso on 2016/4/25.
 */
public class TerminalGame {
    private final double originalCash = 2000;
    private final double originalDeposit = 2000;
    private final int originalTicket = 10;
    private Game game;
    private InputReader inputReader;


    public TerminalGame() {
        inputReader = new InputReader();
        game = new Game();
    }

    public void init() {
        // init player
        System.out.print("请选择玩家个数：（ 2 - 4 ）");
        int playerNumber = inputReader.readInt(2, 4);
        for (int i = 0; i < playerNumber; i++) {
            System.out.print("请输入玩家 " + i + " 的昵称：");
            String playerName = inputReader.readString(0, 10);
            Player player = new Player(i, playerName, 0, Player.Direction.CLOCKWISE, originalCash, originalDeposit, originalTicket);
            game.addPlayer(player);
        }

        // init total rounds number
        System.out.print("请输入总回合数：");
        int totalRoundsNum = inputReader.readInt(0, 365);
        game.setTotalRoundsNum(totalRoundsNum);
        // start main circle
        main();

    }

    public void main() {

        System.out.println("==========游戏开始==========");
    }

    public void printOriginalMap() {
        System.out.print("               " + game.getMap().getSpotList().get(0).getIcon() + " " + game.getMap().getSpotList().get(1).getIcon() + " " + game.getMap().getSpotList().get(2).getIcon() + " " + game.getMap().getSpotList().get(3).getIcon() + " " + game.getMap().getSpotList().get(4).getIcon() + " " + game.getMap().getSpotList().get(5).getIcon() + " " + game.getMap().getSpotList().get(6).getIcon() + "\n\n"
                + "               " + game.getMap().getSpotList().get(61).getIcon() + " " + "               " + game.getMap().getSpotList().get(7).getIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(60).getIcon() + " " + "               " + game.getMap().getSpotList().get(8).getIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(59).getIcon() + " " + "               " + game.getMap().getSpotList().get(9).getIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(58).getIcon() + " " + "               " + game.getMap().getSpotList().get(10).getIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(52).getIcon() + " " + game.getMap().getSpotList().get(53).getIcon() + " " + game.getMap().getSpotList().get(54).getIcon() + " " + game.getMap().getSpotList().get(55).getIcon() + " " + game.getMap().getSpotList().get(56).getIcon() + " " + game.getMap().getSpotList().get(57).getIcon() + " " + "               " + game.getMap().getSpotList().get(11).getIcon() + " " + game.getMap().getSpotList().get(12).getIcon() + " " + game.getMap().getSpotList().get(13).getIcon() + " " + game.getMap().getSpotList().get(14).getIcon() + " " + game.getMap().getSpotList().get(15).getIcon() + " " + game.getMap().getSpotList().get(16).getIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(51).getIcon() + " " + "              " + "                " + "               " + game.getMap().getSpotList().get(17).getIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(50).getIcon() + " " + "              " + "                " + "               " + game.getMap().getSpotList().get(18).getIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(49).getIcon() + " " + "              " + "                " + "               " + game.getMap().getSpotList().get(19).getIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(48).getIcon() + " " + "              " + "                " + "               " + game.getMap().getSpotList().get(20).getIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(47).getIcon() + " " + game.getMap().getSpotList().get(46).getIcon() + " " + game.getMap().getSpotList().get(45).getIcon() + " " + game.getMap().getSpotList().get(44).getIcon() + " " + game.getMap().getSpotList().get(43).getIcon() + " " + game.getMap().getSpotList().get(42).getIcon() + " " + "               " + game.getMap().getSpotList().get(26).getIcon() + " " + game.getMap().getSpotList().get(25).getIcon() + " " + game.getMap().getSpotList().get(24).getIcon() + " " + game.getMap().getSpotList().get(23).getIcon() + " " + game.getMap().getSpotList().get(22).getIcon() + " " + game.getMap().getSpotList().get(21).getIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(41).getIcon() + " " + "               " + game.getMap().getSpotList().get(27).getIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(40).getIcon() + " " + "               " + game.getMap().getSpotList().get(28).getIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(39).getIcon() + " " + "               " + game.getMap().getSpotList().get(29).getIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(38).getIcon() + " " + "               " + game.getMap().getSpotList().get(30).getIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(37).getIcon() + " " + game.getMap().getSpotList().get(36).getIcon() + " " + game.getMap().getSpotList().get(35).getIcon() + " " + game.getMap().getSpotList().get(34).getIcon() + " " + game.getMap().getSpotList().get(33).getIcon() + " " + game.getMap().getSpotList().get(32).getIcon() + " " + game.getMap().getSpotList().get(31).getIcon() + " " + "\n"
        );
    }

    public void printCurrentMap() {
        System.out.print("               " + game.getMap().getSpotList().get(0).printIcon() + " " + game.getMap().getSpotList().get(1).printIcon() + " " + game.getMap().getSpotList().get(2).printIcon() + " " + game.getMap().getSpotList().get(3).printIcon() + " " + game.getMap().getSpotList().get(4).printIcon() + " " + game.getMap().getSpotList().get(5).printIcon() + " " + game.getMap().getSpotList().get(6).printIcon() + "\n\n"
                + "               " + game.getMap().getSpotList().get(61).printIcon() + " " + "               " + game.getMap().getSpotList().get(7).printIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(60).printIcon() + " " + "               " + game.getMap().getSpotList().get(8).printIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(59).printIcon() + " " + "               " + game.getMap().getSpotList().get(9).printIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(58).printIcon() + " " + "               " + game.getMap().getSpotList().get(10).printIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(52).printIcon() + " " + game.getMap().getSpotList().get(53).printIcon() + " " + game.getMap().getSpotList().get(54).printIcon() + " " + game.getMap().getSpotList().get(55).printIcon() + " " + game.getMap().getSpotList().get(56).printIcon() + " " + game.getMap().getSpotList().get(57).printIcon() + " " + "               " + game.getMap().getSpotList().get(11).printIcon() + " " + game.getMap().getSpotList().get(12).printIcon() + " " + game.getMap().getSpotList().get(13).printIcon() + " " + game.getMap().getSpotList().get(14).printIcon() + " " + game.getMap().getSpotList().get(15).printIcon() + " " + game.getMap().getSpotList().get(16).printIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(51).printIcon() + " " + "              " + "                " + "               " + game.getMap().getSpotList().get(17).printIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(50).printIcon() + " " + "              " + "                " + "               " + game.getMap().getSpotList().get(18).printIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(49).printIcon() + " " + "              " + "                " + "               " + game.getMap().getSpotList().get(19).printIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(48).printIcon() + " " + "              " + "                " + "               " + game.getMap().getSpotList().get(20).printIcon() + " " + "\n\n"
                + game.getMap().getSpotList().get(47).printIcon() + " " + game.getMap().getSpotList().get(46).printIcon() + " " + game.getMap().getSpotList().get(45).printIcon() + " " + game.getMap().getSpotList().get(44).printIcon() + " " + game.getMap().getSpotList().get(43).printIcon() + " " + game.getMap().getSpotList().get(42).printIcon() + " " + "               " + game.getMap().getSpotList().get(26).printIcon() + " " + game.getMap().getSpotList().get(25).printIcon() + " " + game.getMap().getSpotList().get(24).printIcon() + " " + game.getMap().getSpotList().get(23).printIcon() + " " + game.getMap().getSpotList().get(22).printIcon() + " " + game.getMap().getSpotList().get(21).printIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(41).printIcon() + " " + "               " + game.getMap().getSpotList().get(27).printIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(40).printIcon() + " " + "               " + game.getMap().getSpotList().get(28).printIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(39).printIcon() + " " + "               " + game.getMap().getSpotList().get(29).printIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(38).printIcon() + " " + "               " + game.getMap().getSpotList().get(30).printIcon() + " " + "\n\n"
                + "               " + game.getMap().getSpotList().get(37).printIcon() + " " + game.getMap().getSpotList().get(36).printIcon() + " " + game.getMap().getSpotList().get(35).printIcon() + " " + game.getMap().getSpotList().get(34).printIcon() + " " + game.getMap().getSpotList().get(33).printIcon() + " " + game.getMap().getSpotList().get(32).printIcon() + " " + game.getMap().getSpotList().get(31).printIcon() + " " + "\n"
        );
    }
}
