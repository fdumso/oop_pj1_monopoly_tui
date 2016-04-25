package ui;

import map.Map;

import java.util.Scanner;

/**
 * Created by freemso on 2016/4/25.
 */
public class Game {
    public static void main(String[] args) {
        init();

        System.out.println("==========游戏开始==========");
    }

    private static void init() {
        System.out.print("这是一个大富翁游戏,有两个玩家参与\n");
        new Scanner(System.in).nextLine();
        System.out.print("地图长成这个样子： \n");

        System.out.print("现在开始\n");

    }
}
