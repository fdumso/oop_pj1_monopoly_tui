package ui;

import kernel.GameLogic;

/**
 * Created by freemso on 2016/4/25.
 */
public class TerminalGame {
    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();
        InputReader inputReader = new InputReader();
        TerminalMap terminalMap = new TerminalMap();


        System.out.print("请选择玩家个数：（ 2 - 4 ）");
        int playerNumber = inputReader.readInt(2, 4);





        System.out.println("==========游戏开始==========");
    }
}
